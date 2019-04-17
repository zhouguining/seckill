package com.zepal.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zepal.seckill.po.SeckillPO;

public interface SeckillDao {

	/**
	 *减库存
	 *@param seckillId 商品id
	 *@param killTime 秒杀时间
	 * */
	int reduceNumber(@Param("seckillId")long seckillId, @Param("killTime")Date killTime);
	
	/**
	 * 根据id查询秒杀对象
	 * @param seckillId 商品id
	 * */
	SeckillPO queryById(@Param("seckillId")long seckillId);
	
	/**
	 * 分页查询商品
	 * @param offset 起始偏移量
	 * @param limit 偏移量
	 * */
	List<SeckillPO> queryAll(@Param("offset")int offset, @Param("limit")int limit);
}
