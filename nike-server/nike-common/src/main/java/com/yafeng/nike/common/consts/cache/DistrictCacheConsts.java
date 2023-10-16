package com.yafeng.nike.common.consts.cache;

/**
 * 地區數據緩存相關常量
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface DistrictCacheConsts {

    /**
     * 緩存數據的KEY的前綴：根據父級存儲的地區列表
     */
    String KEY_PREFIX_LIST_BY_PARENT = "district:list-by-parent:";

    /**
     * 緩存數據的KEY的前綴：地區數據
     */
    String KEY_PREFIX_ITEM = "district:item:";

    /**
     * 緩存中所有列表數據的Key集合的Key
     */
    String KEY_ALL_KEYS = "district:keys";

}
