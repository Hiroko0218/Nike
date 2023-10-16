package com.yafeng.nike.basic.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 市區實體類
 *
 * @author java@yafeng.com
 * @version 2.0
 **/
@Data
@TableName("dict_district")
public class District implements Serializable {

    /**
     * 數據ID
     */
    @TableId
    private Long id;

    /**
     * 父級單位ID
     */
    private Long parentId;

    /**
     * 行政代號
     */
    private String code;

    /**
     * 名稱
     */
    private String name;

    /**
     * 名稱後綴
     */
    private String suffix;

    /**
     * 名稱拼音
     */
    private String pinyin;

    /**
     * 排序序號
     */
    private Integer sort;

}
