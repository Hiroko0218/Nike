package com.yafeng.nike.admin.content.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.admin.content.dao.persist.mapper.CheckLogMapper;
import com.yafeng.nike.admin.content.dao.persist.repository.ICheckLogRepository;
import com.yafeng.nike.admin.content.pojo.entity.CheckLog;
import com.yafeng.nike.admin.content.pojo.vo.CheckLogListItemVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理審核日誌數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class CheckLogRepositoryImpl implements ICheckLogRepository {

    @Autowired
    private CheckLogMapper checkLogMapper;

    public CheckLogRepositoryImpl() {
        log.info("創建存儲庫對象：CheckLogRepositoryImpl");
    }

    @Override
    public int insert(CheckLog checkLog) {
        log.debug("開始執行【插入審核日誌】的數據訪問，參數：{}", checkLog);
        return checkLogMapper.insert(checkLog);
    }

    @Override
    public int deleteByResource(Integer resourceType, Long resourceId) {
        log.debug("開始執行【根據資源刪除審核日誌】的數據訪問，資源類型：{}，資源ID：{}", resourceType, resourceId);
        QueryWrapper<CheckLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_type", resourceType);
        queryWrapper.eq("resource_Id", resourceId);
        return checkLogMapper.delete(queryWrapper);
    }

    @Override
    public PageData<CheckLogListItemVO> listByResourceType(int resourceType, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢審核日誌列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<CheckLogListItemVO> list = checkLogMapper.listByResourceType(resourceType);
        PageInfo<CheckLogListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
