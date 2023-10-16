package com.yafeng.nike.admin.content.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yafeng.nike.admin.content.dao.persist.mapper.ArticleDetailMapper;
import com.yafeng.nike.admin.content.dao.persist.repository.IArticleDetailRepository;
import com.yafeng.nike.admin.content.pojo.entity.ArticleDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 處理文章詳情數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class ArticleDetailRepositoryImpl implements IArticleDetailRepository {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    public ArticleDetailRepositoryImpl() {
        log.info("創建存儲庫對象：ArticleDetailRepositoryImpl");
    }

    @Override
    public int insert(ArticleDetail articleDetail) {
        log.debug("開始執行【插入文章詳情】的數據訪問，參數：{}", articleDetail);
        return articleDetailMapper.insert(articleDetail);
    }

    @Override
    public int deleteByArticle(Long articleId) {
        log.debug("開始執行【根據文章ID刪除文章詳情數據】的數據訪問，參數：{}", articleId);
        QueryWrapper<ArticleDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleId);
        return articleDetailMapper.delete(queryWrapper);
    }

    @Override
    public int updateByArticle(ArticleDetail articleDetail) {
        log.debug("開始執行【更新文章詳情】的數據訪問，參數：{}", articleDetail);
        QueryWrapper<ArticleDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", articleDetail.getArticleId());
        return articleDetailMapper.update(articleDetail, queryWrapper);
    }

}
