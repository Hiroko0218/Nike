package cn.tedu.baking.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ContentManagementVO {
    private Long id;
    private String title;
    private String imgUrl;
    private String brief;
    private Long type;
    private String categoryName; //和分類表關聯查詢
    private Integer viewCount;
    private Integer commentCount;
    // dd日MM月yyyy年 HH時mm分ss秒
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
