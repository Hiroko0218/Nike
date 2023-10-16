package com.yafeng.nike.basic.dao.persist.mapper;

import com.yafeng.nike.common.pojo.po.DistrictSimplePO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理市區數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface DistrictMapper {

    /**
     * 根據父級查詢子級地區列表
     *
     * @param parentId 父級地區ID
     * @return 子級地區列表
     */
    List<DistrictSimplePO> listByParent(Long parentId);

}
