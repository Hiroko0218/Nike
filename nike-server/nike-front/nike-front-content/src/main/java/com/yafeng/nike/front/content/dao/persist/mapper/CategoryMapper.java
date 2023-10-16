package com.yafeng.nike.front.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.front.content.pojo.entity.Category;
import com.yafeng.nike.front.content.pojo.vo.CategoryListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查詢類別數據列表
     *
     * @return 類別數據列表
     */
    List<CategoryListItemVO> list();

}
