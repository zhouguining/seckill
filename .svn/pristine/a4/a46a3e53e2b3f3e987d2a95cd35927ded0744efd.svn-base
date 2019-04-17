package com.zepal.seckill.cache;

import java.util.concurrent.TimeUnit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.zepal.seckill.exception.CacheException;
import com.zepal.seckill.util.SerializationUtils;

/**
 * <p> 将缓存注册为服务，方便维护
 * @author zepal
 * */
@Service
public class CacheServiceImpl implements ICacheService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, byte[]> redisTemplate;
	
	@Override
	public void setCache(String key, String value, long timeOut, TimeUnit timeUnit) {
		stringRedisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
		
	}

	@Override
	public String getCache(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public <V, K> String getCache(K key, Closure<V, K> closure) {
		return doGetCache(key, closure, 1L, TimeUnit.HOURS);
	}

	@Override
	public <V, K> String getCache(K key, Closure<V, K> closure, long timeOut, TimeUnit timeUnit) {
		return doGetCache(key, closure, timeOut, timeUnit);
	}

	@Override
	public void deleteCache(String key) throws CacheException {
		Boolean flag = stringRedisTemplate.delete(key);
		if(!flag) {
			throw new CacheException("缓存key：" + key + "删除失败");
		}
	}
	
	private <K, V> String doGetCache(K key, Closure<V, K> closure,
			long timeOut, TimeUnit timeUnit) {
		String result = getCache(key.toString());
		if(result == null) {
			Object obj = closure.execute(key);
			setCache(key.toString(), obj.toString(), timeOut, timeUnit);
			return obj.toString();
		}
		return result;
	}

	@Override
	public void setCache(String key, Object value, long timeOut, TimeUnit timeUnit) {
		byte[] bytes = SerializationUtils.serialize(value);
		redisTemplate.opsForValue().set(key, bytes, timeOut, timeUnit);
	}

	@Override
	public <T> T getCache(String key, Class<T> clazz) {
		byte[] bs = redisTemplate.opsForValue().get(key);
		if(bs == null) {
			return null;
		}
		return SerializationUtils.deserialize(bs, clazz);
	}

}
