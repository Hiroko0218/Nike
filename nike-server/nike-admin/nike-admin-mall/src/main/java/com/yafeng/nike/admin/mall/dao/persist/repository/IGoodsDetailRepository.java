package com.yafeng.nike.admin.mall.dao.persist.repository;


import com.yafeng.nike.admin.mall.pojo.entity.GoodsDetail;

/**
 * 處理商品詳情數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IGoodsDetailRepository {

    /**
     * 插入商品詳情數據
     *
     * @param goodsDetail 商品詳情數據
     * @return 受影響的行數
     */
    int insert(GoodsDetail goodsDetail);

    /**
     * 根據商品ID刪除商品詳情數據
     *
     * @param goodsId 商品ID
     * @return 受影響的行數
     */
    int deleteByGoods(Long goodsId);

    /**
     * 根據ID修改商品詳情數據
     *
     * @param goodsDetail 封裝了商品ID和新商品詳情數據的對象
     * @return 受影響的行數
     */
    int updateByGoods(GoodsDetail goodsDetail);

}
