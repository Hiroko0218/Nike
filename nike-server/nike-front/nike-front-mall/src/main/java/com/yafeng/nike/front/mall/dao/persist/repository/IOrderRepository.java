package com.yafeng.nike.front.mall.dao.persist.repository;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.entity.Order;
import com.yafeng.nike.front.mall.pojo.vo.OrderListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.OrderStandardVO;

/**
 * 處理訂單數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IOrderRepository {

    /**
     * 插入訂單數據
     *
     * @param order 訂單數據
     * @return 受影響的行數
     */
    int insert(Order order);

    /**
     * 根據訂單ID查詢用戶的訂單
     *
     * @param orderId 訂單ID
     * @param userId  用戶ID
     * @return 訂單詳情
     */
    OrderStandardVO getStandardByIdAndUser(Long orderId, Long userId);

    /**
     * 根據用戶查詢訂單列表
     *
     * @param userId   用戶ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 訂單列表
     */
    PageData<OrderListItemVO> listByUser(Long userId, Integer pageNum, Integer pageSize);

}
