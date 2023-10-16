package com.yafeng.nike.front.mall.dao.cache;

import com.yafeng.nike.common.consts.cache.CartCacheConsts;
import com.yafeng.nike.front.mall.pojo.po.CartPO;

import java.util.List;

/**
 * 處理購物車緩存數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICartCacheRepository extends CartCacheConsts {

    /**
     * 向購物車中存入數據，即用於增加，也用於修改
     *
     * @param userId 用戶ID
     * @param cartPO 購物車數據
     */
    void put(Long userId, CartPO cartPO);

    /**
     * 根據用戶與商品刪除購物車中的數據
     *
     * @param userId  用戶ID
     * @param goodsId 商品ID
     * @return 成功刪除的數據量
     */
    long deleteByUserAndGoods(Long userId, Long goodsId);

    /**
     * 根據用戶與商品查詢購物車中的數據
     *
     * @param userId  用戶ID
     * @param goodsId 商品ID
     * @return 購物車數據
     */
    CartPO getByUserAndGoods(Long userId, Long goodsId);

    /**
     * 根據用戶查詢購物車數據的列表
     *
     * @param userId 用戶ID
     * @return 匹配的用戶的購物車數據的列表
     */
    List<CartPO> listByUser(Long userId);

}
