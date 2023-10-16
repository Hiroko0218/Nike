package com.yafeng.nike.front.mall.service;

import com.yafeng.nike.common.consts.data.MallConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.param.OrderAddNewParam;
import com.yafeng.nike.front.mall.pojo.vo.OrderListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.OrderStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理訂單數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IOrderService extends MallConsts {

    /**
     * 創建訂單
     *
     * @param currentPrincipal 當事人
     * @param orderAddNewParam 創建訂單的參數對象
     * @return 訂單ID
     */
    Long create(CurrentPrincipal currentPrincipal, OrderAddNewParam orderAddNewParam);

    /**
     * 根據ID查詢訂單
     *
     * @param currentPrincipal 當事人
     * @param orderId          訂單ID
     * @return 訂單詳情
     */
    OrderStandardVO getStandardById(CurrentPrincipal currentPrincipal, Long orderId);

    /**
     * 查詢訂單列表，將使用默認的每頁記錄數
     *
     * @param currentPrincipal 當事人
     * @param pageNum 頁碼
     * @return 訂單列表
     */
    PageData<OrderListItemVO> listByUser(CurrentPrincipal currentPrincipal, Integer pageNum);

    /**
     * 查詢訂單列表
     *
     * @param currentPrincipal 當事人
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 訂單列表
     */
    PageData<OrderListItemVO> listByUser(CurrentPrincipal currentPrincipal, Integer pageNum, Integer pageSize);

}
