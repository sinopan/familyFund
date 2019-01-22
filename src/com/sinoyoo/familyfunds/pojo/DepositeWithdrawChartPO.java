package com.sinoyoo.familyfunds.pojo;

import java.util.Date;
/**
 * 收支图表查询实体类
 * @author ASUS-LXP
 *
 */
public class DepositeWithdrawChartPO {

	private Date minDate;//时间范围
	private Date maxDate;//时间范围
	private Integer exchangeType;//支出、存入

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
