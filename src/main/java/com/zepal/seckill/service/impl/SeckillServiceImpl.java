package com.zepal.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.zepal.seckill.cache.ICacheService;
import com.zepal.seckill.common.SeckillStateEnum;
import com.zepal.seckill.dao.SeckillDao;
import com.zepal.seckill.dao.SuccessSeckillDao;
import com.zepal.seckill.dto.ExposerDTO;
import com.zepal.seckill.dto.SeckillExecutionDTO;
import com.zepal.seckill.exception.RepeatKillException;
import com.zepal.seckill.exception.SeckillClosedException;
import com.zepal.seckill.exception.SeckillExeption;
import com.zepal.seckill.po.SeckillPO;
import com.zepal.seckill.po.SuccessSeckillPO;
import com.zepal.seckill.service.ISeckillService;

@Service
public class SeckillServiceImpl implements ISeckillService {

	private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
	
	/**用於混淆md5**/
	private static final String slat = "adskasj18297398kj78^&^$&^(*hjhh";
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessSeckillDao successSeckillDao;
	
	@Autowired
	private ICacheService iCacheService;
	
	@Override
	@Transactional(readOnly=true)
	public List<SeckillPO> listSeckills() {
		return seckillDao.queryAll(0, 4);
	}

	@Override
	@Transactional(readOnly=true)
	public SeckillPO getSeckillById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	@Transactional(readOnly=true)
	public ExposerDTO exportSeckillUrl(long seckillId) {
		//1、访问redis
		SeckillPO seckillPO = iCacheService.getCache("seckillPO:"+seckillId, SeckillPO.class);
		ExposerDTO exposerDTO = new ExposerDTO();
		if(seckillPO == null) {
			//2、访问数据库
			seckillPO = seckillDao.queryById(seckillId);
			if(seckillPO == null) {
				exposerDTO.setExposed(false);
				exposerDTO.setSeckillId(seckillId);
				return exposerDTO;
			}else {
				//3、放入缓存
				iCacheService.setCache("seckillPO:"+seckillId, seckillPO, 1L, TimeUnit.HOURS);
			}
		}
		//SeckillPO seckillPO = seckillDao.queryById(seckillId);
		Date start_time = seckillPO.getStartTime();
		Date end_time = seckillPO.getEndTime();
		Date now_time = new Date();
		//秒杀未开启
		if(now_time.getTime()<start_time.getTime() || now_time.getTime()>end_time.getTime()) {
			exposerDTO.setExposed(false);
			exposerDTO.setSeckillId(seckillId);
			exposerDTO.setNow(now_time.getTime());
			exposerDTO.setStart(start_time.getTime());
			exposerDTO.setEnd(end_time.getTime());
			return exposerDTO;
		}
		String md5 = getMd5(seckillId);
		exposerDTO.setExposed(true);
		exposerDTO.setMd5(md5);
		exposerDTO.setSeckillId(seckillId);
		return exposerDTO;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public SeckillExecutionDTO executionSeckill(long seckillId, String userPhone, String md5)
			throws SeckillExeption, RepeatKillException, SeckillClosedException {
		if(md5==null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillExeption("秒杀数据异常");
		}
		SeckillExecutionDTO seckillExecutionDTO = new SeckillExecutionDTO();
		try {
			//执行秒杀行为: 减库存+记录购买行为
			Date now_date = new Date();
			int updateCount = seckillDao.reduceNumber(seckillId, now_date);
			if(updateCount <= 0) {
				//没有更新到记录
				throw new SeckillClosedException("秒杀结束");
			}else {
				//减库存成功,记录购买行为
				int insertCount = successSeckillDao.insertSuccessSeckill(seckillId, userPhone);
				//唯一性 seckillId+userPhone
				if(insertCount < 1) {
					throw new RepeatKillException("重复秒杀");
				}else {
					SuccessSeckillPO successSeckillPO = successSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
					seckillExecutionDTO.setSeckillId(seckillId);
					seckillExecutionDTO.setState(SeckillStateEnum.SUCCESS.getState());
					seckillExecutionDTO.setStateInfo(SeckillStateEnum.SUCCESS.getStateInfo());
					seckillExecutionDTO.setSuccessSeckillPO(successSeckillPO);
					return seckillExecutionDTO;
				}
			}
		}catch(SeckillClosedException e1) {
			throw e1;
		}catch(RepeatKillException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error("SeckillServiceImpl-->executionSeckill-->" + e.getMessage());
			throw new SeckillExeption("seckill inner error : " + e.getMessage());
		}
	}
	
	private String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	
	/**
	 * <p> 这里不能声明事务,已经交给存储过程去管理事务
	 * */
	@Override
	public SeckillExecutionDTO executionSeckillByProcedure(long seckillId, String userPhone, String md5) throws SeckillExeption {
		if(md5==null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillExeption("秒杀数据异常");
		}
		
		Date killTime = new Date();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("seckillId", seckillId);
		paramMap.put("phone", userPhone);
		paramMap.put("killTime", killTime);
		paramMap.put("result", null); //OUT 参数会返回到map中去
		
		try {
			successSeckillDao.killByProcedure(paramMap);
			//获取result 
			int result = MapUtils.getInteger(paramMap, "result", -2);
			if(result == 1) {
				SuccessSeckillPO successSeckillPO = successSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
				SeckillExecutionDTO seckillExecutionDTO = new SeckillExecutionDTO();
				seckillExecutionDTO.setSeckillId(seckillId);
				seckillExecutionDTO.setState(SeckillStateEnum.SUCCESS.getState());
				seckillExecutionDTO.setStateInfo(SeckillStateEnum.SUCCESS.getStateInfo());
				seckillExecutionDTO.setSuccessSeckillPO(successSeckillPO);
				return seckillExecutionDTO;
			}else {
				throw new SeckillExeption("重复秒杀");
			}
		} catch (Exception e) {
			logger.error("SeckillServiceImpl-->executionSeckillByProcedure-->" + e.getMessage());
			throw new SeckillExeption(e.getMessage());
		}
	}

}
