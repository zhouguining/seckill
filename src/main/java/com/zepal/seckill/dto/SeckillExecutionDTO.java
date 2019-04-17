package com.zepal.seckill.dto;

import java.io.Serializable;

import com.zepal.seckill.po.SuccessSeckillPO;

/**
 * <p> 封装秒杀执行后的结果
 * */
public class SeckillExecutionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**秒杀id**/
	private long seckillId;
	
	/**秒杀状态**/
	private int state;
	
	/**秒杀状态标识**/
	private String stateInfo;
	
	/**秒杀成功对象**/
	private SuccessSeckillPO successSeckillPO;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessSeckillPO getSuccessSeckillPO() {
		return successSeckillPO;
	}

	public void setSuccessSeckillPO(SuccessSeckillPO successSeckillPO) {
		this.successSeckillPO = successSeckillPO;
	}

	public SeckillExecutionDTO() {
		super();
	}

	public SeckillExecutionDTO(long seckillId, int state, String stateInfo, SuccessSeckillPO successSeckillPO) {
		super();
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
		this.successSeckillPO = successSeckillPO;
	}

	@Override
	public String toString() {
		return "SeckillExecutionDTO [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successSeckillPO=" + successSeckillPO + "]";
	}
}
