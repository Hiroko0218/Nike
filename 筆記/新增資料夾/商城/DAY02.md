# 3. Spring Boot框架

## 3.1. Spring Boot框架的作用

Spring Boot框架主要解決了：依賴管理，自動配置

Spring Boot被設計為“開箱即用”的，它是一種“約定大於配置”的思想。

## 3.2. 依賴管理

在開發實踐中，需要使用到的依賴項很多，而且，添加的某個依賴項可能還依賴了其它依賴項，例如，當添加`spring-webmvc`時，`spring-webmvc`還依賴了`spring-context`，另外，再添加`spring-jdbc`時，`spring-jdbc`也會依賴`spring-context`，如果`spring-webmvc`和`spring-jdbc`依賴的`spring-context`的版本並不相同，則項目是不可用的。

所以，眾多依賴項必須是協調的，版本應該兼容且不沖突，Spring Boot項目都使用了`spring-boot-starter-parent`作為父級項目，這個父級項目就管理了許多依賴項的版本，所以，在`pom.xml`中添加依賴時，某些依賴項是不需要寫版本號的，並且，它提供大量了`spring-boot-starter-???`的依賴項，例如`spring-boot-starter-web`，這些依賴項會將相關的一組依賴整合在一起，開發者添加依賴時更加方便！

## 3.3. 自動配置

在`spring-boot-starter`依賴項中包含了`spring-boot-autoconfigure`，這個`spring-boot-autoconfigure`就是用於實現自動配置的，其中包含了大量的、預編寫的自動配置類，這些自動配置類中使用了`@ConditionalOnXxx`系列的注解，對當前項目的環境（是否添加了哪些依賴、是否創建了哪些對象、是否配置了哪些屬性等）進行判斷，以決定是否需要啟用某些自動配置。

另外，其實需要通過`@EnableAutoConfiguration`注解來開啟自動配置，此注解已經被包含在`@SpringBootApplication`中了，所以，並不需要顯式的使用此注解！

## 3.4. 相關注解

- `@SpringBootApplication`：添加在啟動類上，每個Spring Boot項目只能有1個類添加此注解
- `@SpringBootConfiguration`：包含了`@Configuration`，被包含在`@SpringBootApplication`中
- `@SpringBootTest`：標記某個類是基於Spring Boot的測試類，執行這個類中的測試方法時，會加載Spring Boot的所有環境，包括執行組件掃描、讀取配置文件等

# 4. Spring Security框架

## 4.1. Spring Security框架的作用

Spring Security框架主要解決了認證與授權的相關問題。

## 4.2. 相關概念

認證：識別客戶端的身份，Spring Security只會根據`SecurityContext`中的認證信息（`Authentication`對象）來識別客戶端的身份

授權：控制客戶端是否允許訪問某個資源

提示：登錄是處理認證時非常重要且不可或缺的一個環節，在處理登錄時，需要將通過登錄驗證後的結果（認證信息）存入到`SecurityContext`中，後續，Spring Security會自動從`SecurityContext`中找到認證信息，從而識別客戶端的身份

## 4.3. Spring Security框架的基本特點

在Spring Boot項目中，當添加了`spring-boot-starter-security`依賴後，你的項目會發生以下變化：

- 所有請求都是必須通過認證的，否則，會響應`403`，或重定向到默認的登錄頁面
- 提供了默認的登錄頁（`/login`）和登出頁（`/logout`）
- 提供了默認的登錄賬號，用戶名為`user`，密碼為啟動項目時的控制台提示的UUID值
- 默認開啟了防止偽造的跨域攻擊的防禦機制，自定義的POST請求無法正常處理

## 4.4. 關於Spring Security的配置類

在項目中，可以自定義類，繼承自`WebSecurityConfigurerAdapter`，則此類是Spring Security的配置類，在類中重寫`void configure(HttpSecurity http)`方法進行配置。

```java
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public void configure(HttpSecurity http) {
        // 配置請求的授權訪問，注意：將使用第一匹配原則
        http.authorizeRequests()
            .mvcMatchers("/user/login", "/user/reg") // 匹配若幹個路徑，可以使用Ant-Style通配模式
            .permitAll() // 允許直接訪問，不需要檢查認證信息
            .anyRequest() // 所有請求，也可以視為“除了以上配置過的以外的其它請求”
            .authenticated(); // 要求是已經通過認證的，則需要檢查認證信息
        
        // 如果調用以下方法，將啟用默認的登錄頁和登出頁，當視為“未通過認證”時，將重定向到登錄頁
        // 如果不調用以下方法，將不啟用默認的登錄頁和登錄頁，當視為“未通過認證”時，將響應403
        // 還可以在以下方法的基礎上，進一步調用其它方法對登錄頁和登出頁進行配置
        http.formLogin();
        
        // 禁用【防止偽造的跨域攻擊的防禦機制】，則自定義的POST請求可以正常處理
        http.csrf().disable();
    }
}
```

關於Ant-Style：

- 當使用`*`通配符時，例如：`/user/*`，可以匹配`/user/login`，但不可以匹配`/user/9527/delete`
- 當使用`**`通配符時，例如：`/user/**`，可以匹配`/user/login`，也可以匹配`/user/9527/delete`

關於第一匹配原則：

- 如果某個請求匹配以上配置的多種規則，將以第一次匹配到的為準，根據以上演示代碼，`/user/login`匹配到了`mvcMatcher()`方法配置的路徑，也匹配了`anyRequest()`，由於`mvcMatcher()`的代碼靠前，則`/user/login`請求適用於`permitAll()`，而不會是`authenticated()`，在實際應用中，可以簡單的認為：需要將精準的配置寫在靠前的位置，需要將模糊的配置寫在靠後的位置

## 4.5. 使用Spring Security驗證登錄

Spring Security提供了`UserDetailsService`接口，接口中定義了`UserDetails loadUserByUsername(String username)`方法，在驗證登錄時，Spring Security框架會自動使用登錄時提交的用戶名來調用這個方法，則框架將得到返回的`UserDetails`類型的對象，並自動檢查`UserDetails`對象中的用戶狀態，如果用戶狀態為不可用（被禁用、已過期等），則拋出異常，然後，還會自動驗證登錄時提交的密碼與`UserDetails`對象中的密碼是否匹配，如果不匹配，則拋出異常，如果匹配，則驗證完成，將返回`Authentication`類型的結果。

```
假設提交了登錄信息：root / 123456

【以下是框架自動執行】
UserDetails userDetails = userDetailsService.loadUserByUsername("root");
檢查userDetails對象中包含的用戶狀態，例如是否禁用、是否過期等
檢查userDetails對象中包含的密碼，與123456是否匹配
```

當需要自定義登錄驗證時，需要：

- 在配置類中，使用`@Bean`方法配置密碼編碼器（`PasswordEncoder`）
  - Spring Security在驗證密碼是否匹配時，要求`UserDetails`類型的對象中的密碼是某種密文格式
  - 一般定義在Spring Security的配置類中即可
- 在Spring Security配置類中，通過重寫`AuthenticationManager authenticationManagerBean()`方法，並在方法上添加`@Bean`注解，則後續可以自動裝配`AuthenticationManager`對象來執行登錄驗證
  - 不建議重寫`authenticationManager()`方法，此方法在執行某些測試時會出現死循環，從而導致內存溢出

- 自定義組件類，實現`UserDetailsService`接口，並重寫`loadUserByUsername()`方法
  - 一旦Spring容器中存在`UserDetailsService`類型的對象，則Spring Security不再啟用默認的登錄賬號，啟動項目時控制台也不再顯示臨時的UUID密碼
- 在需要執行登錄驗證的類中，先自動裝配`AuthenticationManager`，然後再調用此對象的`authenticate()`方法即可執行登錄驗證
  - 提示：驗證通過後，應該將`authenticate()`方法返回的結果存入到`SecurityContext`中，以保證後續能夠通過認證

# 5. 算法相關

## 5.1. 關於UUID

UUID是全球唯一碼，它保證了在同一時空中的值是唯一的！

UUID的算法並不是固定的，各個平台生成的UUID的特征並不相同！

UUID的主要特點：唯一、隨機（不可預測）

UUID本質是由128位算法（運算結果是128個二進制位）運算得到的結果，在顯示時，通常會轉換成十六進制數，長度為32，並且，會在其中添加4個減號（典型格式是8-4-4-4-12），則總長度為36。

另外，128位算法的結果有2的128次方個，2的128次方的值為340282366920938463463374607431768211456。

## 5.2. 關於BCrypt算法

**注意：**所有用於處理密碼加密並最終保存到數據庫中所使用的算法，都不是加密算法！所有的加密算法都是可以被逆向運算的，加密算法是用於保證傳輸過程的安全性的！用於處理密碼加密並存儲的算法，都是單向算法，即使算法、加密參數、密文均被泄密，也不可能通過運算還原出原始的密碼（密碼的原文）。

早期，典型的用於處理密碼加密算法主要有：MD5、SHA-1等，這些算法是可以被窮舉手段進行破解的！

BCrypt算法默認使用了隨機的鹽值，並且，此鹽值被保存到密文中，作為密文的一部分，以便於後續驗證密碼。

BCrypt算法最大的特點就是“慢”！它被故意設計為一種慢速算法，可以有效的防止暴力破解（循環嘗試）。

BCrypt的算法的工具類是`BCryptPasswordEncoder`，這個類的構造方法可以傳入參數，表示算法的強度值，默認為10，表示將進行2的10次方這麽多次數的哈希運算，如果你認為現在執行BCrypt太快而不安全，可以適當的調整這個值，值越大，執行哈希運算的次數就越多，整體運算速度就會越慢！

# 6. 項目的大致開發流程

項目的大致開發流程應該是：立項（確定要做什麽項目） > 頁面規劃 > 數據庫與表的設計 > 編寫代碼。

提示：以上開發流程並不是規範的，更加規範的開發流程可以學習《軟件工程》。

# 7. 規劃本項目的數據類型與數據屬性

商品：ID，創建時間，修改時間，所屬類別ID，所屬類別名稱（冗余），標題，截取的詳情（詳情表中的數據的前???個字，或單獨填入均可，是冗余的數據），圖片，價格，關鍵字列表，是否推薦，審核狀態（冗余），上架狀態，排序序號，銷量（冗余），評論數量（冗余），好評率（冗余），差評率（冗余）

商品詳情：ID，創建時間，修改時間，商品ID，詳情（圖文）

商品審核記錄：ID，創建時間，修改時間，審核人，商品ID，審核時間，狀態，備注

商品上架下架記錄：（暫不實現）

商品類別：ID，創建時間，修改時間，名字，父級ID，層級（冗余），是否無子級（冗余），是否顯示在列表中，排序序號，啟用狀態，關鍵詞列表

購物車：ID，創建時間，修改時間，商品標題，商品價格，商品數量，匯總金額 ，用戶ID

收貨地址：ID，創建時間，修改時間，用戶ID，收貨人，收貨電話，省編碼，省名稱，市編碼，市名稱，區編碼，區名稱，詳細地址，是否為默認的收貨地址

訂單：ID，創建時間，修改時間，訂單號，下單時間，收貨人，收貨地址，收貨電話，訂單狀態，總金額，用戶ID，商品的總數量，物流單號（不實現），支付渠道（不實現），支付方式（不實現）

訂單項（訂單中的商品）：ID，創建時間，修改時間，所屬訂單，商品ID，商品封面圖，商品標題，商品價格，購買數量

> 備注：某些數據處理的難度較大，本項目將不考慮，至少包括：商品折扣、優惠券、庫存等。



```mysq
select * from xx where 父級=1
```

| ID   | 父級 | 名稱     | 層級 | 是否無子級 |
| ---- | ---- | -------- | ---- | ---------- |
| 1    | 0    | 家電     | 1    | 否         |
| 2    | 0    | 水果     | 1    |            |
| 3    | 0    | 服裝     | 1    |            |
| 4    | 1    | 電視     | 2    |            |
| 5    | 1    | 空調     | 2    | 否         |
| 6    | 5    | 櫃機空調 | 3    | 是         |
| 7    | 5    | 掛機空調 | 3    | 是         |
| 8    | 5    | 中央空調 | 3    | 是         |
|      | 7    | xxxx     |      |            |
|      |      |          |      |            |
