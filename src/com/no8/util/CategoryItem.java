package com.no8.util;

import com.no8.exception.BookkeepingException;

public enum CategoryItem {
	
//	private String itemName;
//	private String categoryName;
//	
//	public CategoryItem(String categoryName, String itemName) {		
//	}
	
	FOOD(Category.DIET, "食物"),
	DRINK(Category.DIET, "飲料"),
	PART_TIME_JOB(Category.JOB, "兼職"),
	FULL_TIME_JOB(Category.JOB, "全職"),
	INTERN(Category.JOB, "實習"),
	MRT(Category.TRAFFIC, "捷運"),
	MOTORCYCLE(Category.TRAFFIC, "摩托車"),
	BUS(Category.TRAFFIC, "公車"),
	CAR(Category.TRAFFIC, "轎車"),
	BICYCLE(Category.TRAFFIC, "自行車"),
	NECESSITY(Category.SHOPPING, "生活必需品"),
	LUXURY(Category.SHOPPING, "奢侈品");
	
	
	private Category category;
	private String itemName;
	
	private CategoryItem(Category category,String itemName) {
		this.category = category;
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public static CategoryItem switchNameToItem(String itemName) throws BookkeepingException {
		if (itemName == null || itemName.trim().length() == 0) {
			throw new BookkeepingException();
		}
		switch(itemName.trim()) {
			case "食物":
				return FOOD;
			case "飲料":
				return DRINK;
			case "兼職":
				return PART_TIME_JOB;
			case "全職":
				return FULL_TIME_JOB;
			case "實習":
				return INTERN;
			case "捷運":
				return MRT;
			case "摩托車":
				return MOTORCYCLE;
			case "公車":
				return BUS;
			case "轎車":
				return CAR;
			case "自行車":
				return BICYCLE;
			case "生活必需品":
				return NECESSITY;
			case "奢侈品":
				return LUXURY;
			default: 
				throw new BookkeepingException("轉換CategoryItem型別失敗");
			
		}
	}
}
