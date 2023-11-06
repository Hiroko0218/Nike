package cn.tedu.egmvc1.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.egmvc1.mapper")
public class MyBatisConfig {
}
