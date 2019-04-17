package com.zepal.seckill.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  <p> 缓存异常处理
 *  <p> 缓存是一种辅助手段,缓存异常(比如连接异常等)不能影响正常业务
 * */
@Configuration
public class CacheAutoConfiguration extends CachingConfigurerSupport {

	private static final Logger logger = LoggerFactory.getLogger(CacheAutoConfiguration.class);
	
	/**
	 * redis异常后这里不作任何处理,直接放行，打印日志
	 * */
	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			
			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				logger.error("redis异常 : key=[{}]", key, exception);
			}
			
			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				logger.error("redis异常 : key=[{}]", key, exception);
			}
			
			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				logger.error("redis异常 : key=[{}]", key, exception);
			}

			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				logger.error("redis异常 ", exception);
			}
			
		};
		
		return cacheErrorHandler;
	}

	
}
