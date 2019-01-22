package com.sinoyoo.familyfunds.result;

import java.io.Serializable;
/**
 * 通用操作结果实体类
 * @author ASUS-LXP
 *
 * @param <T>
 */
public class OperationResult<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer resultCode;
	private boolean isSuccess;
	private T data;
	public OperationResult(Integer resultCode, boolean b, T data) {
		super();
		this.resultCode = resultCode;
		this.isSuccess = b;
		this.data = data;
	}
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
