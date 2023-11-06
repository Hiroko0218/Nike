package cn.tedu._053mvcweibo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 指定微博的所有評論VO
 */
@Data
public class CommentVO {
    //評論的id
    private Integer id;
    private String content;
    private String nickname;
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date created;
}
