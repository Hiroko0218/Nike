package cn.tedu.egmvc2.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.egmvc2.mapper")
public class MyBatisConfig {
}
