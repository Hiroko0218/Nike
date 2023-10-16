package com.yafeng.nike.admin.content.service;

import com.yafeng.nike.admin.content.pojo.vo.CheckLogListItemVO;
import com.yafeng.nike.common.consts.data.MallConsts;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理審核日誌的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICheckLogService extends MallConsts {

    /**
     * 查詢文章審核日誌列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listArticleCheckLog(Integer pageNum);

    /**
     * 查詢文章審核日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listArticleCheckLog(Integer pageNum, Integer pageSize);

    /**
     * 查詢評論審核日誌列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum);

    /**
     * 查詢評論審核日誌列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 審核日誌列表
     */
    PageData<CheckLogListItemVO> listCommentCheckLog(Integer pageNum, Integer pageSize);

}
