package com.yafeng.nike.front.mall.dao.persist.repository;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.vo.CategoryListItemVO;

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

    /**
     * 根據父級類別查詢其子級類別列表
     *
     * @param parentId 父級類別的ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 類別列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize);

}
