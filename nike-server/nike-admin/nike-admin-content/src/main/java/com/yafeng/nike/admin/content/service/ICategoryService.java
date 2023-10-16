package com.yafeng.nike.admin.content.service;

import com.yafeng.nike.admin.content.pojo.param.CategoryAddNewParam;
import com.yafeng.nike.admin.content.pojo.param.CategoryUpdateInfoParam;
import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.common.consts.data.ContentConsts;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理類別業務的接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICategoryService extends ContentConsts {

    /**
     * 添加類別
     *
     * @param categoryAddNewParam 新的類別數據
     */
    void addNew(CategoryAddNewParam categoryAddNewParam);

    /**
     * 根據ID刪除類別
     *
     * @param id 嘗試刪除的類別數據的ID
     */
    void delete(Long id);

    /**
     * 啟用類別
     *
     * @param id 嘗試啟用的類別的ID
     */
    void setEnable(Long id);

    /**
     * 禁用類別
     *
     * @param id 嘗試禁用的類別的ID
     */
    void setDisable(Long id);

    /**
     * 顯示類別
     *
     * @param id 嘗試顯示的類別的ID
     */
    void setDisplay(Long id);

    /**
     * 隱藏類別
     *
     * @param id 嘗試隱藏的類別的ID
     */
    void setHidden(Long id);

    /**
     * 修改類別數據
     *
     * @param id                      被修改的類別數據的ID
     * @param categoryUpdateInfoParam 類別的新數據
     */
    void updateInfoById(Long id, CategoryUpdateInfoParam categoryUpdateInfoParam);

    /**
     * 根據ID查詢類別詳情
     *
     * @param id 類別id
     * @return 匹配的類別詳情，如果沒有匹配的數據，則返回null
     */
    CategoryStandardVO getStandardById(Long id);



    /**
     * 查詢類別列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 類別列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum);

    /**
     * 查詢類別列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 類別列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 重建緩存
     */
    void rebuildCache();

}
