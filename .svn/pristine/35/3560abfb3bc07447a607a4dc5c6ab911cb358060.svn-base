package com.zepal.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//TODO   缓存优化
//TODO 查询库存没加锁
//TODO 更换insert和update顺序
//TODO 存储过程

@Controller
public class TestController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * json测试
	 * */
	@RequestMapping("/test")
	@ResponseBody
	public String jsonTest() {
		System.out.println("-->请求test");
		return "success!";
	}
	
	/**
	 * 页面测试
	 * */
	@RequestMapping("/thymeleaf")
	public String thymeleafTest() {
		return "test";
	}
	
	/**
	 * redis测试
	 * */
	@RequestMapping("/redistest")
	@ResponseBody
	public String redisTest() {
		stringRedisTemplate.opsForValue().set("mykey", "redis_test");
		return "success";
	}
	
}
