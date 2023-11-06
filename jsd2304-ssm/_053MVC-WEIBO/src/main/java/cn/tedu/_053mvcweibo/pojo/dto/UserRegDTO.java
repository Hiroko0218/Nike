package cn.tedu._053mvcweibo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserRegDTO {
    //ApiModelProperty註解：在Knife4j頁面中顯示參數描述,是否必須,以及示例值
    @ApiModelProperty(value = "用戶名", required = true, example = "Tony")
    /**
     * NotNull註解：不允許為空值NULL
     * NotEmpty註解：不允許為空字符串,也不允許為NULL
     * NotBlank註解：不允許為空白,也不允許為空字符串,也不允許為NULL
     * Size註解：限定字符串長度,閉區間(min和max的值都是包含的)
     */
//    @NotNull(message = "用户名不能为NULL")
//    @NotEmpty(message = "用户名不能为空")
//    @NotBlank(message = "用户名不能为空")
    @Size(min = 6, max = 12, message = "用戶名必須在6-12位之間")
    private String username;
    @ApiModelProperty(value = "密碼", required = true, example = "123456")
    private String password;
    @ApiModelProperty(value = "暱稱", required = true, example = "tony")
    private String nickname;
}
