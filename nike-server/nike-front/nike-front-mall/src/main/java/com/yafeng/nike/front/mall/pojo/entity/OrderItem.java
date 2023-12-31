package com.yafeng.nike.front.mall.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 實體類：訂單項（訂單中包含的商品）
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@TableName("mall_order_item")
public class OrderItem implements Serializable {

    /**
     * 數據ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 訂單ID
     */
    private Long orderId;

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

    /**
     * 數據創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 數據最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}
