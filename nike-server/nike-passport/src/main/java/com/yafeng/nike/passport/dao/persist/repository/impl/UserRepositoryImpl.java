package com.yafeng.nike.passport.dao.persist.repository.impl;

import com.yafeng.nike.passport.dao.persist.mapper.UserMapper;
import com.yafeng.nike.passport.dao.persist.repository.IUserRepository;
import com.yafeng.nike.passport.pojo.entity.User;
import com.yafeng.nike.passport.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * 處理用戶數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRepositoryImpl() {
        log.info("創建存儲庫對象：UserRepositoryImpl");
    }

    @Override
    public int updateLastLogin(Long id, Integer loginCount, String lastLoginIp, LocalDateTime gmtLastLogin) {
        log.debug("開始執行【更新登入次數】的數據訪問，用戶ID：{}，登入次數：{}", id, loginCount);
        User user = new User();
        user.setId(id);
        user.setLoginCount(loginCount);
        user.setLastLoginIp(lastLoginIp);
        user.setGmtLastLogin(gmtLastLogin);
        return userMapper.updateById(user);
    }

    @Override
    public UserLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("開始執行【根據用戶名查詢用戶登入信息】的數據訪問，參數：{}", username);
        return userMapper.getLoginInfoByUsername(username);
    }

}
