package com.yafeng.nike.admin.mall.service.impl;

import com.yafeng.nike.admin.mall.dao.persist.repository.ICategoryRepository;
import com.yafeng.nike.admin.mall.dao.persist.repository.IGoodsRepository;
import com.yafeng.nike.admin.mall.pojo.entity.Category;
import com.yafeng.nike.admin.mall.pojo.param.CategoryAddNewParam;
import com.yafeng.nike.admin.mall.pojo.param.CategoryUpdateInfoParam;
import com.yafeng.nike.admin.mall.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.admin.mall.pojo.vo.CategoryTreeItemVO;
import com.yafeng.nike.admin.mall.service.ICategoryService;
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
    private IGoodsRepository goodsRepository;

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

        Long parentId = categoryAddNewParam.getParentId();
        Integer depth = 1;
        CategoryStandardVO parentCategory = null;
        if (parentId != 0) {
            parentCategory = categoryRepository.getStandardById(parentId);
            if (parentCategory == null) {
                String message = "添加類別失敗，父級類別不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            } else {
                depth = parentCategory.getDepth() + 1;
            }
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewParam, category);
        category.setDepth(depth);
        category.setIsParent(0);
        int rows = categoryRepository.insert(category);
        if (rows != 1) {
            String message = "添加類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        if (parentId != 0 && parentCategory.getIsParent() == 0) {
            Category updateParentCategory = new Category();
            updateParentCategory.setId(parentId);
            updateParentCategory.setIsParent(1);
            rows = categoryRepository.update(updateParentCategory);
            if (rows != 1) {
                String message = "添加類別失敗，服務器忙，請稍後再嘗試！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
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

        if (currentCategory.getIsParent() == 1) {
            String message = "刪除類別失敗，該類別仍包含子級類別！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        int goodsCount = goodsRepository.countByCategory(id);
        if (goodsCount > 0) {
            String message = "刪除類別失敗，仍有商品關聯到此類別！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        int rows = categoryRepository.deleteById(id);
        if (rows != 1) {
            String message = "刪除類別失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        Long parentId = currentCategory.getParentId();
        int count = categoryRepository.countByParent(parentId);
        if (count == 0) {
            Category parentCategory = new Category();
            parentCategory.setId(parentId);
            parentCategory.setIsParent(0);
            rows = categoryRepository.update(parentCategory);
            if (rows != 1) {
                String message = "刪除類別失敗，服務器忙，請稍後再嘗試！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
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
    public List<CategoryTreeItemVO> listTree() {
        log.debug("開始處理【查詢類別樹】的業務，參數：無");
        List<CategoryTreeItemVO> categoryTree = new ArrayList<>();

        List<CategoryListItemVO> categoryList = categoryRepository.list();
        Map<Long, CategoryListItemVO> allCategoryMap = transformListToMap(categoryList);
        for (Long key : allCategoryMap.keySet()) {
            CategoryListItemVO mapItem = allCategoryMap.get(key);
            if (mapItem.getParentId() == 0) {
                CategoryTreeItemVO categoryTreeItemVO = convertListItemToTreeItem(mapItem);
                categoryTree.add(categoryTreeItemVO);

                fillChildren(mapItem, categoryTreeItemVO, allCategoryMap);
            }
        }

        return categoryTree;
    }

    private Map<Long, CategoryListItemVO> transformListToMap(List<CategoryListItemVO> categoryList) {
        Map<Long, CategoryListItemVO> categoryMap = new LinkedHashMap<>();
        for (CategoryListItemVO categoryListItemVO : categoryList) {
            if (categoryListItemVO.getEnable() == 0) {
                continue;
            }
            categoryMap.put(categoryListItemVO.getId(), categoryListItemVO);
        }
        return categoryMap;
    }

    private void fillChildren(CategoryListItemVO listItem, CategoryTreeItemVO currentTreeItem, Map<Long, CategoryListItemVO> allCategoryMap) {
        if (listItem.getIsParent() == 1) {
            currentTreeItem.setChildren(new ArrayList<>());
            Set<Long> keySet = allCategoryMap.keySet();
            for (Long key : keySet) {
                CategoryListItemVO mapItem = allCategoryMap.get(key);
                if (mapItem.getParentId().equals(listItem.getId())) {
                    CategoryTreeItemVO categoryTreeSubItemVO = convertListItemToTreeItem(mapItem);
                    currentTreeItem.getChildren().add(categoryTreeSubItemVO);
                    if (mapItem.getIsParent() == 1) {
                        fillChildren(mapItem, categoryTreeSubItemVO, allCategoryMap);
                    }
                }
            }
        }
    }

    private CategoryTreeItemVO convertListItemToTreeItem(CategoryListItemVO listItem) {
        return new CategoryTreeItemVO()
                .setValue(listItem.getId())
                .setLabel(listItem.getName());
    }

    @Override
    public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum) {
        log.debug("開始處理【根據父級查詢子級列表】的業務，父級類別：{}, 頁碼：{}", parentId, pageNum);
        return categoryRepository.listByParent(parentId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據父級查詢子級列表】的業務，父級類別：{}, 頁碼：{}，每頁記錄數：{}", parentId, pageNum, pageSize);
        return categoryRepository.listByParent(parentId, pageNum, pageSize);
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

}
