package com.zepal.seckill.po;

import java.io.Serializable;
import java.util.Date;


public class SuccessSeckillPO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long seckillId;
	
	private String userPhone;
	
	private int state;
	
	private Date createTime;
	
	private SeckillPO seckillPO;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SeckillPO getSeckillPO() {
		return seckillPO;
	}

	public void setSeckillPO(SeckillPO seckillPO) {
		this.seckillPO = seckillPO;
	}

	public SuccessSeckillPO() {
		super();
	}

	public SuccessSeckillPO(long seckillId, String userPhone, int state, Date createTime, SeckillPO seckillPO) {
		super();
		this.seckillId = seckillId;
		this.userPhone = userPhone;
		this.state = state;
		this.createTime = createTime;
		this.seckillPO = seckillPO;
	}

	@Override
	public String toString() {
		return "SucessSeckillPO [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", createTime=" + createTime + ", seckillPO=" + seckillPO + "]";
	}
	
}
