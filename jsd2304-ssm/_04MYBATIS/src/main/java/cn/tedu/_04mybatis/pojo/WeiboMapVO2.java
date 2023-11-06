package cn.tedu._04mybatis.pojo;

import java.util.List;

public class WeiboMapVO2 {
    private Integer id;
    private String nickname;
    // 指定用戶發的所有微博信息
    private List<Weibo> weibos;

    @Override
    public String toString() {
        return "WeiboMapVO2{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", weibos=" + weibos +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
