package com.yafeng.nike.admin.account.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yafeng.nike.admin.account.dao.persist.mapper.UserRoleMapper;
import com.yafeng.nike.admin.account.dao.persist.repository.IUserRoleRepository;
import com.yafeng.nike.admin.account.pojo.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶與角色關聯數據的數據訪問實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class UserRoleRepositoryImpl implements IUserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRoleRepositoryImpl() {
        log.debug("創建存儲庫對象：AdminRoleRepositoryImpl");
    }

    @Override
    public int insertBatch(List<UserRole> userRoleList) {
        log.debug("開始執行【批量插入用戶與角色的關聯數據】的數據訪問，參數：{}", userRoleList);
        return userRoleMapper.insertBatch(userRoleList);
    }

    @Override
    public int deleteByUserId(Long userId) {
        log.debug("開始執行【根據用戶ID刪除用戶與角色的關聯數據】的數據訪問，參數：{}", userId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userRoleMapper.delete(queryWrapper);
    }
}
