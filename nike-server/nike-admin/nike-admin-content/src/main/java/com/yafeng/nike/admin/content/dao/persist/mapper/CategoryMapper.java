package com.yafeng.nike.admin.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.content.pojo.entity.Category;
import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CategoryStandardVO;
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
     * 根據id查詢類別數據詳情
     *
     * @param id 類別ID
     * @return 匹配的類別數據詳情，如果沒有匹配的數據，則返回null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 查詢類別數據列表
     *
     * @return 類別數據列表
     */
    List<CategoryListItemVO> list();

}
