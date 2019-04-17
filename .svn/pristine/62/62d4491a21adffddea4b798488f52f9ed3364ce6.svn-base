package com.zepal.seckill.common;

/**
 * 返回状态信息枚举
 * @author zepal
 * */
public enum EnumConstants {

	FAIL(100, "处理失败", false),
	SUCCESS(200, "成功", true),
	SYSTEM_EXCEPTION(500, "系统异常", false);
	
	private int code;
	
	private String info;
	
	private boolean status;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private EnumConstants(int code, String info, boolean status) {
		this.code = code;
		this.info = info;
		this.status = status;
	}
	
}
