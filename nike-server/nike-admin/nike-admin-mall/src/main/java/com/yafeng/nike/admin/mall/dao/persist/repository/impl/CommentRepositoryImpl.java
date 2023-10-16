package com.yafeng.nike.admin.mall.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.admin.mall.dao.persist.mapper.CommentMapper;
import com.yafeng.nike.admin.mall.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.admin.mall.pojo.entity.Comment;
import com.yafeng.nike.admin.mall.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.CommentStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
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
    public int deleteByGoods(Long goodsId) {
        log.debug("開始執行【根據商品ID刪除評論】的數據訪問，參數：{}", goodsId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", goodsId);
        return commentMapper.delete(queryWrapper);
    }

    @Override
    public int update(Comment comment) {
        log.debug("開始執行【更新評論】的數據訪問，參數：{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public CommentStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢評論】的數據訪問，參數：{}", id);
        return commentMapper.getStandardById(id);
    }

    @Override
    public PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢評論列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.list();
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }
}
