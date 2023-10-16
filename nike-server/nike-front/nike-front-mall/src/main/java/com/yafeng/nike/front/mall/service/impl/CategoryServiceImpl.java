package com.yafeng.nike.front.mall.service.impl;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.dao.persist.repository.ICategoryRepository;
import com.yafeng.nike.front.mall.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.CategoryTreeItemVO;
import com.yafeng.nike.front.mall.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
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

    public CategoryServiceImpl() {
        log.debug("創建業務類對象：CategoryServiceImpl");
    }

    @Override
    public List<CategoryTreeItemVO> listTree() {
        log.debug("開始處理【獲取類別樹】的業務，參數：無");
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

    @Override
    public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum) {
        log.debug("開始處理【根據父級類別查詢子級類別列表】的業務，父級類別：{}, 頁碼：{}", parentId, pageNum);
        return categoryRepository.listByParent(parentId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<CategoryListItemVO> listByParent(Long parentId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據父級類別查詢子級類別列表】的業務，父級類別：{}, 頁碼：{}，每頁記錄數：{}", parentId, pageNum, pageSize);
        return categoryRepository.listByParent(parentId, pageNum, pageSize);
    }

    private Map<Long, CategoryListItemVO> transformListToMap(List<CategoryListItemVO> categoryList) {
        Map<Long, CategoryListItemVO> categoryMap = new LinkedHashMap<>();
        for (CategoryListItemVO categoryListItemVO : categoryList) {
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

}
