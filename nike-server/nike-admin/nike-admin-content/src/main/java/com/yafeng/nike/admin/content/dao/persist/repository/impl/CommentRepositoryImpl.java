package com.yafeng.nike.admin.content.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.admin.content.dao.persist.mapper.CommentMapper;
import com.yafeng.nike.admin.content.dao.persist.repository.ICommentRepository;
import com.yafeng.nike.admin.content.pojo.entity.Comment;
import com.yafeng.nike.admin.content.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CommentStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【批量刪除評論】的數據訪問，參數：{}", idList);
        return commentMapper.deleteBatchIds(idList);
    }

    @Override
    public int deleteByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根據資源刪除評論數據】的數據訪問，資源類型：{}，資源ID：{}", resourceType, resourceId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType)
                .eq("resource_id", resourceId);
        return commentMapper.delete(queryWrapper);
    }

    @Override
    public int update(Comment comment) {
        log.debug("開始執行【更新評論】的數據訪問，參數：{}", comment);
        return commentMapper.updateById(comment);
    }

    @Override
    public int countByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根據資源統計評論表中的數據的數量】的數據訪問，資源類型：{}，資源ID：{}", resourceType, resourceId);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType)
                .eq("resource_id", resourceId);
        return commentMapper.selectCount(queryWrapper);
    }

    @Override
    public CommentStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢評論信息】的數據訪問，參數：{}", id);
        return commentMapper.getStandardById(id);
    }

    @Override
    public PageData<CommentListItemVO> listByResourceType(Integer resourceType, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢評論列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CommentListItemVO> list = commentMapper.listByResourceType(resourceType);
        PageInfo<CommentListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
