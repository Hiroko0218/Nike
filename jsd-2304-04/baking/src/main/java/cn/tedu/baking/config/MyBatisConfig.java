package cn.tedu.baking.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.baking.mapper")
public class MyBatisConfig {

    public MyBatisConfig() {
        System.out.println("MyBatisConfig.MyBatisConfig");
    }
}
