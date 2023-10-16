package com.yafeng.nike.front.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 標準VO類：訂單項信息
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class OrderItemStandardVO implements Serializable {

    /**
     * 數據ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品封面圖
     */
    private String goodsCoverUrl;

    /**
     * 商品標題
     */
    private String goodsTitle;

    /**
     * 商品數量
     */
    private Integer goodsNum;

    /**
     * 商品單價
     */
    private BigDecimal saleUnitPrice;

}
