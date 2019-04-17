package com.zepal.seckill.exception;

/**
 * 重复秒杀异常
 * */
public class RepeatKillException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RepeatKillException(String message) {
		super(message);
	}
	
	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
