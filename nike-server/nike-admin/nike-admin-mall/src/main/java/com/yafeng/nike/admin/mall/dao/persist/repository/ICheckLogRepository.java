package com.yafeng.nike.admin.mall.dao.persist.repository;

import com.yafeng.nike.admin.mall.pojo.entity.CheckLog;
import com.yafeng.nike.admin.mall.pojo.vo.CheckLogListItemVO;
import com.yafeng.nike.common.pojo.vo.PageData;

/**
 * 處理審核日誌數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICheckLogRepository {

    /**
     * 插入審核日誌數據
     *
     * @param checkLog 審核日誌數據
     * @return 受影響的行數
     */
    int insert(CheckLog checkLog);

    /**
     * 根據資源刪除審核日誌
     *
     * @param resourceType 資源類型
     * @param resourceId   資源ID
     * @return 受影響的行數
     */
    int deleteByResource(Integer resourceType, Long resourceId);

    /**
     * 查詢審核日誌列表
     *
     * @param resourceType 資源類型
     * @param pageNum      頁碼
     * @param pageSize     每頁記錄數
     * @return 審核日誌列表
     * @see com.yafeng.nike.common.consts.data.ContentConsts
     */
    PageData<CheckLogListItemVO> listByResourceType(int resourceType, Integer pageNum, Integer pageSize);

}
