package com.zepal.seckill.exception;

/**
 * <p>自定义缓存异常
 * */
public class CacheException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

	public CacheException(String message) {
		super(message);
	}

	
}
