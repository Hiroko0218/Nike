package com.yafeng.nike.front.mall.dao.persist.mapper;

import com.yafeng.nike.front.mall.pojo.entity.Category;
import com.yafeng.nike.front.mall.pojo.vo.CategoryListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    /**
     * 根據父級類別查詢其子級類別列表
     *
     * @param parentId 父級類別的ID
     * @return 類別列表
     */
    List<CategoryListItemVO> listByParent(Long parentId);

}
