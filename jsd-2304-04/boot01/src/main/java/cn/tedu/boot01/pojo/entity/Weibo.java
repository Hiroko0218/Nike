package cn.tedu.boot01.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Weibo {
    private Integer id;
    private String content;
    private Date created;
    private Integer userId;

}
