package com.yafeng.nike.passport.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.passport.pojo.entity.User;
import com.yafeng.nike.passport.pojo.vo.UserLoginInfoVO;
import org.springframework.stereotype.Repository;

/**
 * 處理用戶數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根據用戶名查詢用戶的登入信息
     *
     * @param username 用戶名
     * @return 匹配的用戶的登入信息，如果沒有匹配的數據，則返回null
     */
    UserLoginInfoVO getLoginInfoByUsername(String username);

}
