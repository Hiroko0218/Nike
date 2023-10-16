package com.yafeng.nike.admin.content.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.admin.content.dao.persist.mapper.ArticleMapper;
import com.yafeng.nike.admin.content.dao.persist.repository.IArticleRepository;
import com.yafeng.nike.admin.content.pojo.entity.Article;
import com.yafeng.nike.admin.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.ArticleStandardVO;
import com.yafeng.nike.admin.content.pojo.vo.search.ArticleSearchVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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

    public ArticleRepositoryImpl() {
        log.info("創建存儲庫對象：ArticleRepositoryImpl");
    }

    @Override
    public int insert(Article article) {
        log.debug("開始執行【插入文章】的數據訪問，參數：{}", article);
        return articleMapper.insert(article);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除文章】的數據訪問，參數：{}", id);
        return articleMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【根據若幹個ID批量刪除文章數據】的數據訪問，參數：{}", idList);
        return articleMapper.deleteBatchIds(idList);
    }

    @Override
    public int update(Article article) {
        log.debug("開始執行【根據ID查詢文章數據詳情】的數據訪問，參數：{}", article);
        return articleMapper.updateById(article);
    }

    @Override
    public int countByCategory(Long categoryId) {
        log.debug("開始執行【根據類別統計文章數量】的數據訪問，參數：{}", categoryId);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        return articleMapper.selectCount(queryWrapper);
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢文章信息】的數據訪問，參數：{}", id);
        return articleMapper.getStandardById(id);
    }

    @Override
    public PageData<ArticleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.list();
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<ArticleSearchVO> listSearchVO(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSearchVO> list = articleMapper.listSearchVO();
        PageInfo<ArticleSearchVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢文章列表】的數據訪問，文章類別：{}，頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListItemVO> list = articleMapper.listByCategory(categoryId);
        PageInfo<ArticleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
