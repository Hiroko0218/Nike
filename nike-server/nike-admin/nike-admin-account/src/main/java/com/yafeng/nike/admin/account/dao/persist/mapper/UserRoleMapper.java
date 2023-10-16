package com.yafeng.nike.admin.account.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.account.pojo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶與角色的關聯數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量插入用戶與角色的關聯數據
     *
     * @param userRoleList 若幹個用戶與角色的關聯數據的集合
     * @return 受影響的行數
     */
    int insertBatch(List<UserRole> userRoleList);


}
