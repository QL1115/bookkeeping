package com.no8.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.no8.exception.BookkeepingException;
import com.no8.model.User;
import com.no8.util.Database;

/**
 * 用戶資料與DB的交互
 * @author ql
 *
 */
class UserDAO {
	
	private static final String QUERY = 
			"SELECT `id`, `user_name`, `password` FROM `users`"
			+ " WHERE `id` = ? AND `password` = ?";
	public User signIn(User user) throws BookkeepingException {
		try(
			Connection conn = Database.getConnection();	
			PreparedStatement pstmt = conn.prepareStatement(QUERY);
		) {
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			try(
					ResultSet rs = pstmt.executeQuery();
			) {			
				User queryUser = null;
				if (rs.next()) {
					queryUser = new User();
					queryUser.setUserId(rs.getString("id"));
					queryUser.setPassword(rs.getString("password"));
					queryUser.setUserName(rs.getString("user_name"));
				}
				// 檢查 查詢到的新增的用戶
				if (queryUser != null) {					
					return queryUser;
				} else {
					throw new BookkeepingException("登錄失敗：未找到相符的用戶");
				}
			}
			
		} catch (SQLException e) {
			throw new BookkeepingException("查詢用戶失敗", e);
		} 
	}
	
	private static final String INSERT = 
			"INSERT INTO `users`(`id`, `user_name`, `password`) VALUES (?,?,?)";
	private static final String QUERY_INSERTED_USER = 
			"SELECT id, user_name, password FROM users"
			+ " WHERE id = ?";
	public User signUp(User user) throws BookkeepingException {
		try(
				Connection conn = Database.getConnection();
				PreparedStatement pstmt1 = conn.prepareStatement(INSERT);
				PreparedStatement pstmt2 = conn.prepareStatement(QUERY_INSERTED_USER);
		) {
			pstmt1.setString(1, user.getUserId());
			pstmt1.setString(2, user.getUserName());
			pstmt1.setString(3, user.getPassword());			
			int row = pstmt1.executeUpdate();
			User insertedUser = null;
			if (row == 1) {	// 成功新增一筆用戶
				pstmt2.setString(1, user.getUserId());
				try(
						ResultSet rs = pstmt2.executeQuery();
				) {
					if (rs.next()) {
						insertedUser = new User();
						insertedUser.setUserId(rs.getString("id"));
						insertedUser.setUserName(rs.getString("user_name"));
						insertedUser.setPassword(rs.getString("password"));
					}
				}
			}
			// 檢查 新增的用戶
			if (insertedUser != null) {
				System.out.println(" -> " + insertedUser);
				return insertedUser;
			} else {
				throw new BookkeepingException("新增失敗");
			}
		} catch (SQLException e) {
			throw new BookkeepingException("新增用戶失敗", e);
		}
	}
	
}
