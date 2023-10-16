package com.yafeng.nike.admin.mall.service;

import com.yafeng.nike.admin.mall.pojo.vo.CommentListItemVO;
import com.yafeng.nike.common.consts.data.MallConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICommentService extends MallConsts {

    /**
     * 審核通過評論
     *
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試審核通過的評論的ID
     * @param remarks          備注信息
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 拒絕審核評論
     *
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試拒絕審核的評論的ID
     * @param remarks          備注信息
     */
    void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 查詢商品的評論列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 商品的評論列表
     */
    PageData<CommentListItemVO> list(Integer pageNum);

    /**
     * 查詢商品的評論列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品的評論列表
     */
    PageData<CommentListItemVO> list(Integer pageNum, Integer pageSize);

}
