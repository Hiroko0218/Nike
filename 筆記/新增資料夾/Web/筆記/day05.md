### 修改文章步驟:

1. 給稿件管理頁面中的三個表格里面的刪除和編輯按鈕添加點擊事件,在修改事件中跳轉到postArticle.html頁面中, 同時把需要修改的文章id傳遞到頁面中
2. 在postArticle.html頁面中的created方法里面得到傳遞過來的id, 通過id查詢文章的所有數據,把查詢到的數據顯示到頁面中
3. 在ContentController里面添加方法處理上面的請求, 創建ContentUpdateVO,  調用mapper的selectByIdForUpdate查詢方法通過id查詢詳情
4. 實現mapper里面的方法
5. 在ContentController里面的addNew方法中判斷 傳遞過來的參數中是否包含了id ,如果包含id代表是修改,否則是添加,  如果是修改,則調用mapper的update方法
6. 實現mapper中的update方法

### 刪除文章步驟:

1. 在點擊刪除按鈕時向服務發出刪除請求,把id傳遞過去
2. 在ContentController里面添加delete方法處理上面的請求, 在方法中通過id查詢到商品的信息, 得到封面路徑並刪除對應的文件,  判斷刪除的是否是視頻類型,如果是的話得到視頻路徑並刪除,最後調用mapper里面的delete方法
3. 實現mapper中的delete方法
