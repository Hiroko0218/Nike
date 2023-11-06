# 處理驗證登錄失敗的異常

使用Spring Security驗證登錄時，如果失敗，Spring Security會拋出對應的異常，並且，Spring Security也有處理這些異常的機制，默認的做法是響應`403`，可以自行定義全局異常處理器來接管處理這些異常，相關的異常有：

```
AuthenticationException
-- BadCredentialsException 密碼錯誤
-- AuthenticationServiceException
-- -- InternalAuthenticationServiceException 用戶名錯誤
-- AccountStatusException
-- -- DisabledException 賬號被禁用
```

則使用全局異常處理器處理以上相關異常：

```java
@ExceptionHandler({
        InternalAuthenticationServiceException.class,
        BadCredentialsException.class
})
public JsonResult handleAuthenticationException(AuthenticationException e) {
    log.debug("全局異常處理器開始處理AuthenticationException");
    String message = "登錄失敗，用戶名或密碼錯！";
    return JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED, message);
}

@ExceptionHandler
public JsonResult handleDisabledException(DisabledException e) {
    log.debug("全局異常處理器開始處理DisabledException");
    String message = "登錄失敗，賬號已經被禁用！";
    return JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
}
```

另外，無認證信息卻嘗試訪問需要通過認證的資源時的異常需要在Spring Security的配置類中的`void configurer(HttpSecurity http)`方法中處理：

```java
// 處理無認證信息卻嘗試訪問需要通過認證的資源時的異常
http.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
    Integer state = ServiceCode.ERROR_UNAUTHORIZED.getValue();
    String message = "當前未登錄，或登錄信息已過期，請登錄！";
    response.setContentType("application/json; charset=utf-8");
    PrintWriter printWriter = response.getWriter();
    printWriter.println("{\"state\": " + state + ", \"message\": \"" + message + "\"}");
    printWriter.close();
});
```

# Token

Token：票據，令牌

當某個客戶端第1次向服務器端發起請求後，服務器端會向客戶端響應一個Token，後續，當客戶端向服務器端發起請求時，將攜帶此Token，服務器端將根據Token來識別客戶端的身份！

與Session機制不同，Token是由服務器端的程序發出的（需要程序員編寫代碼向客戶端響應Token數據），並且，客戶端程序需要自行攜帶Token（需要程序員編寫代碼攜帶Token數據發起請求），甚至，識別客戶端身份也是需要程序員編寫相關代碼來實現的！

Token數據與Session不同，Session ID本質是一個UUID值，所以，是非常隨機的、不可預測的值，並且，沒有實際的數據含義，而Token是一段有意義的數據！

基於Token的特點，它適用於集群或分布式系統，只要每個服務器端都具備解讀這個Token數據的能力，就可以根據Token識別客戶端的身份！

在使用Token時，由於服務器不再需要在內存中保存用戶的信息，所以，可以為Token數據設置較長的有效期！

# JWT

**JWT**：**J**SON **W**eb **T**oken

JWT的官網：https://jwt.io

每個JWT數據都是由3個部分組成，最終使用算法進行編碼得到的數據！組成部分包括：

- Header：頭部信息，用於聲明算法（alg）與Token類型（typ）
- Payload：載荷，用於存放數據
- Verify Signature：驗證簽名，用於配置算法與secretKey

在JWT的官網，列舉了常用的JWT編程的庫：https://jwt.io/libraries?language=Java

以使用`jjwt`為例，需要在項目中添加依賴：

```xml
<jjwt.version>0.9.1</jjwt.version>
```

```xml
<!-- JJWT（Java JWT） -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>${jjwt.version}</version>
</dependency>
```

測試使用：

```java
package cn.tedu.tmall.passport;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    String secretKey = "k4^&32flj5Ss(Jf&*(5%DK3da";

    @Test
    void generate() {
        Date date = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 9527);
        claims.put("username", "root");

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload：Claims
                .setClaims(claims)
                .setExpiration(date)
                // Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // compact
                .compact();
        System.out.println(jwt);
    }

    @Test
    void parse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTUyNywiZXhwIjoxNjg5MzMxOTU0LCJ1c2VybmFtZSI6InJvb3QifQ.61NgtudAvpOHk_CTcam2PO2dZHAtSfqcJWpcxfzgAyU";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Object id = claims.get("id");
        Object username = claims.get("username");
        System.out.println("id = " + id);
        System.out.println("username = " + username);
    }

}
```

嘗試解析JWT時，如果JWT已過期，則拋出異常：

```
io.jsonwebtoken.ExpiredJwtException: JWT expired at 2023-07-14T18:52:34Z. Current time: 2023-08-03T11:55:38Z, a difference of 1702984297 milliseconds.  Allowed clock skew: 0 milliseconds.
```

如果使用的`secretKey`與生成時的不一致，或JWT的編碼結果中某個值的錯誤，則拋出異常：

```
io.jsonwebtoken.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
```

如果JWT數據錯誤，導致無法正確解析，則拋出異常：

```
io.jsonwebtoken.MalformedJwtException: Unable to read JSON value: {"id":9527,"MM|b�\�":"root"}
```

**注意：**JWT本身並不對數據進行保密處理，在不知道`secretKey`的情況下仍是可以被解析出其中的數據的（某個編程API在`secretKey`不匹配時直接拋出異常，並不顯示解析結果），所以，不要在JWT中存放敏感數據。

# 基於JWT識別客戶端的身份 -- 分析

當需要在項目中實現“基於JWT識別客戶端的身份”，大致需要：

- 當某個客戶端向服務器端發起登錄請求後，如果服務器端驗證通過，會向客戶端響應一個JWT
- 後續，當客戶端向服務器端發起請求時，將攜帶此JWT，服務器端將嘗試解析JWT，並將成功解析的結果創建為`Authentication`對象存入到`SecurityContext`中

# 向客戶端響應JWT數據

在`UserLoginInfoVO`類中添加`id`屬性；

在`UserMapper.xml`中查詢的字段列表中補充查詢`id`屬性；

創建`CustomUserDetails`類，繼承自`User`類，在類中擴展出`id`屬性；

調整`UserDetailsServiceImpl`中的實現，將返回`CustomUserDetails`類型的結果；

調整`UserServiceImpl`中的實現，當通過登錄驗證後，返回的`Authentication`對象中的當事人（通過調用`getPrinciple()`得到）就是`UserDetailsServiceImpl`中返回的`CustomUserDetails`，則可以獲取用戶的`id`和`username`等數據，再將這些數據用於生成JWT並返回（需要修改`IUserService`和實現類中登錄方法的返回值類型）；

調整`UserController`，調用Service的登錄方法時獲取返回值，並響應到客戶端。

以上代碼請參考GIT提交記錄：

- 在項目中生成JWT（2023/8/3 at 14:37）
- 在項目中生成JWT（2023/8/3 at 15:24）

# 處理驗證登錄的流程

```mermaid
sequenceDiagram
    participant Client
    participant UserController
    participant UserService
    participant AuthenticationManager
    participant UserDetailsService
    participant IUserRepository
    participant UserMapper
  
    Client->>UserController: 發起請求，提交username與password
    UserController->>UserController: 封裝為UserLoginInfoParam
    UserController->>UserService: 傳入UserLoginInfoParam參數
    UserService->>AuthenticationManager: 處理UserLoginInfoParam為Authentication
    AuthenticationManager->>UserDetailsService: 調用loadUserByUsername()方法並傳入用戶名
    UserDetailsService->>IUserRepository: 調用IUserRepository
    IUserRepository->>UserMapper: 調用UserMapper
    UserMapper-->>IUserRepository: 返回UserLoginInfoVO
    IUserRepository-->>UserDetailsService: 返回UserLoginInfoVO
    UserDetailsService-->>AuthenticationManager: 返回UserDetails
    AuthenticationManager-->>UserService: 返回Authentication對象，其中當事人是UserDetails對象
    UserService->>UserController: 基於Authentication生成JWT並返回
    UserController-->>Client: 返回JWT
```

# 解析客戶端攜帶的JWT

Spring Security框架使用了大量的過濾器（`Filter`）處理認證與授權！

過濾器是Java EE中的核心組件之一，是最早處理請求的組件，其大致工作流程如下：

```
---(request)---> Filter(s) ------> Servlet ------> Controller
```

在每個項目中，都可以按需創建若幹個過濾器，形成過濾器鏈，每個過濾器都可以對請求進行“阻止”或“放行”，任何請求必須被所有過濾器“放行”才可以後續的組件被處理！

Spring Security處理`SecurityContext`也是通過過濾器組件來實現的，則處理JWT必須在Spring Security處理`SecurityContext`之前進行，就可以實現“先解析JWT並向`SecurityContext`中存入認證信息，然後框架讀取到認證信息”，則必須使用過濾器解析JWT，不可以使用其它組件，並且用於解析JWT的過濾器必須在Spring Security的某些過濾器之前執行！

自行創建過濾器類，可以直接實現`Filter`接口（不推薦），或繼承自`OncePerRequestFilter`類，例如：

```java
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    public JwtAuthorizationFilter() {
        log.debug("創建過濾器對象：JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JwtAuthorizationFilter.doFilterInternal()");

        // 放行
        filterChain.doFilter(request, response);
    }

}
```

並且，一定要在Spring Security的配置類中的`void configurer(HttpSecurity http)`中將自定義的過濾器添加在框架的過濾器鏈中的某個位置（否則，你的過濾器將在框架的過濾器之後執行，則沒有意義）：

![image-20230803163719997](images/image-20230803163719997.png)

關於使用Knife4j配置請求頭中的參數：

![image-20230803165002477](images/image-20230803165002477.png)

處理JWT的過濾器大致是：

```java
// 此過濾器的主要職責：
// 1. 嘗試接收客戶端攜帶的JWT
// 2. 嘗試解析JWT
// 3. 將解析結果創建為Authentication並存入到SecurityContext
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    // TODO 尚未解決的問題
    // 1. 未處理解析JWT時可能拋出的異常
    // 2. 未合理的處理“當事人”
    // 3. 未正確的存入“權限”

    public JwtAuthorizationFilter() {
        log.debug("創建過濾器對象：JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JwtAuthorizationFilter.doFilterInternal()");
        // 根據業內的慣例，客戶端提交的JWT會放在請求頭（Request Header）中名為Authorization的屬性中
        String jwt = request.getHeader("Authorization");
        log.debug("獲取客戶端攜帶的JWT：{}", jwt);

        // 檢查JWT的基本有效性
        if (!StringUtils.hasText(jwt)) {
            // 放行
            filterChain.doFilter(request, response);
            // 返回，避免代碼繼續向下執行（當前類中剩余的代碼）
            return;
        }

        // 嘗試解析JWT
        String secretKey = "k4^&32flj5Ss(Jf&*(5%DK3da";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        log.debug("id = {}", id);
        log.debug("username = {}", username);

        // 創建認證信息（Authentication）
        Object principal = username; // 當事人，可以是任何類型的數據
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("山寨權限");
        authorities.add(authority);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, null, authorities);

        // 將Authentication存入到SecurityContext
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // 放行
        filterChain.doFilter(request, response);
    }

}
```

由於默認情況下`SecurityContext`是基於Session的，當成功解析JWT且將認證信息存入到`SecurityContext`中以後，只要Session未過期，接下來的訪問即使不攜帶JWT，也可以通過認證。

可以進行配置，使得Spring Security永不使用Session：

![image-20230803175003787](images/image-20230803175003787.png)
