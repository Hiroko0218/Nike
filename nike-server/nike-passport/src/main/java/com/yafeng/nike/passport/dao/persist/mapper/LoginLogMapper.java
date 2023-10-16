package com.yafeng.nike.passport.dao.persist.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.passport.pojo.entity.LoginLog;
import com.yafeng.nike.passport.pojo.vo.LoginLogListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理用戶登入日誌的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    /**
     * 查詢用戶登入日誌列表
     *
     * @return 用戶登入日誌列表
     */
    List<LoginLogListItemVO> list();

}
