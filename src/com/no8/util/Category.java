package com.no8.util;

import com.no8.exception.BookkeepingException;
/**
 * 收支項目
 * @author ql
 *
 */
public enum Category {
//	DIET(InOut.EXPENDITURE, "飲食支出", "食物", "飲料"), 
//	JOB(InOut.INCOME, "工作收入","打工", "兼職", "全職"), 
//	TRAFFIC(InOut.EXPENDITURE, "交通支出", "捷運", "機車", "公車", "自行車", "轎車"),
//	SHOPPING(InOut.EXPENDITURE, "購物支出", "日常用品", "奢侈品");
	DIET(InOut.EXPENDITURE, "飲食支出"), 
	JOB(InOut.INCOME, "工作收入"), 
	TRAFFIC(InOut.EXPENDITURE, "交通支出"),
	SHOPPING(InOut.EXPENDITURE, "購物支出");
	
	private InOut inout;
	private String categoryName;			// 收支項目的名稱
//	private ArrayList<CategoryItem> itemList;		// 收支項目的細項
	
//	private Category(InOut inout, String categoryName, String... items) {
//		this.inout = inout;
//		this.categoryName = categoryName;
//		itemList = new ArrayList<String>();
//		for (String item : items) {
//			itemList.add(item);
//		}
//	}
	
	private Category(InOut inout, String categoryName) {
		this.inout = inout;
		this.categoryName = categoryName;

	}
	
	// 取得收支項目名稱
	public String getCategoryName() {
		return categoryName;
	}
	
	// 查看是收入或是支出
	public String getInOut() {
		return inout.getName();
	}
	
	// 有項目名稱(String)轉成項目(Enum)
	public static Category nameToCategory(String name) throws BookkeepingException {
		if (name == null || name.trim().length() == 0) {
			throw new BookkeepingException("必須傳入收支項目的名稱");
		}
		switch(name.trim()) {
			case "飲食支出": 
				return DIET;
			case "工作收入":
				return JOB;
			case "交通支出":
				return TRAFFIC;
			case "購物支出":
				return SHOPPING;
			default: 
				throw new BookkeepingException("轉換Category型別失敗");
		} 
	}
	
}
