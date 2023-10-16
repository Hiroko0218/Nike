package com.yafeng.nike.front.account.pojo.param;

import com.yafeng.nike.common.validation.account.UserRules;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用戶注冊的參數類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class UserRegisterParam implements Serializable, UserRules {

    /**
     * 用戶名
     */
    @NotNull(message = MESSAGE_USERNAME_NOT_NULL)
    @Pattern(regexp = PATTERN_USERNAME, message = MESSAGE_USERNAME_PATTERN)
    @ApiModelProperty(value = "用戶名", required = true, example = "student")
    private String username;

    /**
     * 密碼（原文）
     */
    @NotNull(message = MESSAGE_PASSWORD_NOT_NULL)
    @Pattern(regexp = PATTERN_PASSWORD, message = MESSAGE_PASSWORD_PATTERN)
    @ApiModelProperty(value = "密碼", required = true, example = "123456")
    private String password;

    /**
     * 頭像URL
     */
    @ApiModelProperty(value = "頭像URL")
    private String avatar;

    /**
     * 手機號碼
     */
    @NotNull(message = MESSAGE_PHONE_NOT_NULL)
    @Pattern(regexp = PATTERN_PHONE, message = MESSAGE_PHONE_PATTERN)
    @ApiModelProperty(value = "手機號碼", required = true, example = "13800138008")
    private String phone;

    /**
     * 電子郵箱
     */
    @NotNull(message = MESSAGE_EMAIL_NOT_NULL)
    @Pattern(regexp = PATTERN_EMAIL, message = MESSAGE_EMAIL_PATTERN)
    @ApiModelProperty(value = "電子郵箱", required = true, example = "student@tedu.cn")
    private String email;

}
