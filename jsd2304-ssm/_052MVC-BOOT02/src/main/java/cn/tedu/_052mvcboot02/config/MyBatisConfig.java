package cn.tedu._052mvcboot02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

//設置為配置類,會自動加載
@Configuration
//設置自動掃描,會自動為mapper包下的接口創建實現類
@MapperScan("cn.tedu._052mvcboot02.mapper")
public class MyBatisConfig {
}
