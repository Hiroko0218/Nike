package com.yafeng.nike.admin.account.dao.cache;

import com.yafeng.nike.common.consts.cache.PassportCacheConsts;
import com.yafeng.nike.common.pojo.po.UserStatePO;

/**
 * 處理用戶緩存數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IUserCacheRepository extends PassportCacheConsts {

    /**
     * 從緩存中刪除用戶登入信息
     *
     * @param userId 用戶ID
     * @return 如果刪除成功，將返回true，否則，將返回false
     */
    boolean deleteUserState(Long userId);

    /**
     * 將緩存中的用戶狀態設置為禁用
     *
     * @param userId 用戶ID
     */
    void setUserDisabled(Long userId);

    /**
     * 從緩存中讀取用戶登入信息
     *
     * @param userId 用戶ID
     * @return 匹配的用戶信息，如果沒有匹配的數據，則返回null
     */
    UserStatePO getUserState(Long userId);

}
