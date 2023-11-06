package cn.tedu.egmybatis1;

import cn.tedu.egmybatis1.mapper.CategoryMapper;
import cn.tedu.egmybatis1.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class Egmybatis1ApplicationTests {

    // 自動裝配
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void insertCategoryTest(){
        Category category = new Category();
        category.setName("電子數碼");
        category.setIntro("電子產品");
        category.setCreated(new Date());
        categoryMapper.insertCategory(category);
    }

    @Test
    void selectAllTest(){
        System.out.println(categoryMapper.selectAll());
    }

    @Test
    void selectByIdTest(){
        System.out.println(categoryMapper.selectById(1));
    }

    @Test
    void updateCategoryByIdTest(){
        Category category = new Category();
        category.setId(1);
        category.setName("時尚女裝");
        category.setIntro("真好看");
        category.setCreated(new Date());
        categoryMapper.updateCategoryById(category);
    }

    @Test
    void deleteByIdTest(){
        System.out.println(categoryMapper.deleteById(1));
    }

}
