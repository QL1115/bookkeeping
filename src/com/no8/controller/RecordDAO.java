package com.no8.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.no8.exception.BookkeepingException;
import com.no8.model.Record;
import com.no8.model.User;
import com.no8.util.CategoryItem;
import com.no8.util.Database;
import com.no8.util.InOut;

/**
 * 收支記錄與資料庫的連結
 * @author ql
 */
class RecordDAO {
	private static final String INSERT = 
			"INSERT INTO `income_expenditure`(`fk_user_id`, `amount`, `category_item`,"
			+ " `date`, `description`, `in_out`)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	public Integer insertRecord(Record record) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(INSERT);
		) {
			pstmt.setString(1, record.getUser().getUserId());
			pstmt.setDouble(2, record.getAmount());
			pstmt.setString(3, record.getCategoryItem().getItemName());
			pstmt.setDate(4, Date.valueOf(record.getDate()));	//
			pstmt.setString(5, record.getDescription());
			pstmt.setString(6, record.getCategoryItem().getCategory().getInOut().getInOutName());	// 收入或支出
			
			int row = pstmt.executeUpdate();
			return row;
			
		} catch (SQLException e) {
			throw new BookkeepingException("新增收支記錄失敗", e);
		}
	}
	
	private static final String QUERY_RECORDS = 
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`"
			+ " FROM `income_expenditure`"
			+ " WHERE fk_user_id = ?";
	public ArrayList<Record> findRecords() throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(QUERY_RECORDS);
		){
			pstmt.setString(1, UserService.loggedinUser.getUserId());
			try(
					ResultSet rs = pstmt.executeQuery();
			) {
				ArrayList<Record> list = new ArrayList<>();
				while(rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt("id"));
					record.setUser(UserService.loggedinUser);
					record.setAmount(rs.getDouble("amount"));
					record.setCategoryItem(CategoryItem.switchNameToItem(rs.getString("category_item")));
					record.setDate(rs.getDate("date").toLocalDate());	//
					record.setDescription(rs.getString("description"));
					//
					list.add(record);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new BookkeepingException("查詢多筆收支記錄失敗", e);
		}
	}
	
	private static final String QUERY_RECORD_BY_ID =
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`"
			+ " FROM `income_expenditure`"
			+ " WHERE id = ? AND fk_user_id = ?";
	public Record findRecordById(Integer recordId) throws BookkeepingException {
		try(
			Connection conn = Database.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(QUERY_RECORD_BY_ID);
		) {
			pstmt.setInt(1, recordId);
			pstmt.setString(2, UserService.loggedinUser.getUserId()); // 確保查詢的是登錄用戶的收支記錄
			try(
					ResultSet rs = pstmt.executeQuery();
			) {
				Record record = null;
				if (rs.next()) {
					record = new Record();
					record.setId(rs.getInt("id"));
					record.setUser(UserService.loggedinUser);
					record.setAmount(rs.getDouble("amount"));
					record.setCategoryItem(CategoryItem.switchNameToItem(rs.getString("category_item")));
					record.setDate(rs.getDate("date").toLocalDate());
					record.setDescription(rs.getString("description"));
				}
				return record;
			}
		} catch (SQLException e) {
			throw new BookkeepingException("依據收支記錄id查詢失敗", e);
		}
	}
	
	private static final String QUERY_RECORDS_BY_TIME_PERIOD = 
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`"
			+ " FROM `income_expenditure`"
			+ " WHERE date BETWEEN ? AND ? AND fk_user_id = ?";
	public ArrayList<Record> findRecordsByTimePeriod(LocalDate start, LocalDate end) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(QUERY_RECORDS_BY_TIME_PERIOD);
		) {
			pstmt.setDate(1, Date.valueOf(start));
			pstmt.setDate(2, Date.valueOf(end));
			pstmt.setString(3, UserService.loggedinUser.getUserId());	// 確保查詢的是登錄用戶的收支記錄
			try(
					ResultSet rs = pstmt.executeQuery();
			) {
				ArrayList<Record> list = new ArrayList<>();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt("id"));
					record.setUser(UserService.loggedinUser);
					record.setAmount(rs.getDouble("amount"));
					record.setCategoryItem(CategoryItem.switchNameToItem(rs.getString("category_item")));
					record.setDate(rs.getDate("date").toLocalDate());
					record.setDescription(rs.getString("description"));
					//
					list.add(record);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new BookkeepingException("依據時間段查詢收支記錄失敗", e);
		}
	}
	
	
	private static final String FIND_RECORDS_BY_INOUT = 
		"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`"
		+ " FROM `income_expenditure` WHERE in_out = ? AND fk_user_id = ?";
	public ArrayList<Record> findRecordsByInOut(InOut inout) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_RECORDS_BY_INOUT);
		) {
			pstmt.setString(1, inout.getInOutName());
			pstmt.setString(2, UserService.loggedinUser.getUserId());
			try(
					ResultSet rs = pstmt.executeQuery();
			) {
				ArrayList<Record> list = new ArrayList<Record>();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt("id"));
					record.setUser(UserService.loggedinUser);
					record.setAmount(rs.getDouble("amount"));
					record.setCategoryItem(CategoryItem.switchNameToItem(rs.getString("category_item")));
					record.setDate(rs.getDate("date").toLocalDate());
					record.setDescription(rs.getString("description"));
					//
					list.add(record);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new BookkeepingException("根據收入或支出查詢收支記錄失敗", e);
		}
	}
	
	private static final String FIND_RECORDS_BY_CATEGORY_ITEM = 
			"SELECT `id`, `fk_user_id`, `amount`, `category_item`, `date`, `description`"
			+ " FROM `income_expenditure` WHERE category_item = ? AND fk_user_id = ?";
	public ArrayList<Record> findRecordsByCategoryItem(CategoryItem item) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(FIND_RECORDS_BY_CATEGORY_ITEM);
		) {
			pstmt.setString(1, item.getItemName());
			pstmt.setString(2, UserService.loggedinUser.getUserId());
			try(
					ResultSet rs = pstmt.executeQuery();
			) {
				ArrayList<Record> list = new ArrayList<Record>();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt("id"));
					record.setUser(UserService.loggedinUser);
					record.setAmount(rs.getDouble("amount"));
					record.setCategoryItem(CategoryItem.switchNameToItem(rs.getString("category_item")));
					record.setDate(rs.getDate("date").toLocalDate());
					record.setDescription(rs.getString("description"));
					//
					list.add(record);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new BookkeepingException("根據收支細項查詢收支記錄失敗", e);
		}
	}
	
	private static final String UPDARE = 
			"UPDATE `income_expenditure` SET `amount`= ?,`category_item`= ?,"
			+ " `date`= ?, `description`= ?, `in_out` = ? WHERE id = ? AND fk_user_id = ?";
	public int updateRecord(Record record) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(UPDARE);
		) {
			pstmt.setDouble(1, record.getAmount());
			pstmt.setString(2, record.getCategoryItem().getItemName());
			pstmt.setDate(3, Date.valueOf(record.getDate()));
			pstmt.setString(4, record.getDescription());
			pstmt.setString(5, record.getCategoryItem().getCategory().getInOut().getInOutName());	// 收入或支出
			pstmt.setInt(6, record.getId());
			pstmt.setString(7, UserService.loggedinUser.getUserId());	// 確保查詢的是登錄用戶的收支記錄
			//
			int row = pstmt.executeUpdate();
			return row;
			
		} catch (SQLException e) {
			throw new BookkeepingException("更新收支記錄失敗", e);
		}
	}
	
	private static final String DELETE = "DELETE FROM `income_expenditure` WHERE id = ? AND fk_user_id = ?";
	public int deleteRecord(Integer recordId) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE);
		) {
			pstmt.setInt(1, recordId);
			pstmt.setString(2, UserService.loggedinUser.getUserId());	// 確保查詢的是登錄用戶的收支記錄
			int row = pstmt.executeUpdate();
			return row;
		} catch (SQLException e) {
			throw new BookkeepingException("刪除收支記錄失敗", e);
		}
	}

}
