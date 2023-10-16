package com.yafeng.nike.admin.content.dao.persist.repository;

/**
 * 處理讚與倒讚日誌數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IUpDownLogRepository {

    /**
     * 根據資源刪除讚與倒讚記錄
     *
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return 受影響的行數
     */
    int deleteByResource(Integer resourceType, Long resourceId);

}
