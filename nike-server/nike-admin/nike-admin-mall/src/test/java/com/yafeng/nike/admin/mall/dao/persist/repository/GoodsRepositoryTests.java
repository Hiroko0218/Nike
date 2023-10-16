package com.yafeng.nike.admin.mall.dao.persist.repository;

import com.yafeng.nike.admin.mall.pojo.vo.GoodsSearchVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsRepositoryTests {

    @Autowired
    IGoodsRepository repository;

    @Test
    void listSearch() {
        Integer pageNum = 1;
        Integer pageSize = 4;
        PageData<GoodsSearchVO> pageData = repository.listSearch(pageNum, pageSize);
        System.out.println(pageData);
    }

}
