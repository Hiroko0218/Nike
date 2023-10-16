package com.yafeng.nike.front.content.service.impl;

import com.yafeng.nike.front.content.dao.persist.repository.ICategoryRepository;
import com.yafeng.nike.front.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.front.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 處理類別的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryServiceImpl() {
        log.debug("創建業務類對象：CategoryServiceImpl");
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始處理【查詢類別列表】的業務，無參數");
        return categoryRepository.list();
    }

}
