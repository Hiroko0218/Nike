package com.yafeng.nike.front.mall.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 實體類：商品信息
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@TableName("mall_goods")
public class Goods implements Serializable {

    /**
     * 數據ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 類別ID
     */
    private Long categoryId;

    /**
     * 類別名稱
     */
    private String categoryName;

    /**
     * 條形碼
     */
    private String barCode;

    /**
     * 標題
     */
    private String title;

    /**
     * 摘要
     */
    private String brief;

    /**
     * 封面圖
     */
    private String coverUrl;

    /**
     * 售價
     */
    private BigDecimal salePrice;

    /**
     * 關鍵詞列表
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 是否推薦
     */
    private Integer isRecommend;

    /**
     * 審核狀態
     */
    private Integer checkState;

    /**
     * 上架狀態，0=下架，1=上架
     */
    private Integer isPutOn;

    /**
     * 銷量
     */
    private Integer salesCount;

    /**
     * 評論數量
     */
    private Integer commentCount;

    /**
     * 好評數量
     */
    private Integer positiveCommentCount;

    /**
     * 差評數量
     */
    private Integer negativeCommentCount;

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
