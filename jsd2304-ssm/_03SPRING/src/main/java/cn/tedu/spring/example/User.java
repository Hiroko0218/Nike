package cn.tedu.spring.example;

import org.springframework.stereotype.Component;

/**
 * 注解描述的類,表示此類交给Spring框架進行管理
 */
@Component
public class User {
    public void run(){
        System.out.println("也許時間是一種解藥");
    }
}
