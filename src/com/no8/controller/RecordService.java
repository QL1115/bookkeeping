 package com.no8.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.no8.exception.BookkeepingException;
import com.no8.model.Record;
import com.no8.util.CategoryItem;
import com.no8.util.InOut;

/**
 * 收支紀錄相關服務
 * @author ql
 */
public class RecordService {
	// 代理
	private RecordDAO dao = new RecordDAO();
	
	/**
	 * 新增收支記錄
	 * @return 插入的筆數
	 */
	public int insertRecord(String categoryItemName, String amountStr, 
			String dateStr, String description) throws BookkeepingException {
		if (categoryItemName == null || UserService.loggedinUser == null || amountStr == null || dateStr == null || dateStr.trim().length() == 0 ) {
			throw new BookkeepingException("欲新增的收支記錄不可為null");
		}
		//
		Record record = new Record();
		record.setUser(UserService.loggedinUser);
		CategoryItem item = CategoryItem.switchNameToItem(categoryItemName);
		record.setCategoryItem(item);
		record.setDescription(description);
		try {
			double amount = Double.parseDouble(amountStr);
			record.setAmount(amount);
		} catch (NumberFormatException e) {
			throw new BookkeepingException("金額格式不正確，金額必須為數字並且大於0元", e);
		}
		try {
			record.setDate(LocalDate.parse(dateStr));
		} catch(DateTimeParseException e) {
			throw new BookkeepingException("日期格式不正確", e);
		}
		//
		return dao.insertRecord(record);
	}
	/**
	 * 查詢用戶的所有收支記錄
	 */
	public ArrayList<Record> findRecords() throws BookkeepingException {
		return dao.findRecords();
	}
	/**
	 * 根據收支紀錄的id來查找收支紀錄
	 */
	public Record findRecordById(Integer recordId) throws BookkeepingException {
		if (recordId == null || recordId == 0) {
			throw new BookkeepingException("必須傳入欲查詢的收支記錄ID");
		}
		return dao.findRecordById(recordId);
	}
	
	public ArrayList<Record> findRecordsByTimePeriod(String startStr, String endStr) throws BookkeepingException {
		if (startStr == null || startStr.trim().length() == 0 || endStr == null || endStr.trim().length() == 0) {
			throw new BookkeepingException("必須傳入起始與結束日期");
		}
		
		try {
			LocalDate start = LocalDate.parse(startStr);
			LocalDate end = LocalDate.parse(endStr);
			if (start.isBefore(end)) {		// 起始日期必須早於結束日期
				return dao.findRecordsByTimePeriod(start, end);
			} else {
				throw new BookkeepingException("起始日期必須早於結束日期");
			}
		} catch(DateTimeParseException e) {
			throw new BookkeepingException("日期格式不正確", e);
		}
	}
	
	/**
	 * 根據「收入」或「支出」查找收支記錄
	 * @param inoutName String 型別
	 */
	public ArrayList<Record> findRecordsByInOut(String inoutName) throws BookkeepingException {
		if (inoutName == null || inoutName.trim().length() == 0) {
			throw new BookkeepingException("收入或支出字符串格式不符");
		}
		InOut inout = InOut.switchNameToInOut(inoutName.trim());
		ArrayList<Record> list = dao.findRecordsByInOut(inout);
		return list;
		
	}
	
	/**
	 * 根據「收支細項」查找收支記錄
	 * @param itemName String 型別
	 */
	public ArrayList<Record> findRecordsByCategoryItem(String itemName) throws BookkeepingException {
		if (itemName == null || itemName.trim().length() == 0) {
			throw new BookkeepingException("收支細項字符串格式不符");
		}
		CategoryItem item = CategoryItem.switchNameToItem(itemName.trim());
		ArrayList<Record> list = dao.findRecordsByCategoryItem(item);
		return list;
	}
	
	/**
	 * 更新一筆收支記錄
	 * @return 被更新的筆數
	 */
	public int updateRecordById(Integer id, Double amount, String categoryItemName, String dateStr, String desc) throws BookkeepingException {
		Record record = new Record();
		record.setId(id);
		record.setAmount(amount);
		record.setCategoryItem(CategoryItem.switchNameToItem(categoryItemName));
		record.setDescription(desc.trim());
		try {
			record.setDate(LocalDate.parse(dateStr));
		} catch(DateTimeParseException e) {
			throw new BookkeepingException("日期格式不正確", e);
		}
		return dao.updateRecord(record);
	}
	
	/**
	 * 刪除一筆收支記錄
	 * @param recordId 欲刪除的收支記錄 ID
	 * @return 被刪除的筆數
	 */
	public int deleteRecordById(Integer recordId) throws BookkeepingException {
		if (recordId == null || recordId == 0) {
			throw new BookkeepingException("必須傳入欲刪除的收支記錄ID");
		}
		return dao.deleteRecord(recordId);
	}
}
