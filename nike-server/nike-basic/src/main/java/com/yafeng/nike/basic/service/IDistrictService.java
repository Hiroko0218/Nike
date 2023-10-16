package com.yafeng.nike.basic.service;

import com.yafeng.nike.common.pojo.po.DistrictSimplePO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理市區數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IDistrictService {

    /**
     * 根據父級查詢子級地區列表
     *
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);

    /**
     * 重建地區數據的緩存
     */
    void rebuildCache();

}
