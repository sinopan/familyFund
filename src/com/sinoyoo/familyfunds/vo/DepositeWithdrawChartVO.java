package com.sinoyoo.familyfunds.vo;

/**
 * ��֧chartͼչʾʵ���ࣺ
 * @author ASUS-LXP
 *
 */
public class DepositeWithdrawChartVO {

	private float amount;//���
	private String consumationType;//�������ͣ��Ӽҡ����Ρ�ͨѶ���ѡ�������
	private String user_id;//�û�id�����ڹ�����չ��
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
