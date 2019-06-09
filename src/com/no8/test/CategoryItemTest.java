package com.no8.test;

import com.no8.controller.RecordService;
import com.no8.controller.UserService;
import com.no8.exception.BookkeepingException;
import com.no8.model.Record;

public class CategoryItemTest {
	public static void main(String[] args) {
		UserService userService = new UserService();
		RecordService recordService = new RecordService();
		try {
			/* 用戶先登錄 */
			userService.signIn("zhangsan234", "4565345");
			// 
			
			
			
		} catch (BookkeepingException e) {
			e.printStackTrace();
		}
	}
	
}
