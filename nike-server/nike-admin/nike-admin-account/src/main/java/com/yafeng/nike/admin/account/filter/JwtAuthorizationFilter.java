package com.yafeng.nike.admin.account.filter;

import com.alibaba.fastjson.JSON;
import com.yafeng.nike.admin.account.dao.cache.IUserCacheRepository;
import com.yafeng.nike.common.consts.web.HttpConsts;
import com.yafeng.nike.common.consts.web.JwtConsts;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.po.UserStatePO;
import com.yafeng.nike.common.util.JwtUtils;
import com.yafeng.nike.common.web.JsonResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
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

/**
 * <p>處理JWT的過濾器</p>
 *
 * <p>此過濾器的主要作用：</p>
 * <ul>
 *     <li>嘗試接收客戶端的請求中攜帶的JWT數據</li>
 *     <li>嘗試解析JWT數據</li>
 *     <li>將解析得到的用戶數據創建為Authentication對象，並存入到SecurityContext中</li>
 * </ul>
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter implements HttpConsts, JwtConsts {

    @Value("${nike.jwt.secret-key}")
    private String secretKey;
    @Autowired
    private IUserCacheRepository userCacheRepository;

    public JwtAuthorizationFilter() {
        log.info("創建過濾器對象：JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        log.debug("處理JWT的過濾器開始處理當前請求……");
        // 嘗試接收客戶端的請求中攜帶的JWT數據
        String jwt = request.getHeader(HEADER_AUTHORIZATION);
        log.debug("客戶端攜帶的JWT：{}", jwt);

        // 判斷JWT的基本有效性（沒有必要嘗試解析格式明顯錯誤的JWT數據）
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH) {
            // 對於無效的JWT，應該直接放行
            log.warn("當前請求中，客戶端沒有攜帶有效的JWT，將放行");
            filterChain.doFilter(request, response);
            return;
        }

        // 嘗試解析JWT數據
        log.debug("嘗試解析JWT數據……");
        response.setContentType("application/json; charset=utf-8");
        Claims claims;
        try {
            claims = JwtUtils.parse(jwt, secretKey);
        } catch (ExpiredJwtException e) {
            log.warn("解析JWT時出現異常：ExpiredJwtException");
            String message = "操作失敗，您的登入信息已經過期，請重新登入！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (SignatureException e) {
            log.warn("解析JWT時出現異常：SignatureException");
            String message = "非法訪問，你的本次操作已經被記錄！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_SIGNATURE, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (MalformedJwtException e) {
            log.warn("解析JWT時出現異常：MalformedJwtException");
            String message = "非法訪問，你的本次操作已經被記錄！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_MALFORMED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        } catch (Throwable e) {
            log.warn("解析JWT時出現異常：", e);
            String message = "服務器忙，請稍後再試！【同學們，看到這句時，你應該檢查服務器端的控制台，並在JwtAuthorizationFilter中解析JWT時添加處理異常的catch代碼塊】";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        }

        // 從解析結果中獲取用戶的信息
        Long userId = claims.get(CLAIM_USER_ID, Long.class);
        String username = claims.get(CLAIM_USER_NAME, String.class);
        String userAgent = claims.get(CLAIM_USER_AGENT, String.class);
        String remoteAddr = claims.get(CLAIM_REMOTE_ADDR, String.class);
        log.debug("JWT中的用戶id = {}", userId);
        log.debug("JWT中的用戶名 = {}", username);
        log.debug("JWT中的瀏覽器信息 = {}", userAgent);
        log.debug("JWT中的IP地址 = {}", remoteAddr);

        // 判斷此次請求，與當初登入成功時的相關信息是否相同
        log.debug("開始檢查JWT是否存在盜用的問題……");
        if (!userAgent.equals(request.getHeader(HEADER_USER_AGENT))
                && !remoteAddr.equals(request.getRemoteAddr())) {
            // 本次請求的信息與當初登入時完全不同，則視為無效的JWT
            log.warn("本次請求的信息與當初登入時完全不同，將直接放行，交由後續的組件進行處理");
            filterChain.doFilter(request, response);
            return;
        }

        // 從緩存中讀取用戶登入信息
        log.debug("開始檢查緩存中用戶的狀態……");
        UserStatePO userState = userCacheRepository.getUserState(userId);
        // 判斷緩存中是否存在數據
        if (userState == null) {
            // 放行，不會向SecurityContext中存入認證信息，則相當於沒有攜帶JWT
            log.warn("在緩存中無此JWT對應的信息，將直接放行，交由後續的組件進行處理");
            filterChain.doFilter(request, response);
            return;
        }

        // 檢查用戶的啟用狀態
        Integer userEnable = userState.getEnable();
        if (userEnable != 1) {
            userCacheRepository.deleteUserState(userId);
            log.warn("用戶已被禁用");
            String message = "用戶已被禁用";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED_DISABLED, message);
            PrintWriter writer = response.getWriter();
            writer.println(JSON.toJSONString(jsonResult));
            writer.close();
            return;
        }

        // 從Redis中讀取當前用戶的權限列表
        String authoritiesJsonString = userState.getAuthoritiesJsonString();
        log.debug("從Redis中讀取當前用戶的權限列表 = {}", authoritiesJsonString);

        // 將解析得到的用戶數據創建為Authentication對象
        CurrentPrincipal principal = new CurrentPrincipal(); // 當事人
        principal.setId(userId);
        principal.setUsername(username);
        List<SimpleGrantedAuthority> authorities
                = JSON.parseArray(authoritiesJsonString, SimpleGrantedAuthority.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, null, authorities);

        // 將Authentication對象存入到SecurityContext中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // 過濾器鏈繼續執行，即：放行
        log.debug("驗證JWT完畢，已經向SecurityContext中存入認證信息，過濾器將放行");
        filterChain.doFilter(request, response);
    }

}
