package com.yafeng.nike.admin.content.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增類別的參數類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class CategoryAddNewParam implements Serializable {

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

