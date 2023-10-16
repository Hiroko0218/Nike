package com.yafeng.nike.front.content.service.impl;

import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.content.dao.persist.repository.IArticleRepository;
import com.yafeng.nike.front.content.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.front.content.pojo.entity.Comment;
import com.yafeng.nike.front.content.pojo.param.ArticleCommentAddNewParam;
import com.yafeng.nike.front.content.pojo.vo.ArticleStandardVO;
import com.yafeng.nike.front.content.pojo.vo.CommentListItemVO;
import com.yafeng.nike.front.content.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理評論數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Value("${nike.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IArticleRepository articleRepository;

    public CommentServiceImpl() {
        log.info("創建業務對象：CommentServiceImpl");
    }

    @Override
    public void addNewArticleComment(CurrentPrincipal currentPrincipal, String remoteAddr, ArticleCommentAddNewParam articleCommentAddNewParam) {
        log.debug("開始處理【發表文章評論】的業務，參數：{}", articleCommentAddNewParam);

        Long articleId = articleCommentAddNewParam.getArticleId();
        ArticleStandardVO article = articleRepository.getStandardById(articleId);
        if (article == null) {
            String message = "發表文章評論失敗，文章數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        Comment comment = new Comment();
        BeanUtils.copyProperties(articleCommentAddNewParam, comment);
        comment.setAuthorId(currentPrincipal.getId());
        comment.setAuthorName(currentPrincipal.getUsername());
        comment.setIp(remoteAddr);
        comment.setFloor(article.getCommentCount() + 1);
        comment.setUpCount(0);
        comment.setDownCount(0);
        comment.setCheckState(0);
        comment.setIsDisplay(0);
        int rows = commentRepository.insert(comment);
        if (rows != 1) {
            String message = "發表文章評論失敗，服務器忙，請稍後再試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        rows = articleRepository.setCommentCount(articleId, article.getCommentCount() + 1);
        if (rows != 1) {
            String message = "發表文章評論失敗，服務器忙，請稍後再試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum) {
        log.debug("開始處理【根據文章查詢評論列表】的業務，文章：{}, 頁碼：{}", articleId, pageNum);
        PageData<CommentListItemVO> pageData = commentRepository.listByArticle(articleId, pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據文章查詢評論列表】的業務，文章：{}, 頁碼：{}，每頁記錄數：{}", articleId, pageNum, pageSize);
        PageData<CommentListItemVO> pageData = commentRepository.listByArticle(articleId, pageNum, pageSize);
        return pageData;
    }

}
