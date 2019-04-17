package com.zepal.seckill.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zepal.seckill.common.EnumConstants;
import com.zepal.seckill.common.Msg;
import com.zepal.seckill.dto.ExposerDTO;
import com.zepal.seckill.dto.SeckillExecutionDTO;
import com.zepal.seckill.po.SeckillPO;
import com.zepal.seckill.service.ISeckillService;
import com.zepal.seckill.vo.SeckillVO;

@Controller
@RequestMapping("/seckill") //url:/模块/资源/{id}/细分
public class SeckillController {
	
	private static final Logger logger = LoggerFactory.getLogger(SeckillController.class);
	
	@Autowired
	private ISeckillService iSeckillService;
	
	/**
	 * 	获取秒杀列表
	 * */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public Msg listSeckill() {
		List<SeckillPO> seckillPOs = iSeckillService.listSeckills();
		
		//po转vo 并格式化数据
		List<SeckillVO> listSeckillVOs = new ArrayList<SeckillVO>();
		//好像传说SimpleDateFormat是线程不安全的,所以不适用将其设计成单例的或者交给IOC管理
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(SeckillPO seckillPO : seckillPOs) {
			SeckillVO listSeckillVO = new SeckillVO();
			listSeckillVO.setSeckillId(seckillPO.getSeckillId());
			listSeckillVO.setName(seckillPO.getName());
			listSeckillVO.setNumber(seckillPO.getNumber());
			listSeckillVO.setStartTime(df.format(seckillPO.getStartTime()));
			listSeckillVO.setEndTime(df.format(seckillPO.getEndTime()));
			listSeckillVO.setCreateTime(df.format(seckillPO.getCreateTime()));
			listSeckillVOs.add(listSeckillVO);
		}
		df = null;
		
		return Msg.success(EnumConstants.SUCCESS).add("listSeckillVOs", listSeckillVOs);
	}
	
	/**
	 *  获取秒杀详情页
	 * */
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	@ResponseBody
	public Msg getSeckillDetail(@PathVariable("seckillId")long seckillId, HttpServletResponse response) {
		SeckillPO seckillPO = iSeckillService.getSeckillById(seckillId);
		if(seckillPO == null) {
			//重定向到列表页
			try {
				response.sendRedirect("/list");
			} catch (IOException e) {
				return Msg.fail(EnumConstants.SYSTEM_EXCEPTION);
			}
		}
		
		//po转vo 并格式化数据
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SeckillVO detailSeckillVO = new SeckillVO();
		detailSeckillVO.setSeckillId(seckillPO.getSeckillId());
		detailSeckillVO.setName(seckillPO.getName());
		detailSeckillVO.setNumber(seckillPO.getNumber());
		detailSeckillVO.setStartTime(df.format(seckillPO.getStartTime()));
		detailSeckillVO.setEndTime(df.format(seckillPO.getEndTime()));
		detailSeckillVO.setCreateTime(df.format(seckillPO.getCreateTime()));
		df = null;
		
		return Msg.success(EnumConstants.SUCCESS).add("detailSeckillVO", detailSeckillVO);
	}
	
	/**
	 *  暴露秒杀地址
	 * */
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST)
	@ResponseBody
	public Msg exposer(@PathVariable("seckillId")long seckillId) {
		try {
			ExposerDTO exposerDTO = iSeckillService.exportSeckillUrl(seckillId);
			return Msg.success(EnumConstants.SUCCESS).add("exposer", exposerDTO);
		} catch (Exception e) {
			logger.error("SeckillController-->exposer-->" + e.getMessage());
			return Msg.fail(EnumConstants.FAIL).add("errorInfo", e.getMessage());
		}
	}
	
	/**
	 *  <p>执行秒杀
	 * */
	/*
	 * @RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.
	 * POST, produces= {"application/json;charset=utf-8"})
	 * 
	 * @ResponseBody public Msg execute(@PathVariable("seckillId")long
	 * seckillId, @PathVariable("md5")String md5, @CookieValue(value="killPhone",
	 * required=false)String userPhone) { //参数验证 if(userPhone == null) { return
	 * Msg.fail(EnumConstants.FAIL).add("errorInfo", "未注册"); }
	 * 
	 * //调用服务 try { SeckillExecutionDTO seckillExecutionDTO =
	 * iSeckillService.executionSeckill(seckillId, userPhone, md5); return
	 * Msg.success(EnumConstants.SUCCESS).add("seckillExecution",
	 * seckillExecutionDTO); }catch(SeckillClosedException e) { return
	 * Msg.fail(EnumConstants.FAIL).add("errorInfo", e.getMessage());
	 * }catch(RepeatKillException e) { return
	 * Msg.fail(EnumConstants.FAIL).add("errorInfo", e.getMessage()); } catch
	 * (Exception e) { return Msg.fail(EnumConstants.FAIL).add("errorInfo",
	 * e.getMessage()); } }
	 */
	
	/**
	 *  <p>执行秒杀 通过存储过程
	 * */
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,
			produces= {"application/json;charset=utf-8"})
	@ResponseBody
	public Msg execute(@PathVariable("seckillId")long seckillId, @PathVariable("md5")String md5, @CookieValue(value="killPhone", required=false)String userPhone) {
		//参数验证
		if(userPhone == null) {
			return Msg.fail(EnumConstants.FAIL).add("errorInfo", "未注册");
		}
		
		//调用服务
		try {
			SeckillExecutionDTO seckillExecutionDTO = iSeckillService.executionSeckillByProcedure(seckillId, userPhone, md5);
			return Msg.success(EnumConstants.SUCCESS).add("seckillExecution", seckillExecutionDTO);
		} catch (Exception e) {
			return Msg.fail(EnumConstants.FAIL).add("errorInfo", e.getMessage());
		}
	}
	
	
	/**
	 * <p>获取系统当前时间
	 * */
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public Msg getTime() {
		return Msg.success(EnumConstants.SUCCESS).add("now", System.currentTimeMillis());
	}
}
