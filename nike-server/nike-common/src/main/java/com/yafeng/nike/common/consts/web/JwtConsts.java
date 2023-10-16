package com.yafeng.nike.common.consts.web;

/**
 * JWT的相關常量
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface JwtConsts {

    /**
     * JWT最小長度值
     */
    int JWT_MIN_LENGTH = 113;

    /**
     * 用戶ID
     */
    String CLAIM_USER_ID = "id";

    /**
     * 用戶名
     */
    String CLAIM_USER_NAME = "username";

    /**
     * 瀏覽器信息
     */
    String CLAIM_USER_AGENT = "userAgent";

    /**
     * IP地址
     */
    String CLAIM_REMOTE_ADDR = "ip";

}
