package com.yafeng.nike.front.mall.service;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.CategoryTreeItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理類別業務的接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICategoryService {

    /**
     * 獲取所有類別形成的"樹"
     *
     * @return 所有類別形成的"樹"
     */
    List<CategoryTreeItemVO> listTree();

    /**
     * 根據父級類別查詢其子級類別列表，將使用默認的每頁記錄數
     *
     * @param parentId 父級類別的ID
     * @param pageNum  頁碼
     * @return 類別列表
     */
    PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum);

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
