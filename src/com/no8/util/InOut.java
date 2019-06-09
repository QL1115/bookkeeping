package com.no8.util;

import com.no8.exception.BookkeepingException;

public enum InOut {
	INCOME("收入"), EXPENDITURE("支出");
	
	
	private String name;
	private InOut(String name) {
		this.name = name;
	}
	
	// 
	public String getInOutName() {
		return name;
	}
	
	// 根據名稱轉換成InOut型別
	public static InOut switchNameToInOut(String name) throws BookkeepingException {
		if (name == null || name.trim().length() == 0) {
			throw new BookkeepingException("必須傳入欲轉換成InOut型別的名稱");
		}
		switch(name) {
			case "收入":
				return INCOME;
			case "支出":
				return EXPENDITURE;
			default: 
				throw new BookkeepingException("字符串轉換InOut型別失敗");
		}
	}
}
