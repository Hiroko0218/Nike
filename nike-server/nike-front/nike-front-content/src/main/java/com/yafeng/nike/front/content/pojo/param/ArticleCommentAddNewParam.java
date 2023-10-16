package com.yafeng.nike.front.content.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 新增文章評論的參數類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class ArticleCommentAddNewParam implements Serializable {

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * 評論內容
     */
    private String content;

}
