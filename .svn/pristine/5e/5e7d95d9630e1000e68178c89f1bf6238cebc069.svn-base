package com.zepal.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.zepal.seckill.dto.ExposerDTO;
import com.zepal.seckill.dto.SeckillExecutionDTO;
import com.zepal.seckill.exception.RepeatKillException;
import com.zepal.seckill.exception.SeckillClosedException;
import com.zepal.seckill.po.SeckillPO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ISeckillServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ISeckillServiceTest.class);
	
	@Autowired
	private ISeckillService iSeckillService;
	
	@Test
	public void listSeckillsTest() {
		List<SeckillPO> seckillPOs = iSeckillService.listSeckills();
		
		logger.info(JSONObject.toJSONString(seckillPOs));
	}
	
	@Test
	public void getSeckillByIdTest() {
		
		long seckillId = 1000;
		SeckillPO seckillPO = iSeckillService.getSeckillById(seckillId);
		
		logger.info("seckillPO={}", JSONObject.toJSONString(seckillPO));
	}
	
	@Test
	public void exportSeckillUrlTest() {
		
		long seckillId = 1000;
		ExposerDTO exposerDTO = iSeckillService.exportSeckillUrl(seckillId);
		
		logger.info(JSONObject.toJSONString(exposerDTO));
	}
	
	@Test
	public void executionSeckillTest() {
		long seckillId = 1000L;
		String userPhone = "17783754874";
		String md5 = "080d5a3c3cf5732df0d57f61993c2dfc";
		
		SeckillExecutionDTO seckillExecutionDTO = iSeckillService.executionSeckill(seckillId, userPhone, md5);
		logger.info(seckillExecutionDTO.toString());
	}
	
	/**
	 * 集成测试
	 * */
	@Test
	public void seckillLogicTest() {
		long seckillId = 1000;
		ExposerDTO exposerDTO = iSeckillService.exportSeckillUrl(seckillId);
		
		if(exposerDTO.isExposed()) {//秒杀开启
			logger.info(JSONObject.toJSONString(exposerDTO));
			
			String userPhone = "17783754878";
			String md5 = exposerDTO.getMd5();
			
			try {
				SeckillExecutionDTO seckillExecutionDTO = iSeckillService.executionSeckill(seckillId, userPhone, md5);
				logger.info(JSONObject.toJSONString(seckillExecutionDTO));
			}catch(RepeatKillException e) {
				logger.error(e.getMessage());
			} catch (SeckillClosedException e) {
				logger.error(e.getMessage());
			}
		}else {//秒杀未开启
			logger.warn("秒杀未开启-->" + JSONObject.toJSONString(exposerDTO));
		}
	}
}
