package com.yafeng.nike.front.account.service;

import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.front.account.pojo.param.UserRegisterParam;
import com.yafeng.nike.front.account.pojo.param.UserUpdateInfoParam;
import com.yafeng.nike.front.account.pojo.vo.UserSimpleInfoVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理用戶數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IUserService {

    /**
     * 用戶注冊
     *
     * @param userRegisterParam 注冊參數
     */
    void register(UserRegisterParam userRegisterParam);

    /**
     * 修改基本信息
     *
     * @param currentPrincipal    當事人
     * @param userUpdateInfoParam 新的基本信息
     */
    void updateInfo(CurrentPrincipal currentPrincipal, UserUpdateInfoParam userUpdateInfoParam);

    /**
     * 修改密碼
     *
     * @param currentPrincipal 當事人
     * @param oldPassword      原密碼
     * @param newPassword      新密碼
     */
    void updatePassword(CurrentPrincipal currentPrincipal, String oldPassword, String newPassword);

    /**
     * 修改頭像
     *
     * @param currentPrincipal 當事人
     * @param avatar           新頭像的URL
     */
    void updateAvatar(CurrentPrincipal currentPrincipal, String avatar);

    /**
     * 修改手機號碼
     *
     * @param currentPrincipal 當事人
     * @param phone            新手機號碼
     */
    void updatePhone(CurrentPrincipal currentPrincipal, String phone);

    /**
     * 修改電子郵箱
     *
     * @param currentPrincipal 當事人
     * @param email            新電子郵箱
     */
    void updateEmail(CurrentPrincipal currentPrincipal, String email);

    /**
     * 查詢當前用戶基本信息
     *
     * @param currentPrincipal 當事人
     * @return 匹配的用戶基本信息，如果沒有匹配的數據，則返回null
     */
    UserSimpleInfoVO getSelfSimpleInfo(CurrentPrincipal currentPrincipal);

}
