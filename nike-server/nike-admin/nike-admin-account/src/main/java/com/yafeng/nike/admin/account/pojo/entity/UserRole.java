package com.yafeng.nike.admin.account.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用戶與角色的關聯的實體類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@TableName("account_user_role")
public class UserRole implements Serializable {

    /**
     * 數據id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用戶id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

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
