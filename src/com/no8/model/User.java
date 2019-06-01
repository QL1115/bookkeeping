package com.no8.model;

/**
 * 用戶實體
 * @author ql
 *
 */
public class User {
	
	private String userId;
	private String password;
	private String userName;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		// TODO 做用戶ID格式檢查
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		// TODO 做用戶名稱格式檢查
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		// TODO 做密碼格式檢查
		this.password = password;
	}
	
}
