package com.yafeng.nike.front.content.service.impl;

import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.content.dao.persist.repository.IArticleRepository;
import com.yafeng.nike.front.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.front.content.pojo.vo.ArticleStandardVO;
import com.yafeng.nike.front.content.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理文章數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class ArticleServiceImpl implements IArticleService {

    @Value("${nike.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IArticleRepository articleRepository;

    public ArticleServiceImpl() {
        log.debug("創建業務類對象：ArticleServiceImpl");
    }

    @Override
    public ArticleStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢文章】的業務，參數：{}", id);
        ArticleStandardVO queryResult = articleRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "查詢文章詳情失敗，文章數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<ArticleListItemVO> listByRecommend(Integer pageNum) {
        log.debug("開始處理【查詢推薦的文章列表】的業務，頁碼：{}", pageNum);
        return articleRepository.listByRecommend(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByRecommend(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢推薦的文章列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return articleRepository.listByRecommend(pageNum, pageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum) {
        log.debug("開始處理【根據類別查詢文章列表】的業務，文章類別：{}, 頁碼：{}", categoryId, pageNum);
        return articleRepository.listByCategory(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<ArticleListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據類別查詢文章列表】的業務，文章類別：{}, 頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        return articleRepository.listByCategory(categoryId, pageNum, pageSize);
    }

}
