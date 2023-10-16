package com.yafeng.nike.front.mall.dao.persist.repository;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.entity.Comment;
import com.yafeng.nike.front.mall.pojo.vo.CommentListItemVO;

/**
 * 處理商品評論數據的存儲庫接口
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
     * 根據商品查詢評論數據列表
     *
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論數據列表
     */
    PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize);

}
