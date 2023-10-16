package com.yafeng.nike.admin.content.service.impl;

import com.yafeng.nike.admin.content.dao.cache.ICategoryCacheRepository;
import com.yafeng.nike.admin.content.dao.persist.repository.IArticleRepository;
import com.yafeng.nike.admin.content.dao.persist.repository.ICategoryRepository;
import com.yafeng.nike.admin.content.pojo.entity.Category;
import com.yafeng.nike.admin.content.pojo.param.CategoryAddNewParam;
import com.yafeng.nike.admin.content.pojo.param.CategoryUpdateInfoParam;
import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.admin.content.service.ICategoryService;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 處理類別的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Value("${nike.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IArticleRepository articleRepository;
    @Autowired
    private ICategoryCacheRepository categoryCacheRepository;

    public CategoryServiceImpl() {
        log.debug("創建業務類對象：CategoryServiceImpl");
    }

    @Override
    public void addNew(CategoryAddNewParam categoryAddNewParam) {
        log.debug("開始處理【添加類別】的業務，參數：{}", categoryAddNewParam);
        String name = categoryAddNewParam.getName();
        int count = categoryRepository.countByName(name);
        log.debug("根據名稱【{}】統計數量，結果：{}", name, count);
        if (count > 0) {
            String message = "添加類別失敗，類別名稱已經被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewParam, category);
        int rows = categoryRepository.insert(category);
        if (rows != 1) {
            String message = "添加類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("開始處理【根據ID刪除類別】的業務，參數：{}", id);
        CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
        if (currentCategory == null) {
            String message = "刪除類別失敗，嘗試刪除的類別數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int articleCount = articleRepository.countByCategory(id);
        if (articleCount > 0) {
            String message = "刪除類別失敗，仍有文章歸屬於此類別！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        int rows = categoryRepository.deleteById(id);
        if (rows != 1) {
            String message = "刪除類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        log.debug("開始處理【啟用類別】的業務，參數：{}", id);
        updateEnableById(id, ENABLE_STATE_ON);
    }

    @Override
    public void setDisable(Long id) {
        log.debug("開始處理【禁用類別】的業務，參數：{}", id);
        updateEnableById(id, ENABLE_STATE_OFF);
    }

    @Override
    public void setDisplay(Long id) {
        log.debug("開始處理【將類別顯示在導航欄】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_ON);
    }

    @Override
    public void setHidden(Long id) {
        log.debug("開始處理【將類別不顯示在導航欄】的業務，參數：{}", id);
        updateDisplayById(id, DISPLAY_STATE_OFF);
    }

    @Override
    public void updateInfoById(Long id, CategoryUpdateInfoParam categoryUpdateInfoParam) {
        log.debug("開始處理【修改類別詳情】的業務，ID：{}，新數據：{}", id, categoryUpdateInfoParam);
        CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "修改類別詳情失敗，嘗試修改的類別數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int count = categoryRepository.countByNameAndNotId(id, categoryUpdateInfoParam.getName());
        if (count > 0) {
            String message = "修改類別詳情失敗，類別名稱已經被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateInfoParam, category);
        category.setId(id);
        int rows = categoryRepository.update(category);
        if (rows != 1) {
            String message = "修改類別詳情失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢類別】的業務，參數：{}", id);
        CategoryStandardVO queryResult = categoryRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "查詢類別詳情失敗，類別數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<CategoryListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢類別列表】的業務，頁碼：{}", pageNum);
        return categoryRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢類別列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return categoryRepository.list(pageNum, pageSize);
    }

    private void updateEnableById(Long id, Integer enable) {
        CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
        if (currentCategory == null) {
            String message = ENABLE_STATE_TEXT[enable] + "類別失敗，類別數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentCategory.getEnable().equals(enable)) {
            String message = ENABLE_STATE_TEXT[enable] + "類別失敗，此類別已經處於" + ENABLE_STATE_TEXT[enable] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category updateCategory = new Category();
        updateCategory.setId(id);
        updateCategory.setEnable(enable);
        int rows = categoryRepository.update(updateCategory);
        if (rows != 1) {
            String message = ENABLE_STATE_TEXT[enable] + "類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updateDisplayById(Long id, Integer isDisplay) {
        CategoryStandardVO currentCategory = categoryRepository.getStandardById(id);
        if (currentCategory == null) {
            String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗，類別數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentCategory.getIsDisplay().equals(isDisplay)) {
            String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗，此類別已經處於" + DISPLAY_STATE_TEXT[isDisplay] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Category updateCategory = new Category();
        updateCategory.setId(id);
        updateCategory.setIsDisplay(isDisplay);
        int rows = categoryRepository.update(updateCategory);
        if (rows != 1) {
            String message = DISPLAY_STATE_TEXT[isDisplay] + "類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void rebuildCache() {
        List<CategoryListItemVO> list
                = categoryRepository.list(1, Integer.MAX_VALUE).getList();
        categoryCacheRepository.deleteList();
        categoryCacheRepository.saveList(list);
    }

}
