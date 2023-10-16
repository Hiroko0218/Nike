package com.yafeng.nike.basic.dao.cache.repository;


import com.yafeng.nike.common.consts.cache.DistrictCacheConsts;
import com.yafeng.nike.common.pojo.po.DistrictSimplePO;

import java.util.List;

/**
 * 處理市區數據的緩存訪問接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IDistrictCacheRepository extends DistrictCacheConsts {

    /**
     * 存儲地區數據
     *
     * @param districtSimplePO 地區數據
     */
    void save(DistrictSimplePO districtSimplePO);

    /**
     * 存儲根據父級劃分的地區列表
     *
     * @param parentId     父級地區ID，如果保存市列表，則父級地址ID使用0
     * @param districtList 歸屬此父級的地區數據
     */
    void saveListByParent(Long parentId, List<DistrictSimplePO> districtList);

    /**
     * 刪除緩存的所有地區數據
     */
    void deleteAll();

    /**
     * 根據父級查詢子級地區列表
     *
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);

}
