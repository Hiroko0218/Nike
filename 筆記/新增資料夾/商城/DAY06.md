# 基於Spring JDBC的事務管理

事務：是數據庫中的能夠保證多個寫操作（增刪改）要麽全部成功，要麽全部失敗的一種機制！

假設存在需求：國斌向傳奇轉賬500元，需要執行的SQL操作可能是：

```mysql
update 賬戶余額表 set 余額=余額+500 where 賬戶='傳奇';
```

```mysql
update 賬戶余額表 set 余額=余額-500 where 賬戶='國斌';
```

以上2個SQL操作，如果全部成功，是達成預期的，如果全部失敗，數據沒有出錯，也是可以接受的，如果只成功一半（第1個成功，第2個失敗），則數據不一致，是不可接受的！

在基於Spring JDBC的項目中，只需要在方法上添加`@Transactional`注解，即可使得此方法是“事務性”的（具有要麽全部成功，要麽全部失敗的特點）。

關於`@Transactional`注解，可以：

- 添加在接口上：將作用於接口中所有抽象方法，也就作用於所有重寫的方法
- 添加在（接口中的）抽象方法上：將作用於重寫的方法
- 添加在類上：將作用於此類中所有的方法，注意：只能作用於重寫的接口中的方法
- 添加在（類中的）方法上：將作用於此方法，注意：只能作用於重寫的接口中的方法

在學習過程中，建議在接口上添加`@Transactional`，一來比較省事，二來避免需求變化後忘記補充注解（相對添加在方法上）。

事務的本質是關閉了數據庫的“自動提交”的機制，當關閉後，即使SQL語句執行成功，也不會同步到硬盤的數據庫文件上，後續，如果發現執行出錯，則完全不提交，如果全部執行成功，再統一提交。

Spring JDBC的事務管理機制大致如下：

```
try {
	開啟事務（關閉自動提交）：BEGIN
	執行你的業務方法
	提交事務：COMMIT
} catch (RuntimeException e) {
	回滾事務：ROLLBACK
} finally {
	打開自動提交
}
```

提示：Spring JDBC在調用原生JDBC技術時，捕獲了`SQLException`，並且，拋出了自定義的相關異常，這些異常都是`RuntimeException`的子孫類異常！

所以，在默認情況下，`@Transactional`只會根據`RuntimeException`執行回滾，如果需要按照其它異常回滾，可以配置`@Transactional`注解中的`rollbackFor`屬性或`rollbackForClassName`，例如：

```java
@Transactional(rollbackFor = {NullPointerException.class, ClassCastException.class})
```

```java
@Transactional(rollbackForClassName = {"java.lang.NullPointerException", "java.lang.ClassCastException"})
```

也可以指定哪些異常不會導致回滾，需要配置`noRollbackFor`或`noRollbackForClassName`屬性。

小結：

- 從學術的角度，如果某個業務方法涉及1次以上的寫操作，則必須保證此業務方法是事務性的，從目前學習的角度，直接使得所有業務方法都是事務性的，所以，在業務接口上添加`@Transactional`注解
- 在業務方法的實現中，每次執行了增、刪、改操作後，都應該獲取受影響的行數，並且，判斷此值是否符合預期，如果不符合，則拋出`RuntimeException`或其子孫類異常，使得事務回滾

關於事務，你還應該補充：事務的四大特性（ACID特性）、事務的傳播、事務的隔離。

# 新增類別 -- Controller 

需要在項目中添加`spring-boot-starter-web`依賴項！

提示：`spring-boot-starter-???`都包含了`spring-boot-starter`，所以，調整依賴項時，只需要將原本的`spring-boot-starter`改為`spring-boot-starter-web`即可。

創建控制器，簡單的處理請求，大致如下：

```java
package cn.tedu.tmall.admin.mall.controller;

import cn.tedu.tmall.admin.mall.pojo.param.CategoryAddNewParam;
import cn.tedu.tmall.admin.mall.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    // http://localhost:8080/categories/add-new?name=ShuiGuo&parentId=0
    @RequestMapping("/add-new")
    public void addNew(CategoryAddNewParam categoryAddNewParam) {
        categoryService.addNew(categoryAddNewParam);
    }

}
```

## 添加Knife4j

在基於Spring Boot的項目中，使用Knife4j至少需要：

- 添加依賴
- 編寫配置類
- 在配置文件中配置：`knife4j.enable=true`

關於依賴項（2.0.9適用於Spring Boot 2.5.x版本）：

```xml
<knife4j-spring-boot.version>2.0.9</knife4j-spring-boot.version>
```

```xml
<!-- Knife4j Spring Boot：在線API文檔 -->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>${knife4j-spring-boot.version}</version>
</dependency>
```

關於配置類（可能需要調整`BASE_PACKAGE`屬性的值）：

```java
package cn.tedu.tmall.admin.mall.config;


import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j配置類
 *
 * @author java@tedu.cn
 * @version 2.0
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    /**
     * 【重要】指定Controller包路徑
     */
    private static final String BASE_PACKAGE = "cn.tedu.tmall";
    /**
     * 組名
     */
    private static final String GROUP_NAME = "學茶商城";
    /**
     * 主機名
     */
    private static final String HOST = "http://java.tedu.cn";
    /**
     * 標題
     */
    private static final String TITLE = "學茶商城-商城管理後台服務-在線API文檔";
    /**
     * 簡介
     */
    private static final String DESCRIPTION = "學茶商城-商城管理後台服務-在線API文檔";
    /**
     * 服務條款URL
     */
    private static final String TERMS_OF_SERVICE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 聯系人
     */
    private static final String CONTACT_NAME = "Java教學研發部";
    /**
     * 聯系網址
     */
    private static final String CONTACT_URL = "http://java.tedu.cn";
    /**
     * 聯系郵箱
     */
    private static final String CONTACT_EMAIL = "java@tedu.cn";
    /**
     * 版本號
     */
    private static final String VERSION = "2.0";

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfiguration() {
        log.debug("創建配置類對象：Knife4jConfiguration");
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(HOST)
                .apiInfo(apiInfo())
                .groupName(GROUP_NAME)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(GROUP_NAME));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
                .version(VERSION)
                .build();
    }

}
```

並在`application.yaml`中添加配置：

```yaml
knife4j:
  enable: true
```

完成後，重啟項目，通過 http://localhost:8080/doc.html 測試訪問

# 關於控制器層的響應結果

無論是處理請求時正確的響應，還是後續處理異常後的響應，目前主流的做法是向客戶端響應JSON格式的結果，則需要自定義某個數據類型（嚴格來說，是Spring MVC沒有默認的消息轉換器的類型），則Spring MVC框架會自動使用Jackson框架中的消息轉換器，將方法（處理請求的方法，或處理異常的方法）的返回值轉換為JSON結果！

首先，應該創建一個枚舉類型，用於窮舉所有的業務狀態碼，例如：

```java
/**
 * 業務狀態碼
 *
 * @author java@tedu.cn
 * @version 2.0
 */
public enum ServiceCode {

    /**
     * 操作成功
     */
    OK(20000),
    /**
     * 錯誤：請求參數格式錯誤
     */
    ERROR_BAD_REQUEST(40000),
    /**
     * 錯誤：未認證
     */
    ERROR_UNAUTHORIZED(40100),
    /**
     * 錯誤：未認證，因為被禁用
     */
    ERROR_UNAUTHORIZED_DISABLED(40101),
    /**
     * 錯誤：禁止訪問，用於無權限
     */
    ERROR_FORBIDDEN(40300),
    /**
     * 錯誤：數據不存在
     */
    ERROR_NOT_FOUND(40400),
    /**
     * 錯誤：數據沖突
     */
    ERROR_CONFLICT(40900),
    /**
     * 錯誤：未知的插入數據失敗
     */
    ERROR_INSERT(50000),
    /**
     * 錯誤：未知的刪除數據失敗
     */
    ERROR_DELETE(50100),
    /**
     * 錯誤：未知的修改數據失敗
     */
    ERROR_UPDATE(50200),
    /**
     * 錯誤：JWT已過期
     */
    ERR_JWT_EXPIRED(60000),
    /**
     * 錯誤：JWT驗證簽名失敗，可能使用了偽造的JWT
     */
    ERR_JWT_SIGNATURE(60100),
    /**
     * 錯誤：JWT格式錯誤
     */
    ERR_JWT_MALFORMED(60200),
    /**
     * 錯誤：上傳的文件為空（沒有選擇有效的文件）
     */
    ERROR_UPLOAD_EMPTY(90000),
    /**
     * 錯誤：上傳的文件類型有誤
     */
    ERROR_UPLOAD_INVALID_TYPE(90100),
    /**
     * 錯誤：上傳的文件超出限制
     */
    ERROR_UPLOAD_EXCEED_MAX_SIZE(90200),
    /**
     * 錯誤：其它異常
     */
    ERROR_UNKNOWN(99999);

    /**
     * 枚舉對象的值
     */
    private Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    /**
     * 獲取枚舉對象的值
     *
     * @return 枚舉對象的值
     */
    public Integer getValue() {
        return value;
    }

}
```

然後，創建新的類，作為響應結果的數據類型：

```java
/**
 * 服務器端的統一響應類型
 *
 * @author java@tedu.cn
 * @version 2.0
 */
@Data
public class JsonResult {
    
    /**
     * 操作結果的狀態碼（狀態標識）
     */
    @ApiModelProperty("業務狀態碼")
    private Integer state;
    
    /**
     * 操作失敗時的提示文本
     */
    @ApiModelProperty("提示文本")
    private String message;
    
    /**
     * 操作成功時響應的數據
     */
    @ApiModelProperty("數據")
    private Object data;

    /**
     * 生成表示"成功"的響應對象
     *
     * @return 表示"成功"的響應對象
     */
    public static JsonResult ok() {
        return ok(null);
    }

    /**
     * 生成表示"成功"的響應對象，此對象中將包含響應到客戶端的數據
     *
     * @param data 響應到客戶端的數據
     * @return 表示"成功"的響應對象
     */
    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = ServiceCode.OK.getValue();
        jsonResult.data = data;
        return jsonResult;
    }

    /**
     * 生成表示"失敗"的響應對象
     *
     * @param serviceCode 業務狀態碼
     * @param message     提示文本
     * @return 表示"失敗"的響應對象
     */
    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.state = serviceCode.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

}
```

完成後，調整控制器中處理請求的方法的返回結果：

```java
@PostMapping("/add-new")
public JsonResult addNew(CategoryAddNewParam categoryAddNewParam) {
    categoryService.addNew(categoryAddNewParam);
    return JsonResult.ok();
}
```

# 關於響應結果中的null值

當前項目中，響應的JSON數據中會包含`JsonResult`類中所有的屬性，但是，每個請求都必然至少有1個屬性的值為`null`，這些為`null`的屬性沒有必要出現在響應結果的JSON數據中！

可以通過`@JsonInclude`注解進行配置，例如：

```java
@JsonInclude(JsonInclude.Include.NON_NULL)
```

當把注解添加在屬性上，則作用於當前屬性；當把注解添加在類上，則作用於當前類中所有屬性！

還可以在配置文件中配置`spring.jackson.default-property-inclusion`屬性，使得當前項目中所有響應的類型（包括作為`JsonResult`對象的`data`屬性的數據類型）都應用此配置，例如：

```properties
spring.jackson.default-property-inclusion=non_null
```

```yaml
spring:
  jackson:
    default-property-inclusion: non_null
```

# 使用全局異常處理器統一處理異常

首先，在Service中拋出的異常不應該是某個已知的異常，因為這些異常都有其對應的錯誤，例如`NullPointerException`，如果在Service中自行拋出NPE，後續，當捕獲到NPE時，將無法區分到底是“真的因為空指針導致的錯誤”還是“不符合業務規則的錯誤”，所以，應該自定義異常！

自定義異常應該繼承自`RuntimeException`，因為：

- 配合Spring JDBC的事務管理機制，在Service方法中拋出自定義異常時，可以回滾
- 當前項目會使用到全局異常處理器來處理各種異常，而全局異常處理器只能處理Controller拋出的異常，而Controller會調用Service，Service必須拋出異常，那麽，如果自定義不是繼承自`RuntimeException`，則Service接口、Service實現類、Controller中所有的方法都必須顯式的通過`throws`關鍵字聲明拋出異常，由於Service、Controller拋出異常是固定的做法，沒有必要都顯式的聲明拋出，所以，使用`RuntimeException`會更加便利

並且，在拋出異常時，應該區分出是因為什麽原因導致的異常，後續處理異常時才能給出正確的業務狀態碼（例如40400、40900等），可選的解決方案有：

- 創建多個異常類型，在不同的錯誤時，拋出不同的異常
- 在自定義異常中添加屬性，通過屬性區分不同的錯誤，每次拋出異常時必須確定此屬性的值

綜合看來，創建的異常類應該是：

```java
public class ServiceException extends RuntimeException {

    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

}
```

然後，在`JsonResult`中添加新的方法：

```java
/**
 * 生成表示"失敗"的響應對象
 *
 * @param e 業務異常
 * @return 表示"失敗"的響應對象
 */
public static JsonResult fail(ServiceException e) {
    return fail(e.getServiceCode(), e.getMessage());
}
```

並且，使用全局異常處理器處理`ServiceException`：

```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.warn("由全局異常處理器開始處理ServiceException");
        return JsonResult.fail(e);
    }

}
```

需要注意，在全局異常處理器中，你應該添加一個方法，用於處理`Throwable`類型的異常！

```java
@ExceptionHandler
public JsonResult handleThrowable(Throwable e) {
    log.warn("由全局異常處理器開始處理Throwable");
    log.warn("", e);
    String message = "服務器忙，請稍後再試！【同學們，當你們看到這個提示時，請檢查服務器端的控制台，發現異常，並在全局異常處理器中補充處理此異常的方法】";
    return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
}
```
