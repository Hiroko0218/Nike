package cn.tedu._051mvcboot01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注解: 將類標記為控制器,可以用戶請求
 */
@Controller
public class OrderController {
    /**
     * 請求地址： http://localhost:8080/selectOrder
     *
     * @RequestMapping注解：請求注解,匹配URL地址中訪问資源的路徑
     * @ResponseBody注解：使控制器方法通過返回值的方式響應给客戶端
     */
    @RequestMapping("/selectOrder")
    @ResponseBody
    public String selectOrder() {
        return "訂單查詢成功";
    }
}
