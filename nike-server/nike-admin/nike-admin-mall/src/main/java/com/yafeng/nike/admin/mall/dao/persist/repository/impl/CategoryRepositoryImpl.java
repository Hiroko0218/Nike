package com.yafeng.nike.admin.mall.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.admin.mall.dao.persist.mapper.CategoryMapper;
import com.yafeng.nike.admin.mall.dao.persist.repository.ICategoryRepository;
import com.yafeng.nike.admin.mall.pojo.entity.Category;
import com.yafeng.nike.admin.mall.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
    public int insert(Category category) {
        log.debug("開始執行【插入類別】的數據訪問，參數：{}", category);
        return categoryMapper.insert(category);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("開始執行【根據ID刪除類別】的數據訪問，參數：{}", id);
        return categoryMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Collection<Long> idList) {
        log.debug("開始執行【批量刪除類別】的數據訪問，參數：{}", idList);
        return categoryMapper.deleteBatchIds(idList);
    }

    @Override
    public int update(Category category) {
        log.debug("開始執行【更新類別】的數據訪問，參數：{}", category);
        return categoryMapper.updateById(category);
    }

    @Override
    public int count() {
        log.debug("開始執行【統計類別的數量】的數據訪問，參數：無");
        return categoryMapper.selectCount(null);
    }

    @Override
    public int countByName(String name) {
        log.debug("開始執行【統計匹配名稱的類別的數量】的數據訪問，參數：{}", name);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return categoryMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByNameAndNotId(Long id, String name) {
        log.debug("開始執行【統計匹配名稱但不匹配ID的類別的數量】的數據訪問，ID：{}，類別：{}", id, name);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name).ne("id", id);
        return categoryMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByParent(Long parentId) {
        log.debug("開始執行【統計匹配父級的類別的數量】的數據訪問，父級類別：{}", parentId);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        return categoryMapper.selectCount(queryWrapper);
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢類別信息】的數據訪問，參數：{}", id);
        return categoryMapper.getStandardById(id);
    }

    @Override
    public List<CategoryListItemVO> list() {
        log.debug("開始執行【查詢類別列表】的數據訪問，參數：無");
        return categoryMapper.list();
    }

    @Override
    public PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢類別列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryListItemVO> list = categoryMapper.list();
        PageInfo<CategoryListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據父級類別查詢其子級類別列表】的數據訪問，父級類別：{}，頁碼：{}，每頁記錄數：{}", parentId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CategoryListItemVO> list = categoryMapper.listByParent(parentId);
        PageInfo<CategoryListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
