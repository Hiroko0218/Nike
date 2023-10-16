package com.yafeng.nike.admin.content.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 列表項VO類：商城-審核日誌
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class CheckLogListItemVO implements Serializable {

    /**
     * 數據ID
     */
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
    private String checkUsername;

    /**
     * 審核備注
     */
    private String checkRemarks;

    /**
     * 審核時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtCheck;

}
