package cn.tedu.spring.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestStudent {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext
                ("cn.tedu.spring.scope");
//        // 第一次獲取bean對象
//        Student s1 = context.getBean(Student.class);
//        System.out.println(s1);
//        // 第二次獲取bean對象
//        Student s2 = context.getBean(Student.class);
//        System.out.println(s2);

        /**
         * 第4步
         */
        Student student = context.getBean(Student.class);
        System.out.println("04-bean對象使用階段");

        //銷毀方法
        context.close();
    }
}
