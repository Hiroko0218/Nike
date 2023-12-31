package com.yafeng.nike.front.content.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表項VO類：內容-類別
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
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    private String keywords;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    private Integer enable;

    /**
     * 是否顯示在導航欄中，1=啟用，0=未啟用
     */
    private Integer isDisplay;

}
