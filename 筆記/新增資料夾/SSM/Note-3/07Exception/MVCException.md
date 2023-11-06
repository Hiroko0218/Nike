[toc]

# Spring MVC中異常處理

## 1 全局異常處理

### 1.1 什麽是全局異常處理器

全局異常處理器是Spring MVC框架中的一種異常處理機制，用於統一 **處理由控制器拋出的異常**。

全局異常處理器可以幫助我們捕獲和處理控制器中的異常，並且可以根據不同的異常類型進行不同的處理操作，從而保障應用的健壯性和穩定性。

當然，Spring MVC中有內置的異常處理對象，但是呈現的結果對於用戶端不友好，所以實際項目我們一般會使用全局異常處理器處理異常。



### 1.2 全局異常處理器的配置

Spring MVC中的全局異常處理器可以通過以下方式進行配置：

1. 創建 `exception.GlobalExceptionHandler`類，並添加異常處理方法；

   使用 `@ControllerAdvice` 注解 或者 `@RestControllerAdvice` 注解標注該類；

2. 在異常處理方法上添加 `@ExceptionHandler` 注解，用於指定控制器中需要處理的異常類型。



### 1.3 使用流程

#### 1）創建全局異常處理器類

工程目錄下創建 `exception.GlobalHandlerException`

* `@ControllerAdvice` 注解

  定義全局異常處理器，處理Controller中拋出的異常。

* `@RestControllerAdvice` 注解

  覆合注解，是`@ControllerAdvice`注解和`@ResponseBody`注解的組合；

  用於捕獲Controller中拋出的異常並對異常進行統一的處理，還可以對返回的數據進行處理。

#### 2）創建異常處理方法

在異常處理方法上添加 `@ExceptionHandler` 注解

* `@ExceptionHandler` 注解

  用於捕獲Controller處理請求時拋出的異常，並進行統一的處理。

* 示例

  ```java
  /**
  	ex.getMessage()方法：用於捕獲異常信息
  */
  @ExceptionHandler
  public JsonResult doHandleRuntimeException(RuntimeException ex){
      log.error("error is " + ex.getMessage());
      return new JsonResult(0,ex.getMessage());
  }
  ```

  

### 1.4 全局異常處理器示例

#### 1）微博詳情頁異常拋出

```java
public JsonResult selectById(int id){
    if(id < 0) {
        throw new IllegalArgumentException("id值無效");
    }
    ... ...
}
```

#### 2）全局異常處理

`exception.GlobalHandlerException` 類

```java
package cn.tedu.weibo.exception;

import cn.tedu.weibo.common.response.JsonResult;
import cn.tedu.weibo.common.response.StatusCode;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * RestControllerAdvice 描述的類型為一個全局異常處理對象類型,
 * 當某個Controller方法中出現了異常，系統底層就會查找有沒有定義全局異常處理對象。
 * 這個全局異常處理對象中有沒有定義對應的異常處理方法，假如有就調用此方法處理異常。
 */

@Slf4j
@RestControllerAdvice //=@ControllerAdvice+@ResponseBody
public class GlobalExceptionHandler {
    /**
     *  @ExceptionHandler 描述的方法為一個異常處理方法，在此注解內部可以定義具體的異常處理
     *  類型(例如RuntimeException),此注解描述的方法需要定義一個異常類型的形式參數，
     *  通過這個參數接收具體的異常對象(也可以接收其異常類型對應的子類類型的異常)。
     */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(RuntimeException ex){
        log.error("error is " + ex.getMessage());
        return new JsonResult(0,ex.getMessage());
    }
    
    /**
     * 假如用全局異常處理對象處理Controller類中出現的異常，全局異常處理對象會優先查找與Controller
     * 中相匹配的異常處理方法，假如沒有，會查找對應異常的父類異常處理方法。
     */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(IllegalArgumentException ex){
        log.error("IllegalArgumentException is " + ex.getMessage());
        return new JsonResult(0,ex.getMessage());
    }
}
```

#### 3）重啟工程測試

http://localhost:8080/v1/weibo/selectById?id=-1



## 2 關於Throwable

在開發實踐中，通常會添加一個處理`Throwable`的方法，它將可以處理所有類型的異常，則不會再出現`500`錯誤！

`GlobalExceptionHandler`中添加處理 Throwable 的方法

```java
@ExceptionHandler
public JsonResult handleThrowable(Throwable e) {
    return new JsonResult(8888, "程序運行過程中出現了Throwable");
}
```
