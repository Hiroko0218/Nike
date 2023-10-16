package com.yafeng.nike.front.content.dao.persist.repository;


import com.yafeng.nike.front.content.pojo.vo.CategoryListItemVO;

import java.util.List;

/**
 * 處理類別數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICategoryRepository {

    /**
     * 查詢類別數據列表
     *
     * @return 類別數據列表
     */
    List<CategoryListItemVO> list();

}
