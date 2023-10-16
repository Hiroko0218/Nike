package com.yafeng.nike.admin.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.content.pojo.entity.UpDownLog;
import org.springframework.stereotype.Repository;

/**
 * 處理讚與倒讚日誌數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface UpDownLogMapper extends BaseMapper<UpDownLog> {

}
