package com.yafeng.nike.basic.dao.persist.repository.impl;

import com.yafeng.nike.basic.dao.persist.mapper.DistrictMapper;
import com.yafeng.nike.basic.dao.persist.repository.IDistrictRepository;
import com.yafeng.nike.common.pojo.po.DistrictSimplePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理市區數據的數據訪問實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class DisctrictRepositoryImpl implements IDistrictRepository {

    @Autowired
    private DistrictMapper roleMapper;

    public DisctrictRepositoryImpl() {
        log.debug("創建存儲庫對象：DisctrictRepositoryImpl");
    }

    @Override
    public List<DistrictSimplePO> listByParent(Long parentId) {
        log.debug("開始執行【根據父級查詢子級地區列表】的數據訪問，參數：{}", parentId);
        return roleMapper.listByParent(parentId);
    }

}
