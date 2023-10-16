package com.yafeng.nike.admin.content.dao.persist.repository;

import com.yafeng.nike.admin.content.pojo.entity.Comment;
import com.yafeng.nike.admin.content.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CommentStandardVO;
import com.yafeng.nike.common.consts.data.ContentConsts;
import com.yafeng.nike.common.pojo.vo.PageData;

import java.util.Collection;

/**
 * 處理評論數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICommentRepository extends ContentConsts {

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
     * 根據若幹個ID批量刪除評論數據
     *
     * @param idList 若幹個評論ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據文章刪除評論數據
     *
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return 受影響的行數
     */
    int deleteByResource(Integer resourceType, Long resourceId);

    /**
     * 根據id修改評論數據
     *
     * @param comment 封裝了評論ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Comment comment);

    /**
     * 根據資源統計評論表中的數據的數量
     *
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return 文章匹配的評論數據的數量
     */
    int countByResource(Integer resourceType, Long resourceId);

    /**
     * 根據id查詢評論數據詳情
     *
     * @param id 評論ID
     * @return 匹配的評論數據詳情，如果沒有匹配的數據，則返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論數據列表
     *
     * @param resourceType 資源類型
     * @param pageNum      頁碼
     * @param pageSize     每頁記錄數
     * @return 評論數據列表
     */
    PageData<CommentListItemVO> listByResourceType(Integer resourceType, Integer pageNum, Integer pageSize);

}
