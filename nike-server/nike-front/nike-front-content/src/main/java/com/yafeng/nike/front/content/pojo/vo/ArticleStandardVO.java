package com.yafeng.nike.front.content.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 標準VO類：內容-文章
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class ArticleStandardVO implements Serializable {

    /**
     * 數據ID
     */
    private Long id;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 作者名字
     */
    private String authorName;

    /**
     * 類別ID
     */
    private Long categoryId;

    /**
     * 類別名稱
     */
    private String categoryName;

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
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    private String keywords;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 排序序號
     */
    private Integer sort;

    /**
     * 讚數量
     */
    private Integer upCount;

    /**
     * 倒讚數量
     */
    private Integer downCount;

    /**
     * 瀏覽量
     */
    private Integer clickCount;

    /**
     * 評論量
     */
    private Integer commentCount;

    /**
     * 是否推薦，0=不推薦，1=推薦
     */
    private Integer isRecommend;

    /**
     * 詳情
     */
    private String detail;

    /**
     * 數據創建時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtCreate;

    /**
     * 數據最後修改時間
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    private LocalDateTime gmtModified;

}
