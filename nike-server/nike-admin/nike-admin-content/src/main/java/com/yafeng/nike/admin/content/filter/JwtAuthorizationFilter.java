package com.yafeng.nike.admin.content.filter;

import com.alibaba.fastjson.JSON;
import com.yafeng.nike.admin.content.dao.cache.IUserCacheRepository;
import com.yafeng.nike.common.consts.web.HttpConsts;
import com.yafeng.nike.common.consts.web.JwtConsts;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.po.UserStatePO;
import com.yafeng.nike.common.web.JsonResult;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// 此過濾器的主要職責：
// 1. 嘗試接收客戶端攜帶的JWT
// 2. 嘗試解析JWT
// 3. 將解析結果創建為Authentication並存入到SecurityContext
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter implements HttpConsts, JwtConsts {

    @Value("${nike.jwt.secret-key}")
    private String secretKey;

    @Autowired
    private IUserCacheRepository userCacheRepository;

    public JwtAuthorizationFilter() {
        log.debug("創建過濾器對象：JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        log.debug("處理JWT的過濾器開始處理當前請求……");
        // 根據業內的慣例，客戶端提交的JWT會放在請求頭（Request Header）中名為Authorization的屬性中
        String jwt = request.getHeader(HEADER_AUTHORIZATION);
        log.debug("獲取客戶端攜帶的JWT：{}", jwt);

        // 檢查JWT的基本有效性（沒有必要嘗試解析格式明顯錯誤的JWT數據）
        if (!StringUtils.hasText(jwt)|| jwt.length() < JWT_MIN_LENGTH) {
            // 對於無效的JWT，應該直接放行
            log.warn("當前請求中，客戶端沒有攜帶有效的JWT，將放行");
            filterChain.doFilter(request, response);
            // 返回，避免代碼繼續向下執行（當前類中剩余的代碼）
            return;
        }

        // 嘗試解析JWT
        log.debug("嘗試解析JWT數據……");
        response.setContentType("application/json; charset=utf-8");
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.warn("解析JWT時出現異常：ExpiredJwtException");
            String message = "操作失敗，您的登入信息已經過期，請重新登入！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED, message);
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonResultString);
            printWriter.close();
            return;
        } catch (SignatureException e) {
            log.warn("解析JWT時出現異常：SignatureException");
            String message = "非法訪問，你的本次操作已經被記錄！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_SIGNATURE, message);
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonResultString);
            printWriter.close();
            return;
        } catch (MalformedJwtException e) {
            log.warn("解析JWT時出現異常：MalformedJwtException");
            String message = "非法訪問，你的本次操作已經被記錄！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_MALFORMED, message);
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonResultString);
            printWriter.close();
            return;
        } catch (Throwable e) {
            log.debug("全局異常處理器開始處理Throwable");
            log.debug("異常跟蹤信息如下：", e);
            String message = "服務器忙，請稍後再試！【同學們，看到這句時，你應該檢查服務器端的控制台，並在JwtAuthorizationFilter中補充catch代碼塊進行處理】";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonResultString);
            printWriter.close();
            return;
        }

        // 獲取JWT中的數據
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        String jwtRemoteAddr = claims.get("remoteAddr", String.class);
        String jwtUserAgent = claims.get("userAgent", String.class);
        log.debug("JWT中的用戶id = {}", id);
        log.debug("JWT中的用戶名 username = {}", username);
        log.debug("JWT中的IP地址 remoteAddr = {}", jwtRemoteAddr);
        log.debug("JWT中的瀏覽器信息 userAgent = {}", jwtUserAgent);

        // 檢查是否盜用JWT：IP地址和瀏覽器信息均不匹配
        log.debug("開始檢查JWT是否存在盜用的問題……");
        String currentRemoteAddr = request.getRemoteAddr();
        String currentUserAgent = request.getHeader("User-Agent");
        if (!currentRemoteAddr.equals(jwtRemoteAddr)
                && !currentUserAgent.equals(jwtUserAgent)) {
            log.debug("本次請求疑似盜用JWT的請求，IP地址：{}，瀏覽器信息：{}", currentRemoteAddr, currentUserAgent);
            filterChain.doFilter(request, response);
            return;
        }

        // 從Redis中讀取用戶狀態數據
        log.debug("開始檢查緩存中用戶的狀態……");
        UserStatePO userState = userCacheRepository.getUserState(id);
        // 判斷緩存中是否存在數據
        if (userState == null) {
            // 放行，不會向SecurityContext中存入認證信息，則相當於沒有攜帶JWT
            log.warn("在緩存中無此JWT對應的信息，將直接放行，交由後續的組件進行處理");
            filterChain.doFilter(request, response);
            return;
        }

        // 判斷用戶賬號的狀態（是否啟用）
        if (userState.getEnable() != 1) {
            userCacheRepository.deleteUserState(id);
            String message = "您的帳號已經被禁用！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonResultString);
            printWriter.close();
            return;
        }

        // 創建當事人對象
        CurrentPrincipal principal = new CurrentPrincipal();
        principal.setId(id);
        principal.setUsername(username);

        // 準備當前用戶的權限列表
        String authoritiesJsonString = userState.getAuthoritiesJsonString();
        List<SimpleGrantedAuthority> authorities
                = JSON.parseArray(authoritiesJsonString, SimpleGrantedAuthority.class);

        // 創建認證信息（Authentication）
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, null, authorities);

        // 將Authentication存入到SecurityContext
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // 放行
        log.debug("驗證JWT完畢，已經向SecurityContext中存入認證信息，過濾器將放行");
        filterChain.doFilter(request, response);
    }

}
