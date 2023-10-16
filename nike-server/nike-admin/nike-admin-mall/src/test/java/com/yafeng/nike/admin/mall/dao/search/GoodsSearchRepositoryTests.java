package com.yafeng.nike.admin.mall.dao.search;


import com.yafeng.nike.admin.mall.pojo.vo.GoodsSearchVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GoodsSearchRepositoryTests {

    @Autowired
    IGoodsSearchRepository repository;

    @Test
    void save() {
        GoodsSearchVO goodsSearchVO = new GoodsSearchVO();
        goodsSearchVO.setId(1L);
        goodsSearchVO.setCategoryName("綠茶");
        goodsSearchVO.setTitle("新款龍井上市，趕緊來買啊！");
        goodsSearchVO.setBrief("新龍井就是好喝");
        goodsSearchVO.setKeywords("綠茶,龍井,茶");
        goodsSearchVO.setSalePrice(new BigDecimal("998"));
        goodsSearchVO.setCommentCount(68);
        goodsSearchVO.setPositiveCommentCount(65);
        goodsSearchVO.setSalesCount(99);
        goodsSearchVO.setIsRecommend(1);
        goodsSearchVO.setGmtCreate(LocalDateTime.now());
        goodsSearchVO.setGmtModified(LocalDateTime.now());

        GoodsSearchVO result = repository.save(goodsSearchVO);
        System.out.println("向ES中寫入數據完成！返回結果：" + result);
    }

    @Test
    void findById() {
        Optional<GoodsSearchVO> optional = repository.findById(1L);
        GoodsSearchVO goodsSearchVO = optional.get();
        System.out.println(goodsSearchVO);
    }

    @Test
    void queryByTitle() {
        String keywords = "精裝";
        List<GoodsSearchVO> goodsList = repository.queryByTitle(keywords);
        for (GoodsSearchVO goodsSearchVO : goodsList) {
            System.out.println(goodsSearchVO);
        }
    }

    @Test
    void customQuery() {
        String keywords = "龍井";
        List<GoodsSearchVO> goodsList = repository.customQuery(keywords);
        for (GoodsSearchVO goodsSearchVO : goodsList) {
            System.out.println(goodsSearchVO);
        }
    }
}

