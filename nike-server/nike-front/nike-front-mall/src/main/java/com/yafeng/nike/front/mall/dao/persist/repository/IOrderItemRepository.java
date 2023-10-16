package com.yafeng.nike.front.mall.dao.persist.repository;

import com.yafeng.nike.front.mall.pojo.entity.OrderItem;

/**
 * 處理訂單項數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IOrderItemRepository {

    /**
     * 插入訂單項數據
     *
     * @param orderItem 訂單項數據
     * @return 受影響的行數
     */
    int insert(OrderItem orderItem);

}
