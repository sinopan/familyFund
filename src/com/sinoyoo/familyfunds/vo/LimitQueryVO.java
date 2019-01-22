package com.sinoyoo.familyfunds.vo;
/**
 * 分页查询条件实体类(由于mybatis的Example不能分页，所以定义该类用于分页)
 * @author ASUS-LXP
 *
 */
public class LimitQueryVO {

	private Integer startRecord;
	private Integer endRecord;
	
	public Integer getStartRecord() {
		return startRecord;
	}
	public void setStartRecord(Integer startRecord) {
		this.startRecord = startRecord;
	}
	public Integer getEndRecord() {
		return endRecord;
	}
	public void setEndRecord(Integer endRecord) {
		this.endRecord = endRecord;
	}
	
}
