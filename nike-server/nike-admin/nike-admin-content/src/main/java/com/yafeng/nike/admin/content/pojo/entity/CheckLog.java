package com.yafeng.nike.admin.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 實體類：內容-審核日誌
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@TableName("content_check_log")
public class CheckLog implements Serializable {

    /**
     * 數據ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 資源類型，0=文章，1=評論
     */
    private Integer resourceType;

    /**
     * 資源ID
     */
    private Long resourceId;

    /**
     * 資源摘要，截取的文章標題或評論
     */
    private String resourceBrief;

    /**
     * 原審核狀態
     */
    private Integer originalState;

    /**
     * 新審核狀態
     */
    private Integer newState;

    /**
     * 審核人ID
     */
    private Long checkUserId;

    /**
     * 審核人用戶名
     */
    @TableField("check_user_name")
    private String checkUsername;

    /**
     * 審核備注
     */
    private String checkRemarks;

    /**
     * 審核時間
     */
    private LocalDateTime gmtCheck;

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

