package com.yafeng.nike.admin.account.service;

import com.yafeng.nike.admin.account.pojo.vo.RoleListItemVO;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理角色數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IRoleService {

    /**
     * 查詢角色列表，將使用默認的每頁記錄數
     *
     * @param pageNum  頁碼
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum);

    /**
     * 查詢角色列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize);

}
