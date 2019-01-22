package com.sinoyoo.familyfunds.result;

import java.io.Serializable;
/**
 * 小程序登陆结果类
 * @author ASUS-LXP
 *
 */

import com.sinoyoo.familyfunds.pojo.User;
public class LoginResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2934362573695124231L;
	
	private boolean isSuccess;
	private User user;
	private String jsessionId;
	public LoginResult(boolean isSuccess, User user,String jsessionId) {
		super();
		this.isSuccess = isSuccess;
		this.user = user;
		this.jsessionId = jsessionId;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getJsessionId() {
		return jsessionId;
	}
	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}
	
}
