package com.zepal.seckill.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zepal.seckill.po.SuccessSeckillPO;

public interface SuccessSeckillDao {

	/**
	 * 插入购买明细,可过滤重复
	 * @param seckillId 商品id
	 * @param userPhone 用户手机号
	 * */
	int insertSuccessSeckill(@Param("seckillId")long seckillId, @Param("userPhone")String userPhone);
	
	/**
	 * 根据id查询秒杀成功明细
	 * @param seckillId 商品id
	 * */
	SuccessSeckillPO queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")String userPhone);
	
	/**
	 * <p> 使用存储过程执行秒杀
	 * 
	 * */
	void killByProcedure(Map<String, Object> paramMap);
}
