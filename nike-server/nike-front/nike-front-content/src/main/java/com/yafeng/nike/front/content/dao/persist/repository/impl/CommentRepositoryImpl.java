package com.yafeng.nike.front.content.dao.persist.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import com.yafeng.nike.front.content.dao.persist.mapper.CommentMapper;
import com.yafeng.nike.front.content.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.front.content.pojo.entity.Comment;
import com.yafeng.nike.front.content.pojo.vo.CommentListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論數據的存儲庫實現類
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
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除評論】的數據訪問，參數：{}", id);
        return commentMapper.deleteById(id);
    }

    @Override
    public int update(Comment comment) {
        log.debug("開始執行【更新評論】的數據訪問，參數：{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public PageData<CommentListItemVO> listByArticle(Long articleId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據文章查詢評論列表】的數據訪問，文章：{}，頁碼：{}，每頁記錄數：{}", articleId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.listByArticle(articleId);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        PageData<CommentListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}
