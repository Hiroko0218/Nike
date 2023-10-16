package com.yafeng.nike.front.content.dao.persist.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import com.yafeng.nike.front.content.dao.persist.mapper.ArticleMapper;
import com.yafeng.nike.front.content.dao.persist.repository.IArticleRepository;
import com.yafeng.nike.front.content.pojo.entity.Article;
import com.yafeng.nike.front.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.front.content.pojo.vo.ArticleStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int setCommentCount(Long articleId, Integer commentCount) {
        log.debug("開始執行【設置文章的評論數】的數據訪問，參數：{}", articleId);
        Article article = new Article();
        article.setId(articleId);
        article.setCommentCount(commentCount);
        return articleMapper.updateById(article);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢文章信息】的數據訪問，參數：{}", id);
        return articleMapper.getStandardById(id);
    }

    @Override
    public PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢推薦的文章列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByRecommend();
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據類別查詢文章列表】的數據訪問，文章類別：{}，頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategory(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
