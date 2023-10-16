package com.yafeng.nike.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用戶登入信息的VO類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class UserLoginInfoVO implements Serializable {

    /**
     * 數據id
     */
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
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 登入次數
     */
    private Integer loginCount;

    /**
     * 權限列表
     */
    private List<String> permissions;

}
