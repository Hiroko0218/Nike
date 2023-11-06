package cn.tedu.baking.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String nickName;
    private Date createTime;
    private Integer isAdmin;
    private String imgUrl;
}
