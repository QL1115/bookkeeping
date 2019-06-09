package com.no8.test;

import java.util.ArrayList;

import com.no8.controller.RecordService;
import com.no8.controller.UserService;
import com.no8.exception.BookkeepingException;
import com.no8.model.Record;

public class RecordTest {
	public static void main(String[] args) {
		UserService userService = new UserService();
		RecordService recordService = new RecordService();
		try {
			/* 【前提】用戶必須先登入 */
			userService.signIn("daniel123", "123daniel");
			
			/* 新增 一筆收支記錄 */
//			int row = recordService.insertRecord("食物","140","2019-04-22",""); // 日期需符合 ISO-8601 的格式
//			System.out.println("新增收支的筆數： " + row);
			
			/* 根據id查詢一筆收支記錄 */
//			Record record = recordService.findRecordById(6);
//			System.out.println(record);
			
			/* 查詢一個用戶的所有收支記錄 */
//			ArrayList<Record> list = recordService.findRecords();
//			for (Record record : list) {
//				System.out.println(record);
//			}
			
			/* 查詢一個時間段的收支記錄 */
//			ArrayList<Record> list = recordService.findRecordsByTimePeriod("2019-04-01", "2019-06-09");
//			for (Record record : list) {
//				System.out.println(record);
//			}
			
			/* 依據「收入」或「支出」查詢收支記錄 */
//			ArrayList<Record> list = recordService.findRecordsByInOut("收入");
//			for (Record record : list) {
//				System.out.println(record.getCategoryItem().getCategory().getInOut().getInOutName() + " : " + record);
//			}
			
			/* 依據「收支細項」查詢收支記錄 */
//			ArrayList<Record> list = recordService.findRecordsByCategoryItem("生活必需品");
//			for (Record record : list) {
//				System.out.println(record.getCategoryItem().getItemName() + " : " + record);
//			}
			
			/* 更新一筆收支記錄 */
//			// 1. 先得到一筆收支記錄
//			Record record = recordService.findRecordById(7);
//			System.out.println("更新之前： " + record);
//			// 2. 再更新得到的收支紀錄
//			recordService.updateRecord(record.getId(), 2000.0, "捷運", "2019-04-01", "整個四月花費的捷運交通費");
//			Record newRecord = recordService.findRecordById(7);
//			System.out.println("更新之後： " + newRecord);	// 再從資料庫撈一次
			
			/* 刪除一筆收支記錄 */
//			int row = recordService.deleteRecordById(12);
//			System.out.println("刪除的筆數： " + row);
			
		} catch (BookkeepingException e) {
			e.printStackTrace();
		}
		
	}
}
