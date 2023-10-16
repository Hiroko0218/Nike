package com.yafeng.nike.admin.content.service;

import com.yafeng.nike.admin.content.pojo.param.ArticleAddNewParam;
import com.yafeng.nike.admin.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.ArticleStandardVO;
import com.yafeng.nike.common.consts.data.ContentConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理文章數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IArticleService extends ContentConsts {

    /**
     * 發布文章
     *
     * @param currentPrincipal   當事人
     * @param remoteAddr         IP地址
     * @param articleAddNewParam 新的文章數據
     */
    void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleAddNewParam articleAddNewParam);

    /**
     * 根據ID刪除文章
     *
     * @param id 嘗試刪除的文章數據的ID
     */
    void delete(Long id);

    /**
     * 審核通過文章
     *
     * @param currentPrincipal 當事人
     * @param id               嘗試審核通過的文章的ID
     * @param remarks          審核備注
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     * 拒絕審核文章
     *
     * @param currentPrincipal 當事人
     * @param id               嘗試拒絕審核的文章的ID
     * @param remarks          審核備注
     */
    void rejectCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     * 顯示文章
     *
     * @param id 嘗試顯示的文章的ID
     */
    void setDisplay(Long id);

    /**
     * 隱藏（不顯示）文章
     *
     * @param id 嘗試隱藏的文章的ID
     */
    void setHidden(Long id);

    /**
     * 根據ID查詢文章
     *
     * @param id 文章ID
     * @return 匹配的文章數據詳情，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢文章列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum);

    /**
     * 查詢文章列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根據類別查詢文章列表，將使用默認的每頁記錄數
     *
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum);

    /**
     * 根據類別查詢文章列表
     *
     * @param categoryId 文章類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

    /**
     * 重建搜索數據（重新從數據庫中獲取數據並寫入到ES中）
     */
    void rebuildSearch();

}
