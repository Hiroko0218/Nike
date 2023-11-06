### 知識點1

Spring、Spring Boot、Spring MVC、MyBatis 關系

* Spring Framework：核心，包括IoC控制反轉、AOP面向切面編程、Spring MVC等子模塊
* Spring Boot：基於Spring Framework的快速開發項目環境的腳手架，快速構建工程環境
* Spring MVC：Spring Framework的子模塊，用來做Web應用開發
* MyBatis：第三方持久層框架，大大減少了開發人員編寫JDBC的工作量

![](./images/image-20230614082427769.png)

### 知識點2

ORM（**對象關系映射**）框架

|              JAVA              |   數據庫   |
| :----------------------------: | :--------: |
|             一個類             |   一張表   |
| 一個類屬性(可以和字段名不一樣) | 一個表字段 |
|            一個對象            | 一條表記錄 |

### 知識點3

Spring整合MyBatis流程

第1步：創建工程

	Spring Boot版本：2.7.12  <font color=red>因為3版本最低版本支持：JAVA 17</font>
	
	勾選依賴項：MyBatis Framework 和 MySQL Driver

第2步：配置數據庫連接信息 `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mybatisdb?
spring.datasource.username=root
spring.datasource.password=root
```

第3步：創建實體類，和數據表做好映射關系

工程目錄下創建包pojo，在pojo包下創建實體類(**entity**)

```java
public class User{
    // 注意類型、數量和數據表中表字段保持一致
    // 名字可以不一致，後期使用別名
    private Integer id;
}
```



第4步：創建Mapper接口

工程目錄下創建包mapper，創建對應接口

```java
@Mapper
public interface XxxMapper{
    @Insert("SQL語句")
    int insertUser(User user);
}
```



第5步：應用程序中(Controller)注入使用

```java
// 自動裝配
@Autowired
private XxxMapper xxxMapper;

@RequestMapping("/v1/users/register")
public String register(){
    // 獲取用戶信息
    // 校驗是否被占用 - MyBatis
    UserVO userVO = xxxMapper.selectByUsername(String username);
    if (UserVO != null){
        return "用戶名被占用";
    }
    // 注冊-插入數據
    int i = xxxMapper.insertUser(User user);
    if (i > 0){
        return "注冊成功";
    }
    return "注冊失敗";
}
```



### 知識點4：

MyBatis管理數據庫的方式

* 注解方式

  一般用於單表增刪改查，比較方便

* xml配置文件方式

  一般用於多表查詢或者覆雜查詢
