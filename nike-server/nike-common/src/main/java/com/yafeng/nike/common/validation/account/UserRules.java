package com.yafeng.nike.common.validation.account;

import com.yafeng.nike.common.validation.BaseRules;

/**
 * 用戶數據相關規則配置
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface UserRules extends BaseRules {

    // ====== 【用戶名】 =====

    /**
     * 正則：用戶名
     */
    String PATTERN_USERNAME = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,14}$";
    /**
     * 驗證失敗描述文本：正則：用戶名
     */
    String MESSAGE_USERNAME_PATTERN = "用戶名必須由4~15長度的字母、數組、下劃線組成，且第1個字符必須是字母";
    /**
     * 驗證失敗描述文本：非NULl：用戶名
     */
    String MESSAGE_USERNAME_NOT_NULL = "請提交用戶名";

    // ====== 【密碼】 =====

    /**
     * 正則：密碼
     */
    String PATTERN_PASSWORD = "^.{4,15}$";
    /**
     * 驗證失敗描述文本：正則：密碼
     */
    String MESSAGE_PASSWORD_PATTERN = "密碼必須是4~15長度的字符組成";
    /**
     * 驗證失敗描述文本：非NULl：密碼
     */
    String MESSAGE_PASSWORD_NOT_NULL = "請提交密碼";

    // ====== 【手機號碼】 =====

    /**
     * 正則：手機號碼
     */
    String PATTERN_PHONE = "^\\d{11}$";
    /**
     * 驗證失敗描述文本：正則：手機號碼
     */
    String MESSAGE_PHONE_PATTERN = "手機號碼必須是11位的純數字";
    /**
     * 驗證失敗描述文本：非NULl：手機號碼
     */
    String MESSAGE_PHONE_NOT_NULL = "請提交手機號碼";

    // ====== 【電子郵箱】 =====

    /**
     * 正則：電子郵箱
     */
    String PATTERN_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    /**
     * 驗證失敗描述文本：正則：電子郵箱
     */
    String MESSAGE_EMAIL_PATTERN = "請輸入正確格式的電子郵箱";
    /**
     * 驗證失敗描述文本：非NULl：電子郵箱
     */
    String MESSAGE_EMAIL_NOT_NULL = "請提交電子郵箱";

    // ====== 【角色列表】 =====

    /**
     * 驗證失敗描述文本：非NULl：角色列表
     */
    String MESSAGE_ROLE_IDS_NOT_NULL = "請至少選擇1種角色";
    /**
     * 長度：角色列表：最小值
     */
    int SIZE_ROLE_IDS_MIN = 1;
    /**
     * 驗證失敗描述文本：最小值：角色列表
     */
    String MESSAGE_ROLE_IDS_MIN_SIZE = "請至少選擇1種角色";

}
