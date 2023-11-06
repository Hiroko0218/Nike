package cn.tedu._053mvcweibo.pojo.vo;

import lombok.Data;

/**
 * 注册功能VO、登入功能VO
 */
@Data
public class UserVO {
    /*
        id: id给到客戶端,後期可以執行發送微博等功能
        password: 後期登入功能需要校驗密碼
        nickname: 後期登入成功後需要在頁面中顯示 歡迎 nickname 回来
     */
    private Integer id;
    private String password;
    private String nickname;
}
