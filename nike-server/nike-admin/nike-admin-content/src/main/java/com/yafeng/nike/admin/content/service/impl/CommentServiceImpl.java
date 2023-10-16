package com.yafeng.nike.admin.content.service.impl;

import com.yafeng.nike.admin.content.dao.persist.repository.ICheckLogRepository;
import com.yafeng.nike.admin.content.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.admin.content.pojo.entity.CheckLog;
import com.yafeng.nike.admin.content.pojo.entity.Comment;
import com.yafeng.nike.admin.content.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CommentStandardVO;
import com.yafeng.nike.admin.content.service.ICommentService;
import com.yafeng.nike.common.consts.data.ContentConsts;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    private ICheckLogRepository checkLogRepository;

    public CommentServiceImpl() {
        log.info("創建業務對象：CommentServiceImpl");
    }

    @Override
    public void passCheck(CurrentPrincipal currentPrincipal, Long id, String remarks) {
        log.debug("開始處理【審核通過評論】的業務，當事人：{}，評論ID：{}，審核備注：{}", currentPrincipal, id, remarks);
        updateCheckById(currentPrincipal, id, CHECK_STATE_PASS, remarks);
    }

    @Override
    public void cancelCheck(CurrentPrincipal currentPrincipal, Long id, String remarks) {
        log.debug("開始處理【拒絕審核評論】的業務，當事人：{}，評論ID：{}，審核備注：{}", currentPrincipal, id, remarks);
        updateCheckById(currentPrincipal, id, CHECK_STATE_REJECT, remarks);
    }

    @Override
    public void setDisplay(Long id) {
        log.debug("開始處理【顯示評論】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_ON);
    }

    @Override
    public void setHidden(Long id) {
        log.debug("開始處理【不顯示評論】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_OFF);
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Integer pageNum) {
        log.debug("開始處理【查詢文章的評論列表】的業務，頁碼：{}", pageNum);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_ARTICLE, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢文章的評論列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_ARTICLE, pageNum, pageSize);
    }

    @Override
    public PageData<CommentListItemVO> listByComment(Integer pageNum) {
        log.debug("開始處理【查詢評論的評論列表】的業務，頁碼：{}", pageNum);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_COMMENT, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> listByComment(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢評論的評論列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return commentRepository.listByResourceType(ContentConsts.RESOURCE_TYPE_COMMENT, pageNum, pageSize);
    }

    /**
     * 根據ID修改評論的審核狀態
     *
     * @param currentPrincipal 當事人
     * @param id               評論ID
     * @param checkState       新狀態
     * @param remarks          審核備注
     */
    private void updateCheckById(CurrentPrincipal currentPrincipal, Long id, Integer checkState, String remarks) {
        CommentStandardVO currentComment = commentRepository.getStandardById(id);
        if (currentComment == null) {
            String message = "將評論的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，評論數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentComment.getCheckState().equals(checkState)) {
            String message = "將評論的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，此評論已經處於" + CHECK_STATE_TEXT[checkState] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Comment updateComment = new Comment();
        updateComment.setId(id);
        updateComment.setCheckState(checkState);
        int rows = commentRepository.update(updateComment);
        if (rows != 1) {
            String message = "將評論的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        String content = currentComment.getContent();
        String brief = content.length() < BRIEF_MAX_LENGTH ? content : content.substring(0, BRIEF_MAX_LENGTH);

        CheckLog checkLog = new CheckLog();
        checkLog.setResourceType(RESOURCE_TYPE_COMMENT);
        checkLog.setResourceId(id);
        checkLog.setResourceBrief(brief);
        checkLog.setCheckUserId(currentPrincipal.getId());
        checkLog.setCheckUsername(currentPrincipal.getUsername());
        checkLog.setCheckRemarks(remarks);
        checkLog.setOriginalState(currentComment.getCheckState());
        checkLog.setNewState(checkState);
        checkLog.setGmtCheck(LocalDateTime.now());
        rows = checkLogRepository.insert(checkLog);
        if (rows != 1) {
            String message = "將文章的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updateDisplayById(Long id, Integer isDisplay) {
        CommentStandardVO currentComment = commentRepository.getStandardById(id);
        if (currentComment == null) {
            String message = "將評論的顯示狀態修改為【" + DISPLAY_STATE_TEXT[isDisplay] + "】失敗，評論數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentComment.getIsDisplay().equals(isDisplay)) {
            String message = "將評論的顯示狀態修改為【" + DISPLAY_STATE_TEXT[isDisplay] + "】失敗，此評論已經處於" + DISPLAY_STATE_TEXT[isDisplay] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Comment updateComment = new Comment();
        updateComment.setId(id);
        updateComment.setIsDisplay(isDisplay);
        int rows = commentRepository.update(updateComment);
        if (rows != 1) {
            String message = "將評論的顯示狀態修改為【" + DISPLAY_STATE_TEXT[isDisplay] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

}
