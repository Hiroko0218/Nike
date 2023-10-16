package com.yafeng.nike.front.mall.dao.cache.impl;

import com.yafeng.nike.common.pojo.po.DistrictSimplePO;
import com.yafeng.nike.front.mall.dao.cache.IDistrictCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 處理市區數據的緩存訪問實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 **/
@Slf4j
@Repository
public class DistrictCacheRepositoryImpl implements IDistrictCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public DistrictCacheRepositoryImpl() {
        log.info("創建緩存存儲庫對象：DistrictCacheRepositoryImpl");
    }

    @Override
    public DistrictSimplePO getByCode(String code) {
        log.debug("開始處理【根據地區的行政代碼獲取地區數據】的緩存數據訪問，參數：{}", code);
        String key = KEY_PREFIX_ITEM + code;
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        Serializable serializable = opsForValue.get(key);
        return (DistrictSimplePO) serializable;
    }

}
