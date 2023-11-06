package cn.tedu._053mvcweibo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登入功能DTO
 */
@Data
public class UserLoginDTO {
    @ApiModelProperty(value = "用戶名",required = true,example = "Tony")
    private String username;
    @ApiModelProperty(value = "密碼",required = true,example = "123456")
    private String password;
}
