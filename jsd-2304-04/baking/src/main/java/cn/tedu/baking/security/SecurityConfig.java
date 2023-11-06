package cn.tedu.baking.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//開啟方法的授權檢測功能
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //配置認證管理器
    @Bean //添加此註解是為了能夠在Controller中自動裝配
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    //配置密碼的加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //下面代碼是獲取了一個不加密的實例
//        return NoOpPasswordEncoder.getInstance();
        //哈希加密算法  單向加密不可逆
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        super.configure(http);
        //配置自己的登入頁面當判斷没有登入的時候 自動跳轉到自己的登入頁面
        http.formLogin().loginPage("/login.html");

        /* 配置白名單(無需登入也可以訪問的資源) */
//        String[] urls = {"/reg.html","/login.html","/reg","/login"};
//        http.authorizeRequests()
//                .mvcMatchers(urls)//匹配某些路徑
//                .permitAll() //直接放行(不需要登入可以訪問)
//                .anyRequest()//其它請求
//                .authenticated();  //需要登入認證才能訪問

        /* 配置黑名單(登入之前禁止訪問的頁面) */
        String[] urls={"/admin.html","/personal.html",
                "/postArticle.html","/articleManagement.html",
                "/**/delete","/**/add-new","/**/update","/v1/users/"
        };
        http.authorizeRequests()
                .mvcMatchers(urls).authenticated()//匹配指定的路徑,需要登入認證後訪問
                .anyRequest().permitAll();//其它所有路徑直接放行(不需要登入)
        //關閉默認開啟的跨域攻擊防禦
        http.csrf().disable();
    }
}
