package cn.tedu.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUserDao {
    public static void main(String[] args) {
        // 1.創建IoC容器,進行bean對象的掃描創建
        ApplicationContext context = new AnnotationConfigApplicationContext("cn.tedu.spring.bean");
        // 2.獲取UserDao的bean對象
        UserDao userDao = context.getBean(UserDao.class);
        System.out.println("userDao="+userDao);
        // 3.獲取UserService 的 bean對象
        UserService userService = context.getBean(UserService.class);
        System.out.println("userService=" + userService);
    }
}
