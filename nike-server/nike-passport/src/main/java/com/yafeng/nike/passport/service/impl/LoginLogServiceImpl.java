package com.yafeng.nike.passport.service.impl;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.passport.dao.persist.repository.ILoginLogRepository;
import com.yafeng.nike.passport.pojo.vo.LoginLogListItemVO;
import com.yafeng.nike.passport.service.ILoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 處理用戶登入日誌的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Value("${nike.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private ILoginLogRepository loginLogRepository;

    public LoginLogServiceImpl() {
        log.info("創建業務對象：LoginLogServiceImpl");
    }

    @Override
    public PageData<LoginLogListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢登入日誌列表】的業務，頁碼：{}", pageNum);
        PageData<LoginLogListItemVO> pageData = loginLogRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢登入日誌列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageData<LoginLogListItemVO> pageData = loginLogRepository.list(pageNum, pageSize);
        return pageData;
    }

}