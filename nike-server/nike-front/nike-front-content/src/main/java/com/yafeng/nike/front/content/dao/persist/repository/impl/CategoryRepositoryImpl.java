package com.yafeng.nike.front.content.dao.persist.repository.impl;

import com.yafeng.nike.front.content.dao.persist.mapper.CategoryMapper;
import com.yafeng.nike.front.content.dao.persist.repository.ICategoryRepository;
import com.yafeng.nike.front.content.pojo.vo.CategoryListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理類別數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryRepositoryImpl() {
        log.info("創建存儲庫對象：CategoryRepositoryImpl");
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始執行【查詢類別列表】的數據訪問，參數：無");
        return categoryMapper.list();
    }

}
