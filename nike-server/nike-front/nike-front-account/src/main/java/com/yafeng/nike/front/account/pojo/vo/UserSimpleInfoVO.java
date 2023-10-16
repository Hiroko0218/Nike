package com.yafeng.nike.front.account.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用戶簡單信息VO類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class UserSimpleInfoVO implements Serializable {

    /**
     * 數據id
     */
    private Long id;

    /**
     * 用戶名
     */
    private String username;

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
     * 簡介
     */
    private String description;

    /**
     * 最後登錄IP地址（冗餘）
     */
    private String lastLoginIp;

    /**
     * 累計登錄次數（冗餘）
     */
    private Integer loginCount;

    /**
     * 最後登錄時間（冗餘）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtLastLogin;

}
