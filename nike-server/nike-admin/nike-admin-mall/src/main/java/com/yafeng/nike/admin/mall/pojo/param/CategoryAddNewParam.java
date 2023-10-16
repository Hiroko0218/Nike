package com.yafeng.nike.admin.mall.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @ApiModelProperty(value = "類別名稱", required = true)
    @NotNull(message = "請提交類別名稱")
    @Pattern(regexp = "^.{1,6}$", message = "類別名稱必須由1~6個字符組成")
    private String name;

    /**
     * 父級類別ID，如果無父級，則為0
     */
    @ApiModelProperty(value = "父級類別ID，如果無父級，則為0", required = true)
    @NotNull(message = "請選擇父級類別")
    @Range(message = "請提交有效的父級類別")
    private Long parentId;

    /**
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    @ApiModelProperty("關鍵詞列表，各關鍵詞使用英文的逗號分隔")
    private String keywords;

    /**
     * 排序序號
     */
    @ApiModelProperty("排序序號")
    @NotNull(message = "請提交排序序號")
    @Range(max = 255, message = "排序序號必須是0~255之間的數字")
    private Integer sort;

    /**
     * 圖標圖片的URL
     */
    @ApiModelProperty("圖標圖片的URL")
    private String icon;

    /**
     * 是否啟用，1=啟用，0=未啟用
     */
    @ApiModelProperty("是否啟用，1=啟用，0=未啟用")
    @NotNull(message = "請提交是否啟用的狀態")
    @Range(max = 1, message = "啟用狀態的值必須是0或者1")
    private Integer enable;

    /**
     * 是否顯示在導航欄中，1=啟用，0=未啟用
     */
    @ApiModelProperty("是否顯示在導航欄中，1=啟用，0=未啟用")
    @NotNull(message = "請提交是否顯示的狀態")
    @Range(max = 1, message = "顯示狀態的值必須是0或者1")
    private Integer isDisplay;

}
