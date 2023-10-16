package com.yafeng.nike.front.account.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yafeng.nike.front.account.dao.persist.mapper.UserMapper;
import com.yafeng.nike.front.account.dao.persist.repository.IUserRepository;
import com.yafeng.nike.front.account.pojo.entity.User;
import com.yafeng.nike.front.account.pojo.vo.UserSimpleInfoVO;
import com.yafeng.nike.front.account.pojo.vo.UserStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public int insert(User user) {
        log.debug("開始執行【插入用戶】的數據訪問，參數：{}", user);
        return userMapper.insert(user);
    }

    @Override
    public int updateById(User user) {
        log.debug("開始執行【根據ID修改用戶數據】的數據訪問，參數：{}", user);
        return userMapper.updateById(user);
    }

    @Override
    public int countByUsername(String username) {
        log.debug("開始執行【根據用戶名統計用戶的數量】的數據訪問，參數：{}", username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhone(String phone) {
        log.debug("開始執行【根據手機號碼統計用戶的數量】的數據訪問，參數：{}", phone);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByPhoneAndNotId(String phone, Long userId) {
        log.debug("開始執行【統計匹配手機號碼但非用戶ID的用戶數據的數量】的數據訪問，手機號碼：{}，用戶ID：{}", phone, userId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmail(String email) {
        log.debug("開始執行【根據電子郵箱統計用戶的數量】的數據訪問，參數：{}", email);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByEmailAndNotId(String email, Long userId) {
        log.debug("開始執行【統計匹配電子郵箱但非用戶ID的用戶數據的數量】的數據訪問，電子郵箱：{}，用戶ID：{}", email, userId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        queryWrapper.ne("id", userId);
        return userMapper.selectCount(queryWrapper);
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢用戶詳情】的數據訪問，參數：{}", id);
        return userMapper.getStandardById(id);
    }

    @Override
    public UserSimpleInfoVO getSimpleInfoById(Long id) {
        log.debug("開始執行【根據用戶ID查詢用戶基本信息】的數據訪問，參數：{}", id);
        return userMapper.getSimpleInfoById(id);
    }

}
