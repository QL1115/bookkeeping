package com.no8.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.no8.model.Record;

/**
 * 收支記錄與資料庫的連結
 * @author ql
 */
class RecordDAO {
	private static final String INSERT = 
			"INSERT INTO `income_expenditure`(`fk_user_id`, `amount`, `category_item`,"
			+ " `date`, `description`, `category`, `in_out`)"
			+ " VALUES (?,?,?,?,?,?,?)";
	public Integer insertRecord(Record record) {
		// TODO RecordDAO 新增收支記錄
		return 0;
	}
	
	private static final String QUERY_RECORDS = 
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`,"
			+ " `category`, `in_out` FROM `income_expenditure`"
			+ " WHERE fk_user_id = ?";
	public ArrayList<Record> findRecords() {
		// TODO RecordDAO 查詢多筆收支記錄
		return null;
	}
	
	private static final String QUERY_RECORD_BY_ID =
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`,"
			+ " `category`, `in_out` FROM `income_expenditure`"
			+ " WHERE id = ?";
	public Record findRecordById(Integer recordId) {
		// TODO RecordDAO 查詢單筆收支記錄
		return null;
	}
	
	private static final String QUERY_RECORDS_BY_TIME_PERIOD = 
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`,"
			+ " `category`, `in_out` FROM `income_expenditure`"
			+ " WHERE date BETWEEN ? AND ?";
	public ArrayList<Record> findRecordsByTimePeriod(LocalDate start, LocalDate end) {
		// TODO
		return null;
	}
	
	private static final String UPDARE = 
			"UPDATE `income_expenditure` SET `fk_user_id`= ?,`amount`= ?,`category_item`= ?,"
			+ " `date`= ?, `description`= ?, `category`= ?, `in_out`= ? WHERE id = ?";
	public int updateRecord(Record record) {
		// TODO RecordDAO 更新收支記錄
		return 0;
	}
	
	private static final String DELETE = "DELETE FROM `income_expenditure` WHERE id = ?";
	public int deleteRecord(Integer recordId) {
		// TODO RecordDAO 刪除收支記錄
		return 0;
	}
}
