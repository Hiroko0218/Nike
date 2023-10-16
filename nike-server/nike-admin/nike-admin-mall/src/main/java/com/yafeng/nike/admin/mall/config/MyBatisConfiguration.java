package com.yafeng.nike.admin.mall.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis的配置類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Configuration
@MapperScan({
        "com.yafeng.nike.admin.mall.dao.persist.mapper"
})
public class MyBatisConfiguration {

    public MyBatisConfiguration() {
        log.info("創建配置類對象：MyBatisConfiguration");
    }
}
