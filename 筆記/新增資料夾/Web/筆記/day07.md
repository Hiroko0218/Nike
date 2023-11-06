### 內容列表頁面

1. 首頁點擊查看更多按鈕時跳轉到contentList.html頁面,同時將分類的id傳遞過去
2. 在contentList.html頁面的created方法中得到傳遞過來的type ,然後發請求獲取此type對應的數據
3. 在CommentController中添加selectList方法處理上面的請求, 方法中調用mapper的selectListByType方法
4. 實現mapper中的方法
   

### 搜索功能

1. 在my-header.js中 讓文本框和wd變量進行雙向綁定, 給搜索按鈕添加點擊事件, 點擊時跳轉到contentList.html頁面中, 同時將wd傳遞過去
2. 在contentList.html頁面的created方法中得到傳遞過來的wd, 然後請求和此wd相關的數據
3. 在ContentController中添加search方法 方法中調用mapper的selectByWd方法 
4. 在mapper中實現方法, 配置SQL語句時 用到CONCAT()函數 進行拼接
   

### 發評論

1. 在detail.html詳情頁面中給發布按鈕添加點擊事件, 讓文本框和變量進行雙向綁定,點擊時向服務器發出添加評論的請求
2. 創建CommentController 添加addNew方法, 在方法中判斷是否登錄,   然後調用mapper的insert方法
3. 在mapper中實現insert方法
   

### 評論列表

1. 詳情頁面的created方法中 發請求獲取當前文章的所有評論
2. CommentController里面處理請求, 調用mapper的selectByContentId
3. mapper中實現方法
4. 在頁面中讓元素和請求到的數組進行綁定
   

### 後台管理用戶列表

1. 在admin.html頁面的created方法里面發請求獲取用戶數據, 讓表格和數據進行綁定
2. 在UserController中處理上面的請求
3. 修改是否是管理員, 給el-switch控件添加了change事件, 在事件方法中發出修改管理員狀態的請求
4. 在UserContentController里面處理上面的請求
5. 刪除用戶
   

### 後台管理頁面-輪播圖

1. 在admin.html頁面中的created方法請求所有輪播圖數據
2. 在BannerController里面處理上面的請求
3. 實現刪除輪播圖功能



### 任務:實現後台管理頁面- 食譜/影片/資訊

1. 在點擊導航菜單中的食譜/影片/資訊時, 調用了handleSelect方法, 在方法中根據點擊的類型請求對應的數據


2. ContentController里面處理上面的請求


3. 修改功能:和稿件管理頁面中的修改功能一致
4. 刪除功能
