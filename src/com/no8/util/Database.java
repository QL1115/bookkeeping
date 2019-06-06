package com.no8.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.no8.exception.BookkeepingException;


/**
 * 連結mysql資料庫
 * @author ql
 */
public class Database {
	
	static {
		String driver, url, id, pwd;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("com.no8.util.jdbc");
			driver = bundle.getString("jdbc.driver");
			url = bundle.getString("jdbc.url");
			id = bundle.getString("jdbc.userid");
			pwd = bundle.getString("jdbc.pwd");
		} catch (Exception e) {
			driver = "com.mysql.cj.jdbc.Driver";
			url = "jdbc:mysql://IP位置/資料庫名稱?useSSL=true&characterEncoding=utf8";
			id = "用戶名";
			pwd = "密碼";
		}
		DRIVER = driver;
		URL = url;
		USER_NAME = id;
		PASSWORD = pwd;
		
	}
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USER_NAME;
	private static final String PASSWORD;
	
	public static Connection getConnection() throws BookkeepingException{
		try {
			/* 1. 載入JDBC Driver */
			Class.forName(DRIVER);
			try {
				/* 2. 獲取連線  */
				// 原則上是需要關閉的
				Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				System.out.println("連結資料庫成功 " + connection);
				return connection;
			} catch (SQLException e) {
				throw new BookkeepingException("資料庫連線失敗", e);
			}
			
		} catch (ClassNotFoundException e) {
			throw new BookkeepingException("載入JDBC Driver發生錯誤", e);
		}
	}
	
	/* 用來測試連結資料庫是否成功 */
	public static void main(String[] args) {
		try {
			Database.getConnection();
		} catch (BookkeepingException e) {
			e.printStackTrace();
		}
	}

}
