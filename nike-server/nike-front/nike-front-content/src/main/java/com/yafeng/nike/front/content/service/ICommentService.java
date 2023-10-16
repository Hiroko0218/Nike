package com.yafeng.nike.front.content.service;

import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.content.pojo.param.ArticleCommentAddNewParam;
import com.yafeng.nike.front.content.pojo.vo.CommentListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICommentService {

    /**
     * 發表文章評論
     *
     * @param currentPrincipal          當事人
     * @param remoteAddr                IP地址
     * @param articleCommentAddNewParam 新增的評論數據
     */
    void addNewArticleComment(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleCommentAddNewParam articleCommentAddNewParam);

    /**
     * 根據文章查詢評論列表，將使用默認的每頁記錄數
     *
     * @param articleId 文章的ID
     * @param pageNum   頁碼
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum);

    /**
     * 根據文章查詢評論列表
     *
     * @param articleId 文章的ID
     * @param pageNum   頁碼
     * @param pageSize  每頁記錄數
     * @return 評論列表
     */
    PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum, Integer pageSize);

}
