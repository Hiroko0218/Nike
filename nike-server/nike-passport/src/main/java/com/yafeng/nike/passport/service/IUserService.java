package com.yafeng.nike.passport.service;

import com.yafeng.nike.common.consts.web.JwtConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.passport.pojo.param.UserLoginInfoParam;
import com.yafeng.nike.passport.pojo.vo.UserLoginResultVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理用戶數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IUserService extends JwtConsts {

    /**
     * 用戶登入
     *
     * @param userLoginInfoParam 封裝了登入信息的對象
     * @param remoteAddr         客戶端的IP地址
     * @param userAgent          客戶端的瀏覽器信息
     * @return 成功登入的用戶的信息，包括：ID、用戶名、頭像、JWT等數據
     */
    UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam, String remoteAddr, String userAgent);

    /**
     * 退出登入
     *
     * @param currentPrincipal 當事人
     */
    void logout(CurrentPrincipal currentPrincipal);

}
