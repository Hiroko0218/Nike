package com.yafeng.nike.passport.service.impl;

import com.alibaba.fastjson.JSON;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.po.UserStatePO;
import com.yafeng.nike.common.util.JwtUtils;
import com.yafeng.nike.passport.dao.cache.IUserCacheRepository;
import com.yafeng.nike.passport.dao.persist.repository.ILoginLogRepository;
import com.yafeng.nike.passport.dao.persist.repository.IUserRepository;
import com.yafeng.nike.passport.pojo.entity.LoginLog;
import com.yafeng.nike.passport.pojo.param.UserLoginInfoParam;
import com.yafeng.nike.passport.pojo.vo.UserLoginResultVO;
import com.yafeng.nike.passport.security.CustomUserDetails;
import com.yafeng.nike.passport.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 處理用戶數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Value("${nike.jwt.secret-key}")
    private String secretKey;
    @Value("${nike.jwt.duration-in-minute}")
    private long durationInMinute;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ILoginLogRepository loginLogRepository;
    @Autowired
    private IUserCacheRepository userCacheRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserServiceImpl() {
        log.debug("創建業務類對象：UserServiceImpl");
    }

    @Override
    public UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam,
                                   String remoteAddr, String userAgent) {
        log.debug("開始處理【用戶登入】的業務，參數：{}", userLoginInfoParam);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginInfoParam.getUsername(), userLoginInfoParam.getPassword());
        log.debug("準備調用AuthenticationManager的認證方法，判斷此用戶名、密碼是否可以成功登入……");
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("驗證用戶登入成功，返回的認證結果：{}", authenticateResult);

        Object principal = authenticateResult.getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        Long userId = userDetails.getId();
        String username = userDetails.getUsername();
        String avatar = userDetails.getAvatar();
        Integer loginCount = userDetails.getLoginCount();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String authoritiesJsonString = JSON.toJSONString(authorities);

        LocalDateTime now = LocalDateTime.now();
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setUsername(username);
        loginLog.setIp(remoteAddr);
        loginLog.setUserAgent(userAgent);
        loginLog.setGmtLogin(now);
        int rows = loginLogRepository.insert(loginLog);
        if (rows != 1) {
            String message = "登入失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        rows = userRepository.updateLastLogin(userId, loginCount + 1, remoteAddr, now);
        if (rows != 1) {
            String message = "登入失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        Date expiration = new Date(System.currentTimeMillis() + 1L * 60 * 1000 * durationInMinute);
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, userId);
        claims.put(CLAIM_USER_NAME, username);
        claims.put(CLAIM_USER_AGENT, userAgent);
        claims.put(CLAIM_REMOTE_ADDR, remoteAddr);
        String jwt = JwtUtils.generate(claims, expiration, secretKey);
        log.debug("生成用戶的JWT數據：{}", jwt);

        UserStatePO userStatePO = new UserStatePO();
        userStatePO.setEnable(userDetails.isEnabled() ? 1 : 0);
        userStatePO.setAuthoritiesJsonString(authoritiesJsonString);
        userCacheRepository.saveUserState(userId, userStatePO);
        log.debug("向緩存中存入用戶狀態數據：{}", userStatePO);

        String[] authorityArray = new String[authorities.size()];
        int i = 0;
        for (GrantedAuthority authority : authorities) {
            authorityArray[i++] = authority.getAuthority();
        }
        UserLoginResultVO userLoginResultVO = new UserLoginResultVO()
                .setId(userId)
                .setUsername(username)
                .setAvatar(avatar)
                .setToken(jwt)
                .setAuthorities(authorityArray);
        log.debug("即將返回用戶的登入結果：{}", userLoginResultVO);
        return userLoginResultVO;
    }

    @Override
    public void logout(CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【退出登入】的業務，參數：{}", currentPrincipal);
        Long userId = currentPrincipal.getId();
        boolean deleteResult = userCacheRepository.deleteUserState(userId);
        if (!deleteResult) {
            String message = "操作失敗，用戶數據有誤！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
    }

}
