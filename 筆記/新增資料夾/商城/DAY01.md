# 1. Spring框架

## 1.1. Spring框架的作用

Spring框架主要解決了創建對象和管理對象的相關問題。

通過Spring創建並管理對象，可以使得開發者不再反覆關心對象的創建過程，並且，默認情況下，由Spring創建的對象都是單例的，這是非常有必要的！

> 由Spring創建的對象通常稱之為Spring Bean。
>
> 由於Spring會創建並管理很多對象，所以Spring也通常被稱之為Spring容器。

## 1.2. Spring框架的依賴項

Spring框架的基礎依賴項是：`spring-context`。

## 1.3. 使用Spring框架創建對象

### 1.3.1. 組件掃描

在配置類上，添加`@ComponentScan`注解可以開啟組件掃描。

> 在Spring Boot項目中，啟用類上的`@SpringBootApplication`注解中包含`@ComponentScan`。
>
> 關於`@SpringBootApplication`注解：
>
> ```java
> @SpringBootApplication
> -- @ComponentScan
> -- @SpringBootConfiguration
> -- -- @Configuration
> ```
>
> 另外，如果沒有在`@ComponentScan`上配置掃描的包，默認掃描的是當前配置類所在的包。

在類上添加`@Component`或基於`@Component`的組合注解，即可將類標記為組件類。

> 在Spring框架中，組件注解有：
>
> - `@Component`
> - `@Controller`
> - `@Service`
> - `@Repository`
> - `@Configuration`
>
> 在Spring MVC框架中，還新增了組件注解：
>
> - `@RestController`
> - `@ControllerAdvice`
> - `@RestControllerAdvice`

Spring框架在執行組件掃描時，會掃描所配置的包及其子孫包，並嘗試創建所有組件類的對象。

這種做法只適用於自定義的類。

### 1.3.2. @Bean方法

在配置類中，可以自行設計方法，返回所需的對象，並在方法上添加`@Bean`注解，則Spring會自動調用此方法，並管理此方法返回的對象。

示例：

```java
@Configuration
public class BeanConfiguration {
    @Bean
    public UserController userController() {
        return new UserController();
    }
}
```

這種做法適用於所有類型創建對象，但一般用於創建非自定義類的對象。

## 1.4. Spring Bean的作用域

默認情況下，被Spring管理的對象都是單例的，也可以通過`@Scope("prototype")`修改為非單例的（相當於局部變量）。

被Spring管理的單例的Spring Bean，默認情況下，都是預加載的（相當於餓漢式的單例模式），也可以通過`@Lazy`注解修改為懶加載的（相當於懶漢式的單例模式）。

注意：不要把Spring和單例模式這種設計模式直接劃等號，只是Spring管理的對象的特征與單例模式的相同而已。

如果使用組件掃描的方式創建對象，則在類上添加`@Scope`或`@Lazy`來配置作用域，如果使用`@Bean`方法的方式創建對象，則在方法上添加這些注解來配置作用域。

## 1.5. Spring Bean的生命周期

僅當Spring Bean是單例的，才有必要討論生命周期問題。

在自定義的組件類中，可以自定義方法，並在方法添加`@PostConstruct`和`@PreDestroy`注解，將方法標記為初始化方法和銷毀方法。

在使用`@Bean`方法的情況下，可以在`@Bean`注解上配置`initMethod`和`destroyMethod`屬性，來指定初始化方法和銷毀方法的名稱。

初始化方法會在創建對象、完成自動裝配之後，被Spring框架自動調用。

銷毀方法會在Spring框架銷毀對象的前一刻被自動調用。

## 1.6. 自動裝配

自動裝配：當某個屬性，或某個被Spring自動調用的方法的參數需要值時，Spring會自動從容器中找到合適的值，為屬性或參數注入值。

示例：為屬性注入值

```java
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
}
```

示例：為方法的參數注入值

```java
@RestController
public class UserController {
    private UserMapper userMapper;
    
    //                    ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 自動裝配
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
```

如果類中僅有1個構造方法，不需要添加`@Autowired`注解；如果類中有多個構造方法，Spring會嘗試調用無參數的構造方法，如果某個構造方法添加了`@Autowired`注解，則必然調用添加了注解的構造方法。

```java
@RestController
public class UserController {
    private UserMapper userMapper;
    
    @Autowired
    //                        ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 自動裝配
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
```

```java
@Configuration
public class BeanConfiguration {
    @Bean
    //                                   ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 自動裝配
    public UserController userController(UserMapper userMapper) {
        UserController userController = new UserController();
        userController.setUserMapper(userMapper);
        return userController;
    }
}
```

除此以外，通過`@Value`注解也適用以上做法！

使用`@Resource`注解也可以實現自動裝配，但是，它不可以添加在構造方法上！並且，它是`javax` 包下的注解！從裝配機制上，`@Resource`是先根據名稱裝配，再根據類型裝配，而`@Autowired`是先根據類型進行查找，當沖突時再嘗試根據名稱裝配。

關於`@Autowired`的裝配機制，它是先根據類型查找匹配類型的Spring Bean的數量，然後：

- 0個：取決於`@Autowired`注解的`required`屬性的值
  - `true`（默認）：裝配失敗，加載Spring時拋出`NoSuchBeanDefinitionException`異常
  - `false`：放棄裝配，但是，後續可能出現NPE
- 1個：直接裝配，且成功
- 多個：將嘗試根據名稱進行裝配：
  - 存在名稱匹配的：裝配成功
  - 不存在名稱匹配的：裝配失敗，加載Spring時拋出`NoUniqueBeanDefinitionException`異常

另外，關於名稱匹配：

- Spring Bean的名稱與屬性（或參數）的名稱相同
- 屬性（或參數）的名稱與Spring Bean的名稱相同
- 通過`@Resource`的`name`屬性指定Spring Bean的名稱；通過`@Qulifier`注解指定Spring Bean的名稱，結合`@Autowired`的機制一起使用

## 1.7. 概念

IoC（Inversion of Control）：控制反轉，指的是將對象的創建、管理等控制權交給了框架

DI（Dependency Injection）：依賴注入，為對象的依賴項（類中的屬性）注入值

Spring框架通過DI完善了IoC。

## 1.8. Spring AOP

再議

# 2. Spring MVC框架

## 2.1. Spring MVC框架的作用

Spring MVC框架主要解決了：

- 接收請求
- 響應結果
- 處理異常

## 2.2. Spring MVC框架的依賴項

Spring MVC框架的基礎依賴項是：`spring-webmvc`

## 2.3. 接收請求

Spring MVC通過控制器中的方法來接收請求。

僅當添加了`@Controller`注解的組件類會被視為“控制器類”。

需要通過`@RequestMapping`系列注解來配置請求路徑，此系列注解包括：

- `@RequestMapping`
- `@GetMapping`，等效於`@RequestMapping(method = RequestMethod.GET)`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`
- `@PatchMapping`

通常，以上注解都只需要用於配置請求路徑即可，如果響應結果中，中文出現亂碼，可以配置注解的`produces`屬性，例如：

```java
@RequestMapping(value = "/users", produces = "application/json; charset=utf-8")
```

## 2.4. 接收請求參數

### 2.4.1. 接收普通參數

無論是GET請求中在URL中的參數，還是POST請求中在請求體中的參數，你都可以直接聲明為處理請求的方法的參數，例如：

```java
@PostMapping("/login")
public void login(String username, String password) {
    // ...
}
```

或者：

```java
@Data
public class UserLoginParam implements Serializable {
    private String username;
    private String password;
}
```

```java
@PostMapping("/login")
public void login(UserLoginParam userLoginParam) {
    // ...
}
```

提示：以上做法都可以接收到請求參數，特別是第2種封裝的做法，在過程中，Spring MVC框架就使用到了Spring框架創建對象的機制，把多個請求參數自動創建為參數類型的對象！

### 2.4.2. 接收URL中占位符的參數

也可以在設計URL時，使用`{}`格式的占位符，並結合`@PathVariable`注解獲取占位符位置的值，例如：

```java
// http://localhost:8080/users/9527/delete
@PostMapping("/{id}/delete") // 假設類上已經配置了@RequestMapping("/users")
public void delete(@PathVariable Long id) {
    // ...
}
```

在占位符中的自定義名稱右側，可以添加冒號，再編寫正則表達式，例如：

```java
@PostMapping("/{id:[0-9]+}/delete")
```

如果請求的URL匹配，則對應的方法可以處理請求，如果請求的URL不匹配，則不會匹配！

另外，如果設計的URL中的占位符名稱與方法的參數名稱不一致，可以配置注解參數：

```java
@PostMapping("/{id}/delete")
public void delete(@PathVariable("id") Long userId) {
    // ...
}
```

### 2.4.3. 關於`@RequestBody`注解

在處理請求的方法的參數上添加`@RequestBody`，表示客戶端提交的請求參數需要是某種特定格式的，例如是JSON格式或XML格式等。

在沒有添加`@RequestBody`的情況下，客戶端提交的請求參數必須是FormData格式的，例如：`username=test&password=123456`。

結論：

- 有`@RequestBody`：參數必須是某種格式的，如果是FormData，則拋出`HttpMediaTypeNotSupportedException`異常
- 沒有`@RequestBody`：參數必須是FormData的，如果是某種格式的，則服務器端接收到的參數均為`null`

### 2.4.4. 關於`@RequestParam`

`@RequestParam`注解的源代碼如下：

```java
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestParam {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default true;

    String defaultValue() default "\n\t\t\n\t\t\n\ue000\ue001\ue002\n\t\t\t\t\n";
}
```

## 2.5. 響應結果

在默認情況下，處理請求的方法的返回值表示“處理響應的視圖組件的名稱及相關數據”，最終的響應是一個嵌入了數據的完整的HTML頁面的源代碼，這不是前後端分離的做法！

在前後端分離的開發模式下，服務器端應該只響應數據結果，由客戶端自行決定如何處理這些數據！

在處理請求的方法上，添加`@ResponseBody`注解，即可使得此方法是“響應正文”的，方法的返回值就是響應到客戶端的數據，而不再處理視圖！

也可以在控制器類上添加`@ResponseBody`注解，則類中所有方法都是響應正文的！或者，在類上使用`@RestController`注解，它是由`@Controller`和`@ResponseBody`組合而成的注解！

## 2.6. 關於HttpMessageConverter

Spring MVC框架內置了許多消息轉換器（MessageConverter），其作用是實現基礎數據與對象的相互轉換。

在接收請求時，Spring MVC會根據請求頭中的`Content-Type`來識別請求參數的文檔類型，然後，自動選擇合適的消息轉換器，將請求參數轉換為對象！

在處理響應時，Spring MVC會根據方法的返回值類型來選擇合適的消息轉換器，例如，當方法的返回值是`String`類型，就會使用`StringHttpMessageConverter`（Spring MVC自帶的），如果方法的返回值類型是Spring MVC沒有對應的消息轉換器的類型時，如果項目中添加了Jackson依賴項時，Spring MVC會自動使用Jackson框架中的消息轉換器來處理方法的返回值，而Jackson框架會將返回值轉換成JSON格式的數據！

在Spring Boot項目中添加了`spring-boot-starter-web`時，就包括了Jackson相關的依賴項！

## 2.7. 統一處理異常

在基於Spring MVC框架的Web項目中，應該由控制器中的方法捕獲並處理異常，但是，處理不同請求時，可能出現相同的異常，則在多個不同的方法都需要使用`try...catch`進行捕獲並處理，非常繁瑣！Spring MVC框架提供了統一處理異常的機制，每個處理請求的方法都不必處理異常，而是將異常拋出即可，Spring MVC會自動使用統一處理異常的機制對這些拋出的異常進行處理！

注意：統一處理異常的機制，只能處理控制器中的方法拋出的異常！

使用統一處理異常機制時：

- 使用專門的類，在類上添加`@ControllerAdvice`注解，或`@RestControllerAdvice`注解，則此類中的特定的方法（例如添加了`@ExceptionHandler`的方法）就可以作用於整個項目中所有控制器類中的所有方法
- 自定義處理異常的方法，此方法需要添加`@ExceptionHandler`注解，並且，方法的參數中必須有1個異常類型

注意：處理異常的方法，並不像處理請求的方法那樣可以自由的添加參數，必須有1個異常類型，並且，可以按需添加少量特定類型的參數，例如`HttpServletRequest`、`HttpServletResponse`等，除了特定類型以外的都不可以設計為處理異常的方法的參數！
