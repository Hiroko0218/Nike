### 1. 前後端聯調（API文檔）

### 2. Lombok

* 自動生成 `@Setter() @Getter() @ToString() @AllArgsConstructor @NoArgsConstructor @Data` ，簡化工程代碼，提高開發效率

* 日志注解：`@Slf4j` ，根據實際需求指定多種日志級別，既可以幫助開發者調試，又可以節約系統資源

  TRACE   <  DEBUG  <  INFO[默認級別] < WARN < ERROR



* 生成API文檔
* 在線調試接口（**Controller中方法**）



1. pom.xml中添加Knife4j依賴，刷新maven
2. 創建配置文件 Knife4jConfig，指定生成API文檔的包路徑
3. 重啟工程
4. 瀏覽器訪問：http://localhost:8080/doc.html





* 確認所有的DTO中，是否添加 `@ApiModelProperty()` 注解
* 確認所有的控制器方法中，是否添加 `@ApiOperation()` 注解
* 確認所有控制器類中，是否添加 `@Api(tags="")` 注解
* 確認微博模塊和評論模塊中，聲明參數接收的方法，是否添加 `@ApiImplicitParam()` 或 `@ApiImplicitParams(value = {})` 注解



* 用戶模塊：3個功能  `@ApiIgore` 注解

* 微博模塊：發布微博

* 評論模塊：發布評論

  重啟工程，Knife4j頁面確認





## 電商網站

* 用戶模塊：`10100 ~ 10199`
* 商品模塊：`10200 ~ 10299`
* 購物車模塊：`10300 ~ 10399`
* 訂單模塊：`10400 ~ 10499`
* 支付模塊：`10500 ~ 10599`





* 封裝 JsonResult，定義相關注解和構造方法；
* 修改注冊功能返回值，並重啟工程；
* 刷新Knife4j頁面測試；
* 修改登錄功能再次測試；



* 首先實現注冊功能 JsonResult 封裝
* 然後實現登錄功能 JsonResult 封裝
* 最後思考 獲取當前用戶功能 如何實現？





* 把微博模塊所有控制器方法，統一封裝返回；
* 把評論模塊所有控制器方法，統一封裝返回；







* 創建工程，並做好相關配置後啟動工程

* 實現用戶模塊的：注冊、登錄、獲取當前用戶功能

* POJO類使用：Lombok

* 日志級別要求：

  * 全局：WARN
  * 工程目錄：DEBUG

  調試過程中不能使用打印，只能使用 log.debug("xxxx")；

* 配置Knife4j，進行測試（做常規的注解配置）

* 統一響應結果的封裝（自定義枚舉狀態碼、定義靜態方法 - 有數據返回和無數據返回）
