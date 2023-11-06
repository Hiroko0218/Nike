package cn.tedu.spring.scope;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Scope(value = "singleton") //單實例
@Component
public class Student {
    private String name;

    /**
     * 第 1步
     */
    public Student(){
        System.out.println("01:bean對象創建,構造方法");
    }

    /**
     * 第 2步
     * @param name
     */
    @Value("趙麗穎")
    public void setName(String name) {
        this.name = name;
        System.out.println("02:屬性賦值,setter()方法");
    }

    /**
     * 第3步
     */
    @PostConstruct
    public void init(){
        System.out.println("03:執行初始化方法");
    }

    /**
     * 第5步
     */
    @PreDestroy
    public void destroy(){
        System.out.println("05:執行銷毀方法");
    }
}
