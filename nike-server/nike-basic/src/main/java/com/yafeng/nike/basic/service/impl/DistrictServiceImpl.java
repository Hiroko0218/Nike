package com.yafeng.nike.basic.service.impl;

import com.yafeng.nike.basic.dao.cache.repository.IDistrictCacheRepository;
import com.yafeng.nike.basic.dao.persist.repository.IDistrictRepository;
import com.yafeng.nike.basic.service.IDistrictService;
import com.yafeng.nike.common.pojo.po.DistrictSimplePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 處理市區數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private IDistrictRepository districtRepository;
    @Autowired
    private IDistrictCacheRepository districtCacheRepository;

    public DistrictServiceImpl() {
        log.debug("創建業務類對象：DistrictServiceImpl");
    }

    @Override
    public List<DistrictSimplePO> listByParent(Long parentId) {
        log.debug("開始執行【根據父級查詢子級地區列表】的業務，參數：{}", parentId);
        return districtRepository.listByParent(parentId);
    }

    @Override
    public void rebuildCache() {
        districtCacheRepository.deleteAll();

        Long parentId = 0L;
        List<DistrictSimplePO> list = districtRepository.listByParent(parentId);

        for (DistrictSimplePO districtSimplePO : list) {
            districtCacheRepository.save(districtSimplePO);
        }

        districtCacheRepository.saveListByParent(parentId, list);
        for (DistrictSimplePO listItem : list) {
            callListRecursion(listItem);
        }
    }

    /**
     * 遞歸得到各地區的子級列表數據
     *
     * @param district 地區數據
     */
    private void callListRecursion(DistrictSimplePO district) {
        Long districtId = district.getId();
        List<DistrictSimplePO> list = districtRepository.listByParent(districtId);
        if (list.size() > 0) {
            districtCacheRepository.saveListByParent(districtId, list);
            for (DistrictSimplePO districtSimplePO : list) {
                districtCacheRepository.save(districtSimplePO);
                callListRecursion(districtSimplePO);
            }
        }
    }

}