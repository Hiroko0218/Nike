package com.yafeng.nike.passport.dao.persist.repository;


import com.yafeng.nike.passport.pojo.vo.UserLoginInfoVO;

import java.time.LocalDateTime;

/**
 * 處理用戶數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IUserRepository {

    /**
     * 更新登入次數
     *
     * @param id           用戶ID
     * @param loginCount   登入次數
     * @param lastLoginIp  最後登入IP
     * @param gmtLastLogin 最後登入時間
     * @return 受影響的行數
     */
    int updateLastLogin(Long id, Integer loginCount, String lastLoginIp, LocalDateTime gmtLastLogin);

    /**
     * 根據用戶名查詢用戶的登入信息
     *
     * @param username 用戶名
     * @return 匹配的用戶的登入信息，如果沒有匹配的數據，則返回null
     */
    UserLoginInfoVO getLoginInfoByUsername(String username);

}
