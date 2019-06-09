package com.no8.model;


import com.no8.exception.BookkeepingException;

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
	public void setUserId(String userId) throws BookkeepingException {
		if (userId == null || userId.trim().length() == 0)  {
			throw new BookkeepingException("必須設置用戶ID");
		}
		String id = userId.trim();
		if (!id.matches("\\w{6,20}")) {
			throw new BookkeepingException("用戶ID格式設置不正確");
		} 
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) throws BookkeepingException {
		if (userName == null || userName.trim().length() == 0) {
			throw new BookkeepingException("必須設置用戶名");
		}
		String name = userName.trim();
		this.userName = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws BookkeepingException {
		if (password == null || password.trim().length() == 0) {
			throw new BookkeepingException("必須設置密碼");
		}
		String pwd = password.trim();
		if ( ! pwd.matches("\\w{6,20}")) {
			throw new BookkeepingException("密碼格式設置不正確");
		}
		this.password = pwd;
	}
	@Override
	public String toString() {
		return "User [用戶ID: " + userId + ", 密碼: " + password + ", 用戶名: " + userName + "]";
	}
	
	
	
}
