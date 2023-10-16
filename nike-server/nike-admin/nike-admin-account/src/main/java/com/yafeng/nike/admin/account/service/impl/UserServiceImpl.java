package com.yafeng.nike.admin.account.service.impl;


import com.yafeng.nike.admin.account.dao.cache.IUserCacheRepository;
import com.yafeng.nike.admin.account.dao.persist.repository.IUserRepository;
import com.yafeng.nike.admin.account.dao.persist.repository.IUserRoleRepository;
import com.yafeng.nike.admin.account.pojo.entity.User;
import com.yafeng.nike.admin.account.pojo.entity.UserRole;
import com.yafeng.nike.admin.account.pojo.param.UserAddNewParam;
import com.yafeng.nike.admin.account.pojo.param.UserUpdateInfoParam;
import com.yafeng.nike.admin.account.pojo.vo.UserListItemVO;
import com.yafeng.nike.admin.account.pojo.vo.UserStandardVO;
import com.yafeng.nike.admin.account.service.IUserService;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 處理用戶數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Value("${nike.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserCacheRepository userCacheRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;

    public UserServiceImpl() {
        log.debug("創建業務類對象：UserServiceImpl");
    }

    @Override
    public void addNew(UserAddNewParam userAddNewParam) {
        log.debug("開始處理【添加用戶】的業務，參數：{}", userAddNewParam);

        // 檢查用戶名是否被占用
        {
            String username = userAddNewParam.getUsername();
            int count = userRepository.countByUsername(username);
            if (count > 0) {
                String message = "添加用戶失敗，用戶名已經被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 檢查手機號碼是否被占用
        {
            String phone = userAddNewParam.getPhone();
            int count = userRepository.countByPhone(phone);
            if (count > 0) {
                String message = "添加用戶失敗，手機號碼已經被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        // 檢查電子郵箱是否被占用
        {
            String email = userAddNewParam.getEmail();
            int count = userRepository.countByEmail(email);
            if (count > 0) {
                String message = "添加用戶失敗，電子郵箱已經被占用！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
            }
        }

        User user = new User();
        BeanUtils.copyProperties(userAddNewParam, user);
        user.setLoginCount(0);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        int rows = userRepository.insert(user);
        if (rows != 1) {
            String message = "添加用戶失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Long[] roleIds = userAddNewParam.getRoleIds();
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        rows = userRoleRepository.insertBatch(userRoleList);
        if (rows < 1) {
            String message = "添加用戶失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("開始處理【根據ID刪除用戶】的業務，參數：{}", id);
        if (id == 1) {
            String message = "刪除用戶失敗，嘗試訪問的數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        Object queryResult = userRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "刪除用戶失敗，嘗試訪問的數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        log.debug("即將執行刪除數據，參數：{}", id);
        int rows = userRepository.deleteById(id);
        if (rows != 1) {
            String message = "刪除用戶失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        log.debug("即將執行刪除關聯數據，參數：{}", id);
        rows = userRoleRepository.deleteByUserId(id);
        if (rows < 1) {
            String message = "刪除用戶失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        log.debug("即將刪除緩存中的賬號狀態數據，無參數");
        userCacheRepository.deleteUserState(id);
    }

    @Override
    public void updateInfo(Long userId, UserUpdateInfoParam userUpdateInfoParam) {
        log.debug("開始處理【修改基本信息】的業務，用戶ID：{}，新基本信息：{}", userId, userUpdateInfoParam);
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
    public void updatePassword(Long userId, String newPassword) {
        log.debug("開始處理【修改密碼】的業務，用戶ID：{}，新密碼：{}", userId, newPassword);
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
    public void updateAvatar(Long userId, String avatar) {
        log.debug("開始處理【修改頭像】的業務，用戶ID：{}，新頭像：{}", userId, avatar);
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
    public void updatePhone(Long userId, String phone) {
        log.debug("開始處理【修改手機號碼】的業務，用戶ID：{}，新手機號碼：{}", userId, phone);
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
    public void updateEmail(Long userId, String email) {
        log.debug("開始處理【修改電子郵箱】的業務，用戶ID：{}，新手機號碼：{}", userId, email);
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
    public void setEnable(Long userId) {
        log.debug("開始處理【啟用用戶】的業務，參數：{}", userId);
        updateEnableById(userId, ENABLE_STATE_ON);
    }

    @Override
    public void setDisable(Long userId) {
        log.debug("開始處理【禁用用戶】的業務，參數：{}", userId);
        updateEnableById(userId, ENABLE_STATE_OFF);
    }

    @Override
    public UserStandardVO getStandardById(Long userId) {
        log.debug("開始處理【根據ID查詢用戶】業務，參數：{}", userId);
        UserStandardVO currentUser = userRepository.getStandardById(userId);
        if (currentUser == null) {
            String message = "獲取用戶詳情失敗，嘗試訪問的用戶數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return currentUser;
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢用戶列表】的業務，頁碼：{}", pageNum);
        return userRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢用戶列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return userRepository.list(pageNum, pageSize);
    }

    private void updateEnableById(Long id, Integer enable) {
        log.debug("開始處理【{}用戶】的業務，ID：{}，目標狀態：{}", ENABLE_STATE_TEXT[enable], id, enable);
        if (id == 1) {
            String message = ENABLE_STATE_TEXT[enable] + "用戶失敗，嘗試訪問的數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        UserStandardVO queryResult = userRepository.getStandardById(id);
        if (queryResult == null) {
            String message = ENABLE_STATE_TEXT[enable] + "用戶失敗，嘗試訪問的數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (queryResult.getEnable().equals(enable)) {
            String message = ENABLE_STATE_TEXT[enable] + "用戶失敗，當前用戶已經處於"
                    + ENABLE_STATE_TEXT[enable] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(id);
        user.setEnable(enable);
        log.debug("即將修改數據，參數：{}", user);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = ENABLE_STATE_TEXT[enable] + "用戶失敗，服務器忙，請稍後再次嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }

        if (enable == 0) {
            userCacheRepository.setUserDisabled(id);
        }
    }

}
