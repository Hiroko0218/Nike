package cn.tedu.spring.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    public static void main(String[] args) {
        // 1.創建java bean對象
        ApplicationContext context = new AnnotationConfigApplicationContext("cn.tedu.spring.example");
        // 2.獲取user對象
        User user = context.getBean(User.class);
        System.out.println(user);
        user.run();
    }
}
