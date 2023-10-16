package com.yafeng.nike.common.validation;

/**
 * 通用數據相關規則配置
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface BaseRules {

    // ====== 【啟用狀態】 =====

    /**
     * 區間：啟用狀態：最小值
     */
    int RANGE_ENABLE_MIN = 0;
    /**
     * 驗證失敗描述文本：啟用狀態最小值
     */
    String MESSAGE_ENABLE_MIN = "啟用狀態的值必須是0或1";

    /**
     * 區間：啟用狀態：最大值
     */
    int RANGE_ENABLE_MAX = 1;
    /**
     * 驗證失敗描述文本：啟用狀態最大值
     */
    String MESSAGE_ENABLE_MAX = "啟用狀態的值必須是0或1";

    /**
     * 驗證失敗描述文本：非NULl：啟用狀態
     */
    String MESSAGE_ENABLE_NOT_NULL = "請選擇用戶的啟用狀態";

}
