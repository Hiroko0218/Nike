package com.yafeng.nike.front.content.service;

import com.yafeng.nike.front.content.pojo.vo.CategoryListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理類別業務的接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICategoryService {

    /**
     * 查詢類別列表
     *
     * @return 類別列表
     */
    List<CategoryListItemVO> list();

}
