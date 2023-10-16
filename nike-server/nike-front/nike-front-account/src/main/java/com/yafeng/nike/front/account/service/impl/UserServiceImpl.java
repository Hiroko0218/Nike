package com.yafeng.nike.front.account.service.impl;

import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.front.account.dao.persist.repository.IUserRepository;
import com.yafeng.nike.front.account.pojo.entity.User;
import com.yafeng.nike.front.account.pojo.param.UserRegisterParam;
import com.yafeng.nike.front.account.pojo.param.UserUpdateInfoParam;
import com.yafeng.nike.front.account.pojo.vo.UserSimpleInfoVO;
import com.yafeng.nike.front.account.pojo.vo.UserStandardVO;
import com.yafeng.nike.front.account.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 處理用戶數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        log.debug("創建業務類對象：UserServiceImpl");
    }

    @Override
    public void register(UserRegisterParam userRegisterParam) {
        log.debug("開始處理【用戶注冊】的業務，參數：{}", userRegisterParam);

        // 檢查用戶名是否被占用
        {
            String username = userRegisterParam.getUsername();
            int count = userRepository.countByUsername(username);
            if (count > 0) {
                String message = "添加用戶失敗，用戶名已經被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 檢查手機號碼是否被占用
        {
            String phone = userRegisterParam.getPhone();
            int count = userRepository.countByPhone(phone);
            if (count > 0) {
                String message = "添加用戶失敗，手機號碼已經被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 檢查電子郵箱是否被占用
        {
            String email = userRegisterParam.getEmail();
            int count = userRepository.countByEmail(email);
            if (count > 0) {
                String message = "添加用戶失敗，電子郵箱已經被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterParam, user);
        user.setLoginCount(0);
        user.setEnable(1);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        int rows = userRepository.insert(user);
        if (rows != 1) {
            String message = "注冊失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void updateInfo(CurrentPrincipal currentPrincipal, UserUpdateInfoParam userUpdateInfoParam) {
        log.debug("開始處理【修改基本信息】的業務，當事人：{}，新基本信息：{}", currentPrincipal, userUpdateInfoParam);
        Long userId = currentPrincipal.getId();
        User user = new User();
        BeanUtils.copyProperties(userUpdateInfoParam, user);
        user.setId(userId);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改基本信息失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void updatePassword(CurrentPrincipal currentPrincipal, String oldPassword, String newPassword) {
        log.debug("開始處理【修改密碼】的業務，當事人：{}，原密碼：{}，新密碼：{}", currentPrincipal, oldPassword, newPassword);
        Long userId = currentPrincipal.getId();
        UserStandardVO queryResult = userRepository.getStandardById(userId);
        String dbPassword = queryResult.getPassword();
        boolean matches = passwordEncoder.matches(oldPassword, dbPassword);
        if (!matches) {
            String message = "修改密碼失敗，原密碼錯誤！";
            log.warn(message);
            log.trace("用戶提交的原密碼：{}", oldPassword);
            log.trace("數據庫中的密文：{}", dbPassword);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        String encodedPassword = passwordEncoder.encode(newPassword);
        User user = new User();
        user.setId(userId);
        user.setPassword(encodedPassword);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改密碼失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void updateAvatar(CurrentPrincipal currentPrincipal, String avatar) {
        log.debug("開始處理【修改頭像】的業務，當事人：{}，新頭像：{}", currentPrincipal, avatar);
        Long userId = currentPrincipal.getId();
        User user = new User();
        user.setId(userId);
        user.setAvatar(avatar);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改頭像失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void updatePhone(CurrentPrincipal currentPrincipal, String phone) {
        log.debug("開始處理【修改手機號碼】的業務，當事人：{}，新手機號碼：{}", currentPrincipal, phone);
        Long userId = currentPrincipal.getId();
        int count = userRepository.countByPhoneAndNotId(phone, userId);
        if (count > 0) {
            String message = "修改手機號碼失敗，手機號碼已經被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(userId);
        user.setPhone(phone);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改手機號碼失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public void updateEmail(CurrentPrincipal currentPrincipal, String email) {
        log.debug("開始處理【修改電子郵箱】的業務，當事人：{}，新手機號碼：{}", currentPrincipal, email);
        Long userId = currentPrincipal.getId();
        int count = userRepository.countByEmailAndNotId(email, userId);
        if (count > 0) {
            String message = "修改電子郵箱失敗，電子郵箱已經被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改電子郵箱失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    @Override
    public UserSimpleInfoVO getSelfSimpleInfo(CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【查詢當前用戶基本信息】的業務，當事人：{}", currentPrincipal);
        Long userId = currentPrincipal.getId();
        UserSimpleInfoVO queryResult = userRepository.getSimpleInfoById(userId);
        if (queryResult == null) {
            String message = "查詢當前用戶基本信息失敗，嘗試訪問的用戶數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

}
