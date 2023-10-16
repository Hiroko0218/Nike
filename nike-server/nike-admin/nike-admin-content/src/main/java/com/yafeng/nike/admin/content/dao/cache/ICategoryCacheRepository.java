package com.yafeng.nike.admin.content.dao.cache;

import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.common.consts.cache.ContentCacheConsts;

import java.util.List;

public interface ICategoryCacheRepository extends ContentCacheConsts {

    void saveList(List<CategoryListItemVO> categoryList);

    boolean deleteList();

    List<CategoryListItemVO> list();

}

