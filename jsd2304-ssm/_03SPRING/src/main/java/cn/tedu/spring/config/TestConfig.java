package cn.tedu.spring.config;

import cn.tedu.spring.auto.UserControllerAuto;
import cn.tedu.spring.file.DBConn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestConfig {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);
        // 1.獲取bean對象:DBConn
        DBConn dbConn = context.getBean(DBConn.class);
        System.out.println(dbConn);
        // 2.獲取bean對象:UserControllerRes
        UserControllerAuto res = context.getBean(UserControllerAuto.class);
        System.out.println(res);
    }
}
