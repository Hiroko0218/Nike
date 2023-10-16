package com.yafeng.nike.admin.account.pojo.param;

import com.yafeng.nike.common.validation.account.UserRules;
import lombok.Data;

import java.io.Serializable;

/**
 * 修改用戶基本信息的參數類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class UserUpdateInfoParam implements Serializable, UserRules {

    /**
     * 簡介
     */
    private String description;

}
