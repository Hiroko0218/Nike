package com.yafeng.nike.admin.account.dao.persist.repository;


import com.yafeng.nike.admin.account.pojo.vo.RoleListItemVO;
import com.yafeng.nike.common.pojo.vo.PageData;

/**
 * 處理角色數據的數據訪問接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IRoleRepository {

    /**
     * 查詢角色列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize);

}
