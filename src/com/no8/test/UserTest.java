package com.no8.test;

import com.no8.controller.UserService;
import com.no8.exception.BookkeepingException;
import com.no8.model.User;

public class UserTest {	
	public static void main(String[] args) {
		UserService service = new UserService();
		try {
			// 用戶新增
			User user = service.signUp("lisi", "李四34", "1234");
			System.out.println(user);
			System.out.println("目前登錄的用戶： " + UserService.loggedinUser.getUserName());
			
			System.out.println();
			// 用戶登入
			User user2 = service.signIn("daniel123", "123daniel");
			System.out.println(user2);
			System.out.println("目前登錄的用戶： " + UserService.loggedinUser.getUserName());
			
		} catch (BookkeepingException e) {
			e.printStackTrace();
		}
	}
}
