package com.yafeng.nike.front.mall.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 收貨地址的實體類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@TableName("mall_receiver_address")
public class ReceiverAddress {

    /**
     * 數據ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用戶ID
     */
    private Long userId;

    /**
     * 收貨人
     */
    private String receiverName;

    /**
     * 收貨電話
     */
    private String receiverPhone;

    /**
     * 市名稱
     */
    private String city;

    /**
     * 市編碼
     */
    private String cityCode;

    /**
     * 區名稱
     */
    private String area;

    /**
     * 區編碼
     */
    private String areaCode;

    /**
     * 詳細地址
     */
    private String detail;

    /**
     * 是否默認
     */
    private Integer isDefault;

    /**
     * 數據創建時間
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 數據最後修改時間
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}
