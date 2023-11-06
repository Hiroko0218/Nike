package cn.tedu.spring.auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 1. 接口對應的唯一實現類上添加: @Component注解、
 * 2. UserControllerAuto類中添加: @Component注解,交由Spring管理,
 *   實現對象類型數據屬性注入( @Autowired 中指定接口名字 )
 * 3. 測試類進行測試
 */
public class TestUserControllerAuto {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("cn.tedu.spring.auto");
        UserControllerAuto userControllerAuto =context.getBean(UserControllerAuto.class);
        System.out.println(userControllerAuto);
    }
}
