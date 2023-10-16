package com.yafeng.nike.admin.account.dao.persist.repository;

import com.yafeng.nike.admin.account.pojo.entity.User;
import com.yafeng.nike.admin.account.pojo.vo.UserListItemVO;
import com.yafeng.nike.admin.account.pojo.vo.UserStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;

/**
 * 處理用戶數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IUserRepository {

    /**
     * 插入用戶數據
     *
     * @param user 用戶數據
     * @return 受影響的行數
     */
    int insert(User user);

    /**
     * 根據用戶ID刪除用戶數據
     *
     * @param id 用戶id
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據用戶ID修改用戶的數據
     *
     * @param user 封裝了用戶id和新的數據的對象
     * @return 受影響的行數
     */
    int updateById(User user);

    /**
     * 根據用戶名統計用戶數據的數量
     *
     * @param username 用戶名
     * @return 匹配用戶名的用戶數據的數量
     */
    int countByUsername(String username);

    /**
     * 根據手機號碼統計用戶數據的數量
     *
     * @param phone 手機號碼
     * @return 匹配手機號碼的用戶數據的數量
     */
    int countByPhone(String phone);

    /**
     * 統計匹配手機號碼但非用戶ID的用戶數據的數量，通常用於檢查手機號碼是否被其他用戶占用
     *
     * @param phone  手機號碼
     * @param userId 用戶ID
     * @return 匹配的用戶數據的數量
     */
    int countByPhoneAndNotId(String phone, Long userId);

    /**
     * 根據電子郵箱統計用戶數據的數量
     *
     * @param email 電子郵箱
     * @return 匹配電子郵箱的用戶數據的數量
     */
    int countByEmail(String email);

    /**
     * 統計匹配電子郵箱但非用戶ID的用戶數據的數量，通常用於檢查電子郵箱是否被其他用戶占用
     *
     * @param email  電子郵箱
     * @param userId 用戶ID
     * @return 匹配的用戶數據的數量
     */
    int countByEmailAndNotId(String email, Long userId);

    /**
     * 根據用戶ID查詢用戶數據詳情
     *
     * @param id 用戶id
     * @return 匹配的用戶數據詳情，如果沒有匹配的數據，則返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查詢用戶數據列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 用戶數據列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);

}
