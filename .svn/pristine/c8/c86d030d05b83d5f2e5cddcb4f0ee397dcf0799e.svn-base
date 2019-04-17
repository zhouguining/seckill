package com.zepal.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JsonConfig {

	/**
	 *  因为懒加载返回前端json问题
	 * */
	@Bean(name="jacksonObjectMapper")
	public CustomMapper initCustomMapper() {
		return new CustomMapper();
	}
	
	/**
	 * 因为懒加载返回前端json问题
	 * */
	@Bean
	public MappingJackson2HttpMessageConverter initMappingJackson2HttpMessageConverter(CustomMapper jacksonObjectMapper) {
		return new MappingJackson2HttpMessageConverter(jacksonObjectMapper);
	}
}
