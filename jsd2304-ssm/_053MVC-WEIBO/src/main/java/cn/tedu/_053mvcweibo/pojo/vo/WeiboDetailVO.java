package cn.tedu._053mvcweibo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * 微博詳情頁VO
 */
@Data
public class WeiboDetailVO {
    //微博id
    private Integer id;
    private String content;
    private String nickname;
    /*
        年：year 月：Month 日：day  時：Hour  分：minute  秒：second
        格式匹配：小大小,大小小
          yyyyMMddHHmmss
          yyyy-MM-dd HH:mm:ss
          yyyy/MM/dd HH:mm:ss
          yyyy年MM月dd日 HH时mm分ss秒

          GMT：格林威治時間
     */
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date created;
}
