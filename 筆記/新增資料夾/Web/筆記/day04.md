### 修改用戶信息

1. 上傳組件中添加on-success="handleSuccess"  , 這樣當上傳完成之後會調用handleSuccess方法, 方法中得到上傳的圖片路徑
2. 給保存按鈕添加點擊事件, 調用save方法,  在方法中 準備一個user對象里面裝著需要修改的昵稱/圖片路徑和id ,  向服務器發出修改請求
3. 在UserController中添加upate方法處理請求,  在方法中通過傳遞過來的信息判斷是否需要修改圖片, 如果需要修改先查詢到之前的圖片路徑 刪除圖片,   最後調用mapper的update方法進行修改
4. 實現mapper中的update方法
5. 在application.properties里面設置d:/files文件夾為靜態資源文件夾, 這樣才能夠讓客戶端訪問到上傳的圖片

### 發布文章

1. 在稿件管理頁面中,點擊三種不同的發布按鈕跳轉頁面時傳遞type參數
2. 在postArticle.html發布頁面中的created方法里面 得到傳遞過來的type值, 並讓頁面中的單選組件和type進行雙向綁定 
3. 在created方法中向服務器發請求獲取type值對應的二級分類數據, 獲取到數據後賦值給categoryArr數組,讓頁面中的選擇器組件和數組進行綁定   
4. 給頁面中的類型單選組件添加input事件, 當值發生改變時發請求獲取對應分類的數據
5. 讓頁面中的二級分類選擇器組件進行雙向綁定
6. 讓富文本編輯器和vue對象中的c.content進行關聯 
7. 實現上傳封面的功能
8. 給上傳按鈕添加點擊事件, 調用post方法
9. 在post方法中 判斷是否選擇了二級分類, 是否選擇了封面  然後向服務器發出請求, 並將和頁面綁定的c對象提交給服務器
10. 創建ContentController 里面添加addNew方法 處理請求,  在方法中調用mapper的insert方法把數據保存到數據庫
11. 實現mapper里面的方法
12. 實現發布視頻功能   

### 稿件管理頁面

1. 將articleManagement.html頁面中三個表格對應的數組contentArr 改成三個不同的數組
2. 添加created方法,在里面發請求獲取三種不同類型的數據,並把得到的數據賦值給三個數組
3. 在ContentController中添加方法 處理上面的請求, 然後調用mapper的selectByType方法,把接收到的type和當前登錄的用戶id傳遞到方法中
4. 實現mapper中的selectByType方法  配置xml里面的SQL語句 (內容表和分類表關聯查詢)
