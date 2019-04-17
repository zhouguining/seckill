package com.zepal.seckill.cache;

import java.util.concurrent.TimeUnit;

import com.zepal.seckill.exception.CacheException;

public interface ICacheService {

	/**
	 * @see 设置有超时的缓存
	 * @param key 缓存key
	 * @param value 缓存value
	 * @param timeOut 超时时间
	 * @param timeUnit 超时时间单位
	 * */
	void setCache(String key, String value, long timeOut, TimeUnit timeUnit);
	
	/**
	 * <p> 设置有超时时间的对象缓存
	 * @param key 缓存key
	 * @param value 缓存value Object
	 * @param timeOut 超时时间
	 * @param timeUnit 超时时间单位
	 * */
	void setCache(String key, Object value, long timeOut, TimeUnit timeUnit);
	/**
	 * @see 获取缓存
	 * @param key 缓存key
	 * */
	String getCache(String key);
	
	<T> T getCache(String key, Class<T> clazz);
	
	<V, K> String getCache(K key, Closure<V, K> closure);
	
	<V, K> String getCache(K key, Closure<V, K> closure,
			long timeOut, TimeUnit timeUnit);
	
	/**
	 * @see 删除缓存
	 * @param key 缓存key
	 * */
	void deleteCache(String key) throws CacheException;
	
	
}
