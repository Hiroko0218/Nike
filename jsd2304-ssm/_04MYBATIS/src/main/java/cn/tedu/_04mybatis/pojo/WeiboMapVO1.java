package cn.tedu._04mybatis.pojo;

public class WeiboMapVO1 {
    private String id;
    private String content;
    private Integer userId;

    @Override
    public String toString() {
        return "WeiboMapVO1{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
