package cn.tedu._051mvcboot01.controller;

import cn.tedu._051mvcboot01.pojo.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    /** 接收參數方式 1：通過 request 接收 */
//    @RequestMapping("v1/users/login")
//    @ResponseBody
//    public String login(HttpServletRequest request){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        return username+","+password;
//    }

    /** 接收参數方式 2：通過 聲明參數方式接收
     * 關於參數名命名
     * 1.GET請求：參數名為查詢參數中的名字
     * 2.POST請求：參數名為form表單中 input 標籤的name的屬性值
     */
//    @RequestMapping("v1/users/login")
//    @ResponseBody
//    public String login(String username,String password){
//        return username+","+password;
//    }

    /**
     * 接收参數方式 3：通過 POJO類 方式接收
     */
    @RequestMapping("v1/users/login")
    @ResponseBody
    public String login(UserLogin userLogin) {
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();
        return username + "," + password;
    }
}
