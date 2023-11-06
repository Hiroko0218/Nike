package cn.tedu._053mvcweibo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu._053mvcweibo.mapper")
public class MyBatisConfig {
}
