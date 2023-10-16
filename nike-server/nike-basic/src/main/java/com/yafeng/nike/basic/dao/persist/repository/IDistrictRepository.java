package com.yafeng.nike.basic.dao.persist.repository;

import com.yafeng.nike.common.pojo.po.DistrictSimplePO;

import java.util.List;

/**
 * 處理市區數據的數據訪問接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IDistrictRepository {

    /**
     * 根據父級查詢子級地區列表
     *
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);

}

