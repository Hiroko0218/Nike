package com.yafeng.nike.admin.mall.config;

import com.alibaba.fastjson.JSON;
import com.yafeng.nike.admin.mall.filter.JwtAuthorizationFilter;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * Spring Security的配置類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfiguration() {
        log.debug("創建配置類對象：SecurityConfiguration");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 啟用Spring Security的CorsFilter，允許跨域訪問
        http.cors();

        // 配置Spring Security不再使用Session
        // 注意：不要使用NEVER，它表示的是“不主動創建Session”，但是，當Session已存在時，仍會使用
        // 推薦使用STATELESS，它表示的是“無狀態”，無論是否存在Session都不會使用
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 將解析JWT的過濾器添加到Spring Security框架的過濾器鏈中
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // 處理無認證信息卻嘗試訪問需要通過認證的資源時的異常
        http.exceptionHandling().authenticationEntryPoint((request, response, e) -> {
            log.warn("Spring Security捕獲到異常，由AuthenticationEntryPoint進行處理：", e);
            String message = "當前未登入，或登入信息已過期，請登入！";
            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERROR_UNAUTHORIZED, message);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonResultString);
            printWriter.close();
        });

        // 白名單
        // 配置路徑時，使用Ant-Style匹配模式：
        // 使用1個星號可以匹配當前層級的任何內容，例如 /user/* 可以匹配 /user/delete、/user/list，但不可以匹配 /user/9527/delete
        // 使用2個星號可以匹配若幹層級的任何內容，例如 /user/** 可以匹配 /user/delete、/user/list、/user/9527/delete
        String[] urls = {
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs",
                "/favicon.ico",
        };

        // 禁用“防止偽造的跨域攻擊”的防禦機制（CSRF）
        http.csrf().disable();

        // 配置請求授權
        http.authorizeRequests()
                .mvcMatchers(urls) // 匹配某些請求
                .permitAll() // 許可，即不需要通過認證就可以訪問
                .anyRequest() // 任何請求，從執行效果來看，也可以視為：除了以上配置過的以外的其它請求
                .authenticated(); // 需要通過認證才可以訪問
    }

}