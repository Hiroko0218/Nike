package com.yafeng.nike.admin.mall.dao.persist.repository;


import com.yafeng.nike.admin.mall.pojo.entity.Comment;
import com.yafeng.nike.admin.mall.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.CommentStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;

/**
 * 處理商品評論數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICommentRepository {

    /**
     * 根據商品ID刪除評論
     *
     * @param goodsId 商品ID
     * @return 受影響的行數
     */
    int deleteByGoods(Long goodsId);

    /**
     * 根據ID修改評論數據
     *
     * @param comment 封裝了評論ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Comment comment);

    /**
     * 根據ID查詢評論
     *
     * @param id 評論ID
     * @return 匹配的評論，如果沒有匹配的數據，則返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論數據列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論數據列表
     */
    PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize);

}
