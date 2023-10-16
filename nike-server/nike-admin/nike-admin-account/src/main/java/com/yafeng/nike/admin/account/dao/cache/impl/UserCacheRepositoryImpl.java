package com.yafeng.nike.admin.account.dao.cache.impl;

import cn.hutool.core.bean.BeanUtil;
import com.yafeng.nike.admin.account.dao.cache.IUserCacheRepository;
import com.yafeng.nike.common.pojo.po.UserStatePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

/**
 * 處理用戶緩存數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class UserCacheRepositoryImpl implements IUserCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public boolean deleteUserState(Long userId) {
        log.debug("開始處理【從緩存中刪除用戶登入信息】的數據訪問，用戶ID：{}，", userId);
        String key = KEY_PREFIX_USER_STATE + userId;
        return redisTemplate.delete(key);
    }

    @Override
    public void setUserDisabled(Long userId) {
        log.debug("開始處理【將緩存中的用戶狀態設置為禁用】的數據訪問，用戶ID：{}，", userId);
        String key = KEY_PREFIX_USER_STATE + userId;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(key, HASH_KEY_USER_ENABLE, 0);
    }

    @Override
    public UserStatePO getUserState(Long userId) {
        log.debug("開始處理【從緩存中讀取用戶登入信息】的數據訪問，用戶ID：{}，", userId);
        String key = KEY_PREFIX_USER_STATE + userId;
        UserStatePO userStatePO = null;
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Map<Object, Object> entries = opsForHash.entries(key);
        if (entries.size() != 0) {
            userStatePO = BeanUtil.mapToBean(entries, UserStatePO.class, true, null);
        }
        return userStatePO;
    }

}
