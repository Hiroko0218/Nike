package com.yafeng.nike.front.content.dao.persist.repository;


import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.front.content.pojo.vo.ArticleStandardVO;

/**
 * 處理文章數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IArticleRepository {

    /**
     * 設置文章的評論數
     *
     * @param articleId    文章ID
     * @param commentCount 評論數的新值
     * @return 受影響的行數
     */
    int setCommentCount(Long articleId, Integer commentCount);

    /**
     * 根據id查詢文章數據詳情
     *
     * @param id 文章ID
     * @return 匹配的文章數據詳情，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的文章列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章數據列表
     */
    PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

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
