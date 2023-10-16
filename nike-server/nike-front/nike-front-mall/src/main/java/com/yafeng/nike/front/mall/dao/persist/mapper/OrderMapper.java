package com.yafeng.nike.front.mall.dao.persist.mapper;

import com.yafeng.nike.front.mall.pojo.entity.Order;
import com.yafeng.nike.front.mall.pojo.vo.OrderListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.OrderStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理訂單數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根據訂單ID查詢用戶的訂單
     *
     * @param orderId 訂單ID
     * @param userId  用戶ID
     * @return 訂單詳情
     */
    OrderStandardVO getStandardByIdAndUser(@Param("orderId") Long orderId, @Param("userId") Long userId);

    /**
     * 根據用戶查詢訂單列表
     *
     * @param userId 用戶ID
     * @return 訂單列表
     */
    List<OrderListItemVO> listByUser(Long userId);

}
