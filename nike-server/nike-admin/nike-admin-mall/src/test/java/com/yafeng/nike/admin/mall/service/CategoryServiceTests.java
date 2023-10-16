package com.yafeng.nike.admin.mall.service;

import com.yafeng.nike.admin.mall.pojo.param.CategoryAddNewParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    ICategoryService service;

    @Test
    void addNew(){
        CategoryAddNewParam categoryAddNewParam = new CategoryAddNewParam();
        categoryAddNewParam.setParentId(26L);
        categoryAddNewParam.setName("瓜子");

        try{
            service.addNew(categoryAddNewParam);
            System.out.println("新增類別，成功!");
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
