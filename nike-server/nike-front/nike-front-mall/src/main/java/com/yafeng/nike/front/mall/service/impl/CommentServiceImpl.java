package com.yafeng.nike.front.mall.service.impl;

import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.front.mall.dao.persist.repository.IGoodsRepository;
import com.yafeng.nike.front.mall.pojo.entity.Comment;
import com.yafeng.nike.front.mall.pojo.entity.Goods;
import com.yafeng.nike.front.mall.pojo.param.CommentAddNewParam;
import com.yafeng.nike.front.mall.pojo.vo.CommentListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.GoodsStandardVO;
import com.yafeng.nike.front.mall.service.ICommentService;
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
    private IGoodsRepository goodsRepository;

    public CommentServiceImpl() {
        log.debug("創建業務類對象：CommentServiceImpl");
    }

    @Override
    public void addNewGoodsComment(CurrentPrincipal currentPrincipal,String remoteAddr, CommentAddNewParam commentAddNewParam) {
        log.debug("開始處理【發表評論】的業務，參數：{}", commentAddNewParam);

        Long goodsId = commentAddNewParam.getGoodsId();
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(goodsId);
        if (currentGoods == null) {
            String message = "發表評論失敗，商品數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentGoods.getIsPutOn() == PUT_ON_STATE_OFF) {
            String message = "發表評論失敗，此商品已經下架！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        // TODO 另需檢查是否購買過此商品
        // TODO 另需檢查是否已經評論過此商品

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam, comment);
        comment.setAuthorId(currentPrincipal.getId());
        comment.setAuthorName(currentPrincipal.getUsername());
        comment.setCheckState(0);
        int rows = commentRepository.insert(comment);
        if (rows != 1) {
            String message = "發表評論失敗，服務器忙，請稍後再試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(goodsId);
        updateGoods.setCommentCount(currentGoods.getCommentCount() + 1);
//        switch (commentAddNewParam.getType()) {
//            case COMMENT_TYPE_POSITIVE:
//                updateGoods.setPositiveCommentCount(updateGoods.getPositiveCommentCount()+1);
//                break;
//            case COMMENT_TYPE_NEGATIVE:
//                updateGoods.setNegativeCommentCount(updateGoods.getNegativeCommentCount()+1);
//                break;
//        }

        rows = goodsRepository.update(updateGoods);
        if (rows != 1) {
            String message = "發表評論失敗，服務器忙，請稍後再試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public PageData<CommentListItemVO> list(Long goodsId, Integer pageNum) {
        log.debug("開始處理【查詢商品的評論列表】的業務，頁碼：{}", pageNum);
        return commentRepository.list(goodsId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢商品的評論列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return commentRepository.list(goodsId, pageNum, pageSize);
    }

}