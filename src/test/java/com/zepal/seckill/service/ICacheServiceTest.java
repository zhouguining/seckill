package com.zepal.seckill.service;


import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zepal.seckill.cache.ICacheService;
import com.zepal.seckill.po.SeckillPO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ICacheServiceTest {

	@Autowired
	private ICacheService iCacheService;
	
	@Test
	public void setCacheTest() {
		SeckillPO seckillPO = new SeckillPO();
		seckillPO.setName("红牛");
		seckillPO.setSeckillId(1234);
		iCacheService.setCache("seckillPO:"+seckillPO.getSeckillId(), seckillPO, 1L, TimeUnit.HOURS);
		
		System.out.println("-->处理完成");
	}
	
	@Test
	public void getCacheTest() {
		SeckillPO seckillPO = iCacheService.getCache("seckillPO:1234", SeckillPO.class);
		System.out.println(seckillPO.toString());
		System.out.println("-->处理完成");
	}
}
