package com.no8.controller;

import com.no8.model.User;

/**
 * 用戶相關資料中間層
 * @author ql
 *
 */
public class UserService {
	// 代理模式
	private UserDAO dao  = new UserDAO();
	public static User loggedinUser; 	// TODO 登錄的用戶
	
	/**
	 *	用戶登錄 
	 */
	public User login(String userId, String pwd) {
		// TODO 用戶登錄 service層
		System.out.println("登錄");
		System.out.println("ID: " + userId);
		System.out.println("PWD: " + pwd);
		return null;
	}
	
	/**
	 * 用戶註冊
	 */
	public User signUp(String userId, String userName, String pwd) {
		// TODO 用戶註冊 service層
		System.out.println("註冊");
		System.out.println("ID: " + userId);
		System.out.println("PWD: " + pwd);
		System.out.println("Name: " + userName);
		return null;
	}
}
