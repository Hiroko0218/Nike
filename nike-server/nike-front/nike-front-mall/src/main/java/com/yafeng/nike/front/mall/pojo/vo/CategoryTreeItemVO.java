package com.yafeng.nike.front.mall.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 類別完整"樹"結構節點項的VO類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Data
@Accessors(chain = true)
public class CategoryTreeItemVO implements Serializable {

    /**
     * 數據ID，Element UI控件要求名為value
     */
    @ApiModelProperty("類別ID")
    private Long value;

    /**
     * 類別名稱，Element UI控件要求名為label
     */
    @ApiModelProperty("類別名稱")
    private String label;

    /**
     * 子級類別列表，Element UI控件要求名為children
     */
    @ApiModelProperty("子級類別列表")
    private List<CategoryTreeItemVO> children;

}
