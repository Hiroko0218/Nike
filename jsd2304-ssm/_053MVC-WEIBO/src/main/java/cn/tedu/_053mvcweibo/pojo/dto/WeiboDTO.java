package cn.tedu._053mvcweibo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 發布微博功能DTO
 */
@Data
public class WeiboDTO {
    @ApiModelProperty(value = "微博內容",required = true)
    private String content;
}
