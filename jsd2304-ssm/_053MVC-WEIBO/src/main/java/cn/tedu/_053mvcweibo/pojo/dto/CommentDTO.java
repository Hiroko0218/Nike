package cn.tedu._053mvcweibo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 發布評論DTO
 */
@Data
public class CommentDTO {
    @ApiModelProperty(value = "微博id",required = true)
    private Integer weiboId;
    @ApiModelProperty(value = "評論內容",required = true)
    private String content;
}
