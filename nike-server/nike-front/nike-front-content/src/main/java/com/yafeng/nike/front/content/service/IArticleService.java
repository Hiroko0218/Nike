package com.yafeng.nike.front.content.service;

import com.yafeng.nike.common.consts.data.ContentConsts;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.front.content.pojo.vo.ArticleStandardVO;
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
     * 根據ID查詢文章
     *
     * @param id 文章ID
     * @return 匹配的文章數據詳情，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的文章列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByRecommend(Integer pageNum);

    /**
     * 查詢推薦的文章列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章列表
     */
    PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

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

}
