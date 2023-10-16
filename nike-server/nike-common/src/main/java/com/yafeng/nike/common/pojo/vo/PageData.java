package com.yafeng.nike.common.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分頁數據類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@Accessors(chain = true)
public class PageData<T> implements Serializable {

    /**
     * 每頁記錄數
     */
    private Integer pageSize;

    /**
     * 記錄總數
     */
    private Long total;

    /**
     * 當前頁碼
     */
    private Integer currentPage;

    /**
     * 最大頁碼
     */
    private Integer maxPage;

    /**
     * 數據列表
     */
    private List<T> list;

    
}

