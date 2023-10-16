package com.yafeng.nike.admin.mall.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增商品的參數類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class GoodsAddNewParam implements Serializable {

    /**
     * 類別ID
     */
    private Long categoryId;

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
    private Long salePrice;

    /**
     * 關鍵詞列表
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 詳情
     */
    private String detail;

}

