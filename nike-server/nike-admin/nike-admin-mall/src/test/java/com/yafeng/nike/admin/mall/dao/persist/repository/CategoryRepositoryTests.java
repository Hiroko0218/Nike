package com.yafeng.nike.admin.mall.dao.persist.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTests {

    @Autowired
    ICategoryRepository repository;

    @Test
    void  countByName(){
        String name = "hehe";
        int countByName = repository.countByName(name);
        System.out.println("統計完成，结果：" + countByName);
    }
}
