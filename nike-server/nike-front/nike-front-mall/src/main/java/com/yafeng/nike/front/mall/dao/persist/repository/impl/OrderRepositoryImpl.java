package com.yafeng.nike.front.mall.dao.persist.repository.impl;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import com.yafeng.nike.front.mall.dao.persist.mapper.OrderMapper;
import com.yafeng.nike.front.mall.dao.persist.repository.IOrderRepository;
import com.yafeng.nike.front.mall.pojo.entity.Order;
import com.yafeng.nike.front.mall.pojo.vo.OrderListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.OrderStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理訂單數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    public OrderRepositoryImpl() {
        log.info("創建存儲庫對象：OrderRepositoryImpl");
    }

    @Override
    public int insert(Order order) {
        log.debug("開始執行【插入訂單】的數據訪問，參數：{}", order);
        return orderMapper.insert(order);
    }

    @Override
    public OrderStandardVO getStandardByIdAndUser(Long orderId, Long userId) {
        log.debug("開始執行【根據訂單ID查詢用戶的訂單】的數據訪問，訂單：{}，用戶：{}", orderId, userId);
        return orderMapper.getStandardByIdAndUser(orderId, userId);
    }

    @Override
    public PageData<OrderListItemVO> listByUser(Long userId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據用戶查詢訂單列表】的數據訪問，用戶：{}，頁碼：{}，每頁記錄數：{}", userId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<OrderListItemVO> list = orderMapper.listByUser(userId);
        PageInfo<OrderListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
