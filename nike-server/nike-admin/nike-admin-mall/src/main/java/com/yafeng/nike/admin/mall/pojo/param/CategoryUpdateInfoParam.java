package com.yafeng.nike.admin.mall.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 修改類別的參數類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
public class CategoryUpdateInfoParam  implements Serializable {

    /**
     * 類別名稱
     */
    @ApiModelProperty(value = "類別名稱", required = true)
    @NotNull(message = "請提交類別名稱")
    @Pattern(regexp = "^.{1,6}$", message = "類別名稱必須由1~6個字符組成")
    private String name;

    /**
     * 關鍵詞列表，各關鍵詞使用英文的逗號分隔
     */
    @ApiModelProperty("關鍵詞列表，各關鍵詞使用英文的逗號分隔")
    private String keywords;

    @ApiModelProperty("排序序號")
    @NotNull(message = "請提交排序序號")
    @Range(max = 255, message = "排序序號必須是0~255之間的數字")
    private Integer sort;

    /**
     * 圖標圖片的URL
     */
    @ApiModelProperty("圖標圖片的URL")
    private String icon;

}
