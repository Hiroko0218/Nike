package com.yafeng.nike.admin.content.dao.persist.repository;


import com.yafeng.nike.admin.content.pojo.entity.Article;
import com.yafeng.nike.admin.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.ArticleStandardVO;
import com.yafeng.nike.admin.content.pojo.vo.search.ArticleSearchVO;
import com.yafeng.nike.common.pojo.vo.PageData;

import java.util.Collection;

/**
 * 處理文章數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IArticleRepository {

    /**
     * 插入文章數據
     *
     * @param article 文章數據
     * @return 受影響的行數
     */
    int insert(Article article);

    /**
     * 根據ID刪除文章數據
     *
     * @param id 文章ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若幹個ID批量刪除文章數據
     *
     * @param idList 若幹個文章ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據ID修改文章數據
     *
     * @param article 封裝了文章ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Article article);

    /**
     * 根據類別統計文章數量
     *
     * @param categoryId 類別ID
     * @return 歸屬此類別下的文章數量
     */
    int countByCategory(Long categoryId);

    /**
     * 根據ID查詢文章數據詳情
     *
     * @param id 文章ID
     * @return 匹配的文章數據詳情，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢文章數據列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章數據列表
     */
    PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 查詢文章數據列表，用於讀取數據後存入到elasticsearch中
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章數據列表
     */
    PageData<ArticleSearchVO> listSearchVO(Integer pageNum, Integer pageSize);

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
