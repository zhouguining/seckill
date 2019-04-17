package com.zepal.seckill.cache;

/**
 * 方法回调接口
 * */
public interface Closure<O, I> {

	public O execute(I input);
}
