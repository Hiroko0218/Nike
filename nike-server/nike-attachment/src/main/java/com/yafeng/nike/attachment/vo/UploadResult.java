package com.yafeng.nike.attachment.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上傳的結果
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class UploadResult implements Serializable {

    /**
     * 文件URL
     */
    private String url;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文檔MIME類型
     */
    private String contentType;

    /**
     * 文件名
     */
    private String fileName;

}