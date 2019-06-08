package com.no8.model;

import java.time.LocalDate;

import com.no8.util.CategoryItem;
/**
 * 收支記錄
 * @author ql
 */
public class Record {
	private Integer id;
	private User user;
	private CategoryItem categoryItem;
	private Double amount;
	private LocalDate date;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CategoryItem getCategoryItem() {
		return categoryItem;
	}
	public void setCategoryItem(CategoryItem categoryItem) {
		this.categoryItem = categoryItem;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", 用戶=" + user + ", 收支細項=" + categoryItem + ", 金額=" + amount
				+ ", 日期=" + date + ", 備註=" + description + "]";
	}
	
	
	
}
