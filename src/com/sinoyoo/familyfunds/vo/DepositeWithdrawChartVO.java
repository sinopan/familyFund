package com.sinoyoo.familyfunds.vo;

/**
 * 收支chart图展示实体类：
 * @author ASUS-LXP
 *
 */
public class DepositeWithdrawChartVO {

	private float amount;//金额
	private String consumationType;//消费类型（居家、旅游、通讯消费。。。）
	private String user_id;//用户id（后期功能扩展）
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getConsumationType() {
		return consumationType;
	}
	public void setConsumationType(String consumationType) {
		this.consumationType = consumationType;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
