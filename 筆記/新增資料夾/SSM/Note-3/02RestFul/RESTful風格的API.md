[toc]

# RESTful -Representational State Transfer

## 1 什麽是RESTful特征的API

- 每一個URI代表一種資源


- 客戶端和服務器端之間傳遞著資源的某種表現


- 客戶端通過HTTP的幾個動作 對 資源進行操作，發生 狀態轉化



## 2 如何設計符合RESTful 特征的API

### 2.1 關於URL

- 協議  - http/https


- 域名

  域名中體現出api字樣，如   

  https://api.example.com

  https://example.org/api/

- 版本

  https://api.example.com/v1/

- 路徑

  路徑中避免使用動詞，資源用名詞表示，案例如下

  ```python
  https://api.example.com/v1/users
  https://api.example.com/v1/animals
  ```



### 2.2 HTTP動詞語義

#### 2.2.1 說明


- HTTP動詞語義

  | 請求動詞         | 說明                           |
  | ---------------- | ------------------------------ |
  | GET（SELECT）    | 從服務器取出資源（一項或多項） |
  | POST（CREATE）   | 在服務器新建一個資源           |
  | PUT（UPDATE）    | 在服務器更新資源               |
  | DELETE（DELETE） | 從服務器刪除資源               |

  

  <font color=blue>具體案例如下：</font>

  | 請求動作 | 請求資源            | 說明                         |
  | -------- | ------------------- | ---------------------------- |
  | GET      | /zoos               | 列出所有動物園               |
  | POST     | /zoos               | 新建一個動物園               |
  | GET      | /zoos/ID            | 獲取某個指定動物園的信息     |
  | PUT      | /zoos/ID            | 更新某個指定動物園的信息     |
  | DELETE   | /zoos/ID            | 刪除某個動物園               |
  | GET      | /zoos/ID/animals    | 列出某個指定動物園的所有動物 |
  | DELETE   | /zoos/ID/animals/ID | 刪除某個指定動物園的指定動物 |



### 2.3 查詢參數

巧用查詢參數

```python
?type_id=1：指定篩選條件
?limit=10：指定返回記錄的數量
?offset=10：指定返回記錄的開始位置。
```



### 2.4 HTTP狀態碼

<font color=red>用HTTP響應碼表達 此次請求結果，例如</font>

| 響應碼                          | 說明                               |
| ------------------------------- | ---------------------------------- |
| 200 OK - [GET]                  | 服務器成功返回用戶請求的數據       |
| 404 NOT FOUND - [*]             | 用戶發出的請求針對的是不存在的記錄 |
| 500 INTERNAL SERVER ERROR - [*] | 服務器發生錯誤                     |



## 3 常用注解

### 1) `@RequestMapping` 注解

`@RequestMapping` 用於指定處理請求的 URL，它可以標注在類和方法上；

可以通過 `method` 參數限定處理 GET、POST、PUT、DELETE 等HTTP的請求方法，比如：

* 處理 `GET` 請求

  @RequestMapping(value = "/v1/users", <font color=red>**method = RequestMethod.GET**</font>)

* 處理 `POST` 請求

  @RequestMapping(value = "/v1/users", <font color=red>**method = RequestMethod.POST**</font>)

**處理 PUT DELETE 方式的HTTP請求同理。**



### 2) 限定請求方式的注解

#### 2.1`@GetMapping` 注解

`@GetMapping` 只會處理 HTTP GET 請求；

是 `@RequestMapping(method = RequestMethod.GET)` 的縮寫。

如果限定為處理GET請求，則發送其他方式請求時<font color=red>**HTTP狀態碼為 405**</font>



#### 2.2 `@PostMapping` 注解

`@PostMapping` 只會處理 HTTP POST 請求；

是 `@RequestMapping(method = RequestMethod.POST)` 的縮寫。

如果限定為處理POST請求，則發送其他方式請求時<font color=red>**HTTP狀態碼為 405**</font>



#### 2.3 `@PutMapping` 注解

`@PutMapping` 只會處理 HTTP PUT 請求；

是 `@RequestMapping(method = RequestMethod.PUT)` 的縮寫。

如果限定為處理PUT請求，則發送其他方式請求時<font color=red>**HTTP狀態碼為 405**</font>



#### 2.4 `@DeleteMapping` 注解

`@DeleteMapping` 只會處理 HTTP DELETE 請求；

是 `@RequestMapping(method = RequestMethod.DELETE)` 的縮寫。

如果限定為處理DELETE請求，則發送其他方式請求時<font color=red>**HTTP狀態碼為 405**</font>



### 3)`@PathVariable` 注解

`@PathVariable` 注解用於接受 RESTful API 中的 URL 中的變量；

通常與請求注解一起使用，可以將 URL 中的變量映射到 Controller 中的方法參數上。

#### 3.1 用法

假如有一個 RESTful API：`/v1/users/{id}`，

其中 `{id}` 是一個變量，表示用戶 ID。

可以這樣定義一個處理該請求的控制器方法

```java
@GetMapping("/v1/users/{id}")
public String getUserById(@PathVariable Integer id) {
    // 根據 ID 查詢用戶，並返回用戶信息
}
```

#### 3.2 使用示例

**根據 `id` 查詢用戶，並返回該用戶信息**

* 地址：`/v1/users/{id}`   id為用戶id
* 請求方法：`GET`
* 查詢參數：無
* 響應類型：用戶對象user

**實現**

<font color=red>使用 _052MVC-BOOT02 工程</font>

* 根據 `id` 查詢用戶，並返回該用戶信息（`GET請求：/v1/users/{id}`）

  ```java
  @GetMapping("{id}")
  public User selectById(@PathVariable int id){
      // 自己定義相對應的接口方法
      return userMapper.selectById(id);
  }
  ```

  

## 4 JSON

JSON（JavaScript Object Notation）是一種數據交換格式，常用於不同系統間的數據傳輸。

在使用SpringMVC框架時，常常需要將Java對象轉換成JSON格式，然後返回給前端。

JSON格式的數據通常由一對花括號 {} 括起來，在花括號中包含鍵值對，鍵值對之間使用逗號分隔。

比如：

```json
{
    "name": "Tom",
    "age": 18,
    "interests": ["reading", "music"],
    "address": {
        "city": "Beijing",
        "street": "xxx road"
    }
}
```
