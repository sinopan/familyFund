package com.sinoyoo.familyfunds.pojo;

import java.util.Date;
/**
 * ��֧ͼ���ѯʵ����
 * @author ASUS-LXP
 *
 */
public class DepositeWithdrawChartPO {

	private Date minDate;//ʱ�䷶Χ
	private Date maxDate;//ʱ�䷶Χ
	private Integer exchangeType;//֧��������

	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	public Integer getExchangeType() {
		return exchangeType;
	}
	public void setExchangeType(Integer exchangeType) {
		this.exchangeType = exchangeType;
	}
}
