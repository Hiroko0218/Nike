package com.yafeng.nike.passport.pojo.entity;

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
@TableName("account_login_log")
public class LoginLog implements Serializable {

    /**
     * 數據id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用戶ID
     */
    private Long userId;

    /**
     * 用戶名
     */
    private String username;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 瀏覽器信息
     */
    private String userAgent;

    /**
     * 登入時間
     */
    private LocalDateTime gmtLogin;

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
