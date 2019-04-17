package com.zepal.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zepal.seckill.po.SuccessSeckillPO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuccessSeckillDaoTest {

	@Autowired
	private SuccessSeckillDao successSeckillDao;
	
	@Test
	public void queryByIdWithSeckillTest() {
		long seckillId = 1000;
		SuccessSeckillPO successSeckillPO = successSeckillDao.queryByIdWithSeckill(seckillId, "userPhone");
		if(successSeckillPO == null) {
			System.out.println(successSeckillPO == null);
		}else {
			System.out.println(successSeckillPO.toString());
		}
	}
}
