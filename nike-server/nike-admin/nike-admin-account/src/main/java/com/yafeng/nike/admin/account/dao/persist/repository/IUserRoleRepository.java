package com.yafeng.nike.admin.account.dao.persist.repository;


import com.yafeng.nike.admin.account.pojo.entity.UserRole;

import java.util.List;

/**
 * 處理用戶角色數據的數據訪問接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IUserRoleRepository {

    /**
     * 批量插入用戶與角色的關聯數據
     *
     * @param userRoleList 若幹個用戶與角色的關聯數據的集合
     * @return 受影響的行數
     */
    int insertBatch(List<UserRole> userRoleList);

    /**
     * 根據用戶id刪除用戶與角色的關聯數據
     *
     * @param userId 用戶id
     * @return 受影響的行數
     */
    int deleteByUserId(Long userId);
}
