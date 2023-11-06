[toc]

# 統一響應結果的處理

## 1 為什麽需要統一響應結果處理

在實際開發中，我們往往需要在多個控制器方法中返回相同的響應結構；

例如，統一返回接口調用成功的狀態碼、提示信息、以及請求結果數據。但是，如果對於每個接口都單獨進行處理的話，不僅邏輯覆雜，而且容易出現疏漏，進而增加前端調用的難度。

所以為了更好定義服務端返回值的格式，統一客戶端對服務端響應結果的處理，我們需要將服務端返回到客戶端的數據再次進行封裝。

* 響應狀態碼 `code`
* 提示信息 `msg`
* 響應數據 `data`

## 2 統一狀態碼設計

當項目中的狀態碼越來越多時，對狀態碼的定義如果沒有統一規劃，後續對狀態的理解就會相當的困難，而且容易導致操作上的失敗。

**狀態碼設計**

| 自定義狀態碼 |     含義     |
| :----------: | :----------: |
|     1000     |    未登錄    |
|     1001     |   登錄成功   |
|     1002     |   密碼錯誤   |
|     1003     |  用戶名錯誤  |
|     1004     | 用戶名已存在 |
|     2001     |   操作成功   |
|     2002     |   操作失敗   |



## 3 統一響應結果處理的代碼實現

### 3.1 `JsonResult`類的創建

首先，在工程目錄中創建一個新的類`common.response.JsonResult`；該類作為統一的響應結果類；

其包含了響應結果的狀態碼（code）、提示信息（msg）和請求結果數據（data）三個屬性：

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    /**響應狀態碼*/
    private Integer code;
    /**服務端要返回給客戶端的消息*/
    private String msg;
    /**服務端要返回給客戶端的具體數據(業務數據)*/
    private Object data;

    public JsonResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public JsonResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
```

### 3.2 `controller`代碼重構

以 `UserController` 中的 **注冊功能** 為例

對 `Controller`中的代碼進行重構，將結果封裝成 `JsonResult` 類型的結果，並返回給客戶端

```java
@ApiOperation(value = "注冊功能")
@PostMapping("reg")
public JsonResult reg(@RequestBody UserRegDTO userRegDTO){
    /**
     * 1.確認用戶名是否被占用
     * 2.注冊用戶
    */
    log.debug("userRegDTO = " + userRegDTO);
    UserVO userVO = userMapper.selectByUsername(userRegDTO.getUsername());
    if (userVO != null){
        return new JsonResult(1004, "用戶名被占用"); // 用戶名被占用
    }

    User user = new User();
    BeanUtils.copyProperties(userRegDTO, user);
    user.setCreated(new Date());
    userMapper.insert(user);
    return new JsonResult(1001, "操作成功"); // 注冊成功
}
```

### 3.3 使用 `Knife4j` 進行測試

重啟工程後，使用 `Knife4j` 進行測試

![image-20230530152603608](./images/image-20230530152603608.png)



# 自定義枚舉狀態碼(StatusCode)

## 1 簡介

在Spring MVC中，一般通過HTTP響應狀態碼來表示請求是否成功。但是HTTP狀態碼的語義有時候比較籠統，不能夠很好地表達業務邏輯。

為了解決這一問題，我們可以定義自己的狀態碼，將它們作為HTTP響應狀態碼的補充，以更加精準地表示請求的處理結果。

通常我們使用枚舉類來定義自定義狀態碼，並且將它們應用到Spring MVC的實踐中。



## 2 自定義枚舉狀態碼

工程目錄下創建 `common.response.StatusCode` 來自定義枚舉狀態碼

```java
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {
    NOT_LOGIN(1000,"未登錄"), //所有實例必須是在最上面的
    LOGIN_SUCCESS(1001,"登錄成功"),
    PASSWORD_ERROR(1002,"密碼錯誤"),
    USERNAME_ERROR(1003,"用戶名錯誤"),
    USERNAME_ALREADY_EXISTS(1004,"用戶名已存在"),
    OPERATION_SUCCESS(2001,"操作成功"),
    OPERATION_FAILED(2002,"操作失敗");

    /**表示狀態碼*/
    private int code;
    /**狀態碼信息*/
    private String msg;
}
```



## 3 使用自定義枚舉狀態碼

### 3.1 `JsonResult` 構造方法

```java
public JsonResult(StatusCode statusCode){
    this.code = statusCode.getCode();
    this.msg = statusCode.getmsg();
}

public JsonResult(StatusCode statusCode, Object data){
    this.code = statusCode.getCode();
    this.msg = statusCode.getmsg();
    this.data = data;
}

```



### 3.2 `controller` 代碼重構

**注冊功能**

```java
//用戶名被占用
return new JsonResult(StatusCode.USERNAME_ALREADY_EXISTS);

//注冊成功
return new JsonResult(StatusCode.OPERATION_SUCCESS);
```



### 3.3 使用 `Knife4j` 進行測試



## 4 擴展練習

微博項目優化

將微博項目中 用戶模塊、微博模塊 和 評論模塊 中所有的功能使用 自定義枚舉狀態碼 和 統一響應結果處理 實現優化。
