package com.zepal.seckill.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回信息封装
 * @author zepal
 * */
public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private String message;
	
	private boolean status;
	
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public static Msg success(EnumConstants enumConstants) {
		Msg result = new Msg();
		result.setCode(enumConstants.getCode());
		result.setMessage(enumConstants.getInfo());
		result.setStatus(enumConstants.getStatus());
		return result;
	}

	public static Msg fail(EnumConstants enumConstants) {
		Msg result = new Msg();
		result.setCode(enumConstants.getCode());
		result.setMessage(enumConstants.getInfo());
		result.setStatus(enumConstants.getStatus());
		return result;
	}
	
	public Msg add(String key, Object value) {
		this.getData().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
	
}
