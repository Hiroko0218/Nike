package com.yafeng.nike.passport.dao.persist.repository;


import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.passport.pojo.entity.LoginLog;
import com.yafeng.nike.passport.pojo.vo.LoginLogListItemVO;

/**
 * 處理登入日誌數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ILoginLogRepository {

    /**
     * 插入登入日誌
     *
     * @param loginLog 登入日誌
     * @return 受影響的行數
     */
    int insert(LoginLog loginLog);

    /**
     * 查詢用戶登入日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 用戶登入日誌列表
     */
    PageData<LoginLogListItemVO> list(Integer pageNum, Integer pageSize);

}
