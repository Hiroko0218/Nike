package com.yafeng.nike.admin.mall.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.mall.pojo.entity.CheckLog;
import com.yafeng.nike.admin.mall.pojo.vo.CheckLogListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理審核日誌數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface CheckLogMapper extends BaseMapper<CheckLog> {

    /**
     * 根據資源類型查詢審核日誌列表
     *
     * @param resourceType 資源類型
     * @return 審核日誌列表
     * @see com.yafeng.nike.common.consts.data.ContentConsts
     */
    List<CheckLogListItemVO> listByResourceType(int resourceType);

}
