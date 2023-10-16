package com.yafeng.nike.admin.account.dao.persist.mapper;

import com.yafeng.nike.admin.account.pojo.vo.RoleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理角色數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface RoleMapper {

    /**
     * 查詢角色列表
     *
     * @return 角色列表
     */
    List<RoleListItemVO> list();

}
