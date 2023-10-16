package com.yafeng.nike.front.content.dao.persist.repository;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.content.pojo.entity.Comment;
import com.yafeng.nike.front.content.pojo.vo.CommentListItemVO;

/**
 * 處理評論數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICommentRepository {

    /**
     * 插入評論數據
     *
     * @param comment 評論數據
     * @return 受影響的行數
     */
    int insert(Comment comment);

    /**
     * 根據id刪除評論數據
     *
     * @param id 評論ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據id修改評論數據
     *
     * @param comment 封裝了評論ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Comment comment);

    /**
     * 根據文章查詢評論列表
     *
     * @param articleId 文章ID
     * @param pageNum   頁碼
     * @param pageSize  每頁記錄數
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum, Integer pageSize);

}
