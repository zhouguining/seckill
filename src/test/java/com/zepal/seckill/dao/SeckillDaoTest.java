package com.zepal.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zepal.seckill.po.SeckillPO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillDaoTest {

	@Autowired
	private SeckillDao seckillDao;
	
	@Test
	public void queryByIdTest() {
		SeckillPO seckillPO = seckillDao.queryById(1000);
		System.out.println(seckillPO.toString());
	}
	
}
