### 注冊功能:

- 修改reg.html頁面,點擊注冊按鈕時發出請求
- 創建User,UserVO,UserRegDTO,UserMapper.java,UserMapper.xml, UserController
- 在工程中把boot01工程里面的Security相關內容覆制到baking工程
  - 在pom.xml添加Security依賴
  - 把security包copy到新工程
  - 將SecurityConfig里面的密碼編碼器改成BCryptPasswordEncoder

### 登錄功能:

- 修改login.html頁面, 點擊登錄按鈕時發出請求
- 在UserController中添加login方法處理請求,在login方法中開啟認證流程
- 把boot01工程中的全局異常處理代碼覆制到新工程



### 自定義組件my-header

- 通過自定義組件可以將多個頁面的頂部進行重用,  這樣頂部的功能只需要寫一遍即可覆用到多個頁面
- 代碼參見my-header.js



### 個人中心頁面

- 將localStorage里面登錄成功的用戶信息 ,顯示到personal.html頁面中

- 上傳用戶頭像

  - 在personal.html頁面中的上傳組件中添加 action/name/limit三個屬性

  - 創建UploadController  添加upload 方法處理上傳文件的請求

  - 在application.properties里面添加配置信息 設置最大上傳文件的大小

  - 在upload方法中,將接收到的文件保存到和日期相關的文件夾中, 並把圖片的路徑響應給客戶端

- 刪除圖片:
  -  當點擊頁面中的刪除圖標時會觸發on-remove事件 ,調用handleRemove方法, 在方法中向服務器發出刪除請求, 同時把圖片的路徑(當圖片上傳完成時服務器給客戶端響應的圖片路徑, 此時路徑在file.response中)傳遞給服務器  
  -  在UploadController中添加remove方法處理刪除請求, 在方法中 拼接完成的文件路徑 並刪除.
