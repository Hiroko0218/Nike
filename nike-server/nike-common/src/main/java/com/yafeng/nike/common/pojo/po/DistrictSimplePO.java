package com.yafeng.nike.common.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 市區VO類
 *
 * @author java@yafeng.com
 * @version 2.0
 **/
@Data
public class DistrictSimplePO implements Serializable {

    /**
     * 數據ID
     */
    private Long id;
    /**
     * 行政代號
     */
    private String code;
    /**
     * 名稱
     */
    private String name;
    /**
     * 名稱拼音
     */
    private String pinyin;

}
