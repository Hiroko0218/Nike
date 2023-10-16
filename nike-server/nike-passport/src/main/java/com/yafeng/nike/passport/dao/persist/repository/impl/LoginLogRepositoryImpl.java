package com.yafeng.nike.passport.dao.persist.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import com.yafeng.nike.passport.dao.persist.mapper.LoginLogMapper;
import com.yafeng.nike.passport.dao.persist.repository.ILoginLogRepository;
import com.yafeng.nike.passport.pojo.entity.LoginLog;
import com.yafeng.nike.passport.pojo.vo.LoginLogListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理登入日誌數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class LoginLogRepositoryImpl implements ILoginLogRepository {

    @Autowired
    private LoginLogMapper loginLogMapper;

    public LoginLogRepositoryImpl() {
        log.info("創建存儲庫對象：LoginLogRepositoryImpl");
    }

    @Override
    public int insert(LoginLog loginLog) {
        log.debug("開始執行【插入登入日誌】的數據訪問，參數：{}", loginLog);
        return loginLogMapper.insert(loginLog);
    }

    @Override
    public PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢用戶登入日誌列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<LoginLogListItemVO> list = loginLogMapper.list();
        PageInfo<LoginLogListItemVO> pageInfo = new PageInfo<>(list);
        PageData<LoginLogListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

}
