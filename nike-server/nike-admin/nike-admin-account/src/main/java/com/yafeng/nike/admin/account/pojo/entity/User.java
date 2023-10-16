package com.yafeng.nike.admin.account.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用戶的實體類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@TableName("account_user")
public class User implements Serializable {

    /**
     * 數據id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用戶名
     */
    private String username;

    /**
     * 密碼（密文）
     */
    private String password;

    /**
     * 頭像URL
     */
    private String avatar;

    /**
     * 手機號碼
     */
    private String phone;

    /**
     * 電子郵箱
     */
    private String email;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 最後登入IP地址（冗餘）
     */
    private String lastLoginIp;

    /**
     * 累計登入次數（冗餘）
     */
    private Integer loginCount;

    /**
     * 最後登入時間（冗餘）
     */
    private LocalDateTime gmtLastLogin;

    /**
     * 數據創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 數據最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}
