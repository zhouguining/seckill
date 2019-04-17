package com.zepal.seckill.service;

import java.util.List;

import com.zepal.seckill.dto.ExposerDTO;
import com.zepal.seckill.dto.SeckillExecutionDTO;
import com.zepal.seckill.exception.RepeatKillException;
import com.zepal.seckill.exception.SeckillClosedException;
import com.zepal.seckill.exception.SeckillExeption;
import com.zepal.seckill.po.SeckillPO;

/**
 * 业务接口:站在"使用者"角度设计接口
 * 三个方面:方法定义粒度、参数(越简练、越直接越好)、返回类型(类型又好)
 * 说白了,遵循单一职责,不要太过冗余
 * */
public interface ISeckillService {

	/**
	 * 查询所有秒杀记录
	 * */
	List<SeckillPO> listSeckills();
	
	/**
	 * 查询单个秒杀记录
	 * @param seckillId 秒杀id
	 * */
	SeckillPO getSeckillById(long seckillId);
	
	/**
	 * 秒杀开启时输出秒杀地址,否则输出系统时间和秒杀时间
	 * @param seckillId 秒杀id
	 * */
	ExposerDTO exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀
	 * @param seckillId 秒杀id
	 * @param userPhone 用户电话
	 * @param md5 针对秒杀数据记录,防止被串改
	 * */
	SeckillExecutionDTO executionSeckill(long seckillId, String userPhone, String md5)
		throws SeckillExeption, RepeatKillException, SeckillClosedException;
	
	/**
	 * <p>执行秒杀 通过 存储过程
	 * @param seckillId 秒杀id
	 * @param userPhone 用户电话
	 * @param md5 针对秒杀数据记录,防止被串改
	 * */
	SeckillExecutionDTO executionSeckillByProcedure(long seckillId, String userPhone, String md5) throws SeckillExeption;
}
