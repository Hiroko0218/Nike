package com.yafeng.nike.admin.account.dao.persist.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.admin.account.dao.persist.mapper.RoleMapper;
import com.yafeng.nike.admin.account.dao.persist.repository.IRoleRepository;
import com.yafeng.nike.admin.account.pojo.vo.RoleListItemVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理角色數據的數據訪問實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class RoleRepositoryImpl implements IRoleRepository {

    @Autowired
    private RoleMapper roleMapper;

    public RoleRepositoryImpl() {
        log.debug("創建存儲庫對象：RoleRepositoryImpl");
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢用戶列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<RoleListItemVO> list = roleMapper.list();
        PageInfo<RoleListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
