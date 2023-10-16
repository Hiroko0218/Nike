package com.yafeng.nike.admin.content.service;

import com.yafeng.nike.admin.content.pojo.vo.CommentListItemVO;
import com.yafeng.nike.common.consts.data.ContentConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICommentService extends ContentConsts {

    /**
     * 審核通過評論
     *
     * @param currentPrincipal 當事人
     * @param id               嘗試審核通過的評論的ID
     * @param remarks          審核備注
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     * 拒絕審核評論
     *
     * @param currentPrincipal 當事人
     * @param id               嘗試取消審核的評論的ID
     * @param remarks          審核備注
     */
    void cancelCheck(CurrentPrincipal currentPrincipal, Long id, String remarks);

    /**
     * 顯示評論
     *
     * @param id 嘗試顯示的評論的ID
     */
    void setDisplay(Long id);

    /**
     * 隱藏（不顯示）評論
     *
     * @param id 嘗試隱藏的評論的ID
     */
    void setHidden(Long id);

    /**
     * 查詢文章的評論列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 文章的評論數據列表
     */
    PageData<CommentListItemVO> listByArticle(Integer pageNum);

    /**
     * 查詢文章的評論列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 文章的評論數據列表
     */
    PageData<CommentListItemVO> listByArticle(Integer pageNum, Integer pageSize);

    /**
     * 查詢評論的評論列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 評論的評論列表
     */
    PageData<CommentListItemVO> listByComment(Integer pageNum);

    /**
     * 查詢評論的評論列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 評論的評論列表
     */
    PageData<CommentListItemVO> listByComment(Integer pageNum, Integer pageSize);

}
