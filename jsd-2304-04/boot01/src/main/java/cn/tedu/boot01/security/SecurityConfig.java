package cn.tedu.boot01.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //配置認證管理器
    @Bean //添加此註解是為了能夠在Controller中自動裝配
    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    //配置密碼的加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //下面代碼是獲取了一個不加密的實例
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        super.configure(http);
        //配置自己的登入頁面當判斷没有登入的時候 自動跳轉到自己的登入頁面
        http.formLogin().loginPage("/login.html");

        //配置白名單(無需登入也可以訪問的資源)
        String[] urls = {"/reg.html","/login.html","/reg","/login"};
        http.authorizeRequests()
                .mvcMatchers(urls)//匹配某些路徑
                .permitAll() //直接放行(不需要登入可以訪問)
                .anyRequest()//其它請求
                .authenticated();  //需要登入認證才能訪問

        //關閉默認開啟的跨域攻擊防禦
        http.csrf().disable();
    }
}
