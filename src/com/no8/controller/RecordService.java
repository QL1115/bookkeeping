package com.no8.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.no8.exception.BookkeepingException;
import com.no8.model.Record;
import com.no8.util.Category;

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
	public int insertRecord(Category category, Double amount, 
			LocalDate date, String description) throws BookkeepingException {
		if (UserService.loggedinUser == null || amount == null || amount <= 0 || date == null ) {
			throw new BookkeepingException("欲新增的收支記錄不可為null");
		}
		//
		Record record = new Record();
		record.setUser(UserService.loggedinUser);
		record.setCategory(category);
		record.setAmount(amount);
		record.setDate(date);
		record.setDescription(description);
		//
		return dao.insertRecord(record);
	}
	
	public ArrayList<Record> findRecords() {
		return dao.findRecords();
	}
	
	public Record findRecordById(Integer recordId) throws BookkeepingException {
		if (recordId == null || recordId == 0) {
			throw new BookkeepingException("必須傳入欲查詢的收支記錄ID");
		}
		return dao.findRecordById(recordId);
	}
	
	public ArrayList<Record> findRecordsByTimePeriod(LocalDate start, LocalDate end) throws BookkeepingException {
		if (start == null || end == null) {
			throw new BookkeepingException("必須傳入起始與結束日期");
		}
		return dao.findRecordsByTimePeriod(start, end);
	}
	
	/**
	 * 更新一筆收支記錄
	 * @return 被更新的筆數
	 */
	public int updateRecord(Record record) throws BookkeepingException {
		if (record == null) {
			throw new BookkeepingException("欲更新的收支記錄不可為null");
		}
		
		return dao.updateRecord(record);
	}
	
	/**
	 * 刪除一筆收支記錄
	 * @param recordId 欲刪除的收支記錄 ID
	 * @return 被刪除的筆數
	 */
	public int deleteRecord(Integer recordId) throws BookkeepingException {
		if (recordId == null || recordId == 0) {
			throw new BookkeepingException("必須傳入欲刪除的收支記錄ID");
		}
		return dao.deleteRecord(recordId);
	}
}
