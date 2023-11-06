package cn.tedu.baking.pojo.vo;


import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String password;
    private String nickName;
    private String imgUrl;
    private Integer isAdmin;
}
