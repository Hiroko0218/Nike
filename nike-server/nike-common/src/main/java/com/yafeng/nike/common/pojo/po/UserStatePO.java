package com.yafeng.nike.common.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 用戶狀態信息的PO，此類數據將存入到緩存中，管理員修改用戶關鍵信息時也會同步修改緩存中對應的數據，用戶提交請求時也會實時檢查緩存中的用戶狀態
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class UserStatePO implements Serializable {

    /**
     * 啟用狀態，0=禁用，1=啟用
     */
    private Integer enable;

    /**
     * 權限列表的JSON字符串
     */
    private String authoritiesJsonString;

}
