package com.yafeng.nike.front.mall.dao.cache;

import com.yafeng.nike.common.consts.cache.DistrictCacheConsts;
import com.yafeng.nike.common.pojo.po.DistrictSimplePO;

/**
 * 處理市區數據的緩存訪問接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IDistrictCacheRepository extends DistrictCacheConsts {

    /**
     * 根據地區的行政代碼獲取地區數據
     *
     * @param code 地區的行政代碼
     * @return 地區數據
     */
    DistrictSimplePO getByCode(String code);

}
