# bookkeeping
第八組——記帳本


## 啟動
1. 請自行使用第八組的ID及Password配置好 `util/jdbc.properties` 的三個屬性
   - `jdbc.url屬性`：
      - 配置好IP位置及資料庫名稱
      - 在後面加上 `?useSSL=true&characterEncoding=utf8` 確保使用SSL加密連線以及utf8編碼
   - `jdbc.userid`：使用者名稱
   - `jdbc.pwd`：密碼
2. **`src/MainTester.java` 為專案啟動的類別**


## 專案結構
### package 說明
- `controller` 放置與Service及SQL相關的code
- `model` 放置實體類別
- `view` 放置視窗畫面
- `exception` 自定義異常處理，將所有異常統一包裝成自己的異常
- `util` 可放置實用的工具類
### class 說明
- `model/User.java` 用戶實體
- `model/Record.java` 收支記錄實體
- `controller/UserService.java` 有關用戶信息的服務類，eg. 登錄、註冊等等功能
- `controller/UserDAO.java` 有關用戶信息的SQL語句
- `controller/RecordService.java` 有關收支記錄的服務類，eg. 新增收支、查詢收支、刪除收支等等功能
- `controller/RecordDAO.java` 有關收支紀錄的SQL語句
- `util/Database.java` 處理資料庫連線
- `util/Category.java` 收支項目的分類的ENUM
- `util/InOut.java` 區分是收入或是支出的ENUM
- `util/jdbc.properties` 配置資料庫連線的info
### 測試類 說明
- `test/` 此 package 下的各個 class 是用來測試各個單一功能
- `src/Tester.java` 可不用理
- `src/MainTester.java` 專案的進入點

## 注意事項