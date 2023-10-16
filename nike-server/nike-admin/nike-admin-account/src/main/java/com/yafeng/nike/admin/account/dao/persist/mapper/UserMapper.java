package com.yafeng.nike.admin.account.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.account.pojo.entity.User;
import com.yafeng.nike.admin.account.pojo.vo.UserListItemVO;
import com.yafeng.nike.admin.account.pojo.vo.UserStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根據用戶ID查詢用戶數據詳情
     *
     * @param id 用戶ID
     * @return 匹配的用戶數據詳情，如果沒有匹配的數據，則返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查詢用戶數據列表
     *
     * @return 用戶數據列表
     */
    List<UserListItemVO> list();

}
