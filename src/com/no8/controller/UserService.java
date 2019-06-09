package com.no8.controller;

import com.no8.exception.BookkeepingException;
import com.no8.model.User;

/**
 * 用戶相關資料中間層
 * @author ql
 */
public class UserService {
	// 代理模式
	private UserDAO dao  = new UserDAO();
	public static User loggedinUser; 	// 登錄的用戶
	
	/**
	 *	用戶登錄 
	 */
	public User signIn(String userId, String pwd) throws BookkeepingException {
		if (userId == null || userId.trim().length() == 0 || pwd == null || pwd.trim().length() == 0) {
			throw new BookkeepingException("登錄失敗：ID或密碼錯誤");
		}
		//
		User user = new User();
		user.setUserId(userId);
		user.setPassword(pwd);
		//
		UserService.loggedinUser = dao.signIn(user);
		return UserService.loggedinUser;
		
	}
	
	/**
	 * 用戶註冊
	 */
	public User signUp(String userId, String userName, String pwd) throws BookkeepingException {
		if (userId == null || userId.trim().length() == 0 || userName == null 
			|| userName.trim().length() == 0 || pwd == null || pwd.trim().length() == 0) {
			throw new BookkeepingException("註冊失敗：資料填寫不完整");
		}
		//
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(pwd);
		//
		UserService.loggedinUser = dao.signUp(user);
		return UserService.loggedinUser;
	}
}
