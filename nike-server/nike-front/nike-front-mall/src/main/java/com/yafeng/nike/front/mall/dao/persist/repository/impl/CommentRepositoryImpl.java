package com.yafeng.nike.front.mall.dao.persist.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import com.yafeng.nike.front.mall.dao.persist.mapper.CommentMapper;
import com.yafeng.nike.front.mall.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.front.mall.pojo.entity.Comment;
import com.yafeng.nike.front.mall.pojo.vo.CommentListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品評論數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class CommentRepositoryImpl implements ICommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    public CommentRepositoryImpl() {
        log.info("創建存儲庫對象：CommentRepositoryImpl");
    }

    @Override
    public int insert(Comment comment) {
        log.debug("開始執行【插入評論】的數據訪問，參數：{}", comment);
        return commentMapper.insert(comment);
    }

    @Override
    public PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據商品查詢評論數據列表】的數據訪問，商品ID：{}，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.list(goodsId);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
