package com.yafeng.nike.admin.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表項VO類：商品-類別
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class CategoryListItemVO implements Serializable {

    /**
     * 數據ID
     */
    private Long id;

    /**
     * 類別名稱
     */
    private String name;

    /**
     * 父級類別ID，如果無父級，則為0
     */
    private Long parentId;

    /**
     * 深度，最讚級類別的深度為1，次級為2，以此類推
     */
    private Integer depth;

    /**
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 圖標圖片的URL
     */
    private String icon;

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 是否為父級（是否包含子級），1=是父級，0=不是父級
     */
    private Integer isParent;

    /**
     * 是否顯示在導航欄中，1=啟用，0=未啟用
     */
    private Integer isDisplay;

}
