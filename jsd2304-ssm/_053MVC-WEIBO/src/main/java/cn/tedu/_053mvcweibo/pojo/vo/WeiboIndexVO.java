package cn.tedu._053mvcweibo.pojo.vo;

import lombok.Data;

/**
 * 首頁微博列表展示VO
 */
@Data
public class WeiboIndexVO {
    private Integer wid;
    private String content;
    private String nickname;
}
