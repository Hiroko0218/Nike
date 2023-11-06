package cn.tedu._053mvcweibo.controller;

import cn.tedu._053mvcweibo.common.response.JsonResult;
import cn.tedu._053mvcweibo.common.response.StatusCode;
import cn.tedu._053mvcweibo.mapper.UserMapper;
import cn.tedu._053mvcweibo.pojo.dto.UserLoginDTO;
import cn.tedu._053mvcweibo.pojo.dto.UserRegDTO;
import cn.tedu._053mvcweibo.pojo.entity.User;
import cn.tedu._053mvcweibo.pojo.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * RestController：複合注解(Controller和 ResponseBody注解)
 * Api(tags="")注解： Knife4j中顯示模塊名
 * ApiOperation注解：在Knife4j頁面中,指定顯示的接口名稱
 */
@Slf4j
@RestController
@Api(tags = "01.用戶規模")
@RequestMapping("/v1/users/")
public class UserController {

    @Autowired(required = false)
    private UserMapper userMapper;

    /** 1.註冊功能
     * RequestBody： 注解：客戶端 --> 服務端, 將請求體中JSON格式的數據解析為對應的JAVA對象
     * ResponseBody：注解：服務端 --> 客戶端, 將服務端需要返回的JAVA對象轉為JSON格式的字符串
     */
    @PostMapping("reg")
    @ApiOperation(value = "註冊功能")
    public JsonResult reg(@RequestBody @Validated UserRegDTO userRegDTO){
         /*
            只要添加了@Slf4j注解,就會創建一個 log 對象
            log.trace("xxx")
            log.debug("xxx")
            log.info("xxx")
            log.warn("xxx")
            log.error("xxx")
         */
        log.debug("userRegDTO = " + userRegDTO);

        // 1.數據表查詢用戶是否被占用: username
        // 2.被占用: return 2;
        // 3.未被占用: 用戶數據存入數據表, return 1;

        // 1.2.是否被占用
        UserVO userVO = userMapper.selectByUsername(userRegDTO.getUsername());
        if (userVO != null){//用戶名被占用
            return new JsonResult(StatusCode.USERNAME_ALREADY_EXISTS);
        }

        // 3.未被占用,插入數據表
        User user = new User();
        BeanUtils.copyProperties(userRegDTO, user);
        user.setCreated(new Date());
        userMapper.insertUser(user);
//        return new JsonResult(StatusCode.OPERATION_SUCCESS); //注册成功
        return JsonResult.ok();
    }


    /**
     * 2.登入功能
     * @return 1-登入成功 2-密碼錯誤 3-用戶名錯誤
     * 一定要注意添加 RequestBody 注解
     */
    @PostMapping("login")
    @ApiOperation(value = "登入功能")
    public JsonResult login(@RequestBody UserLoginDTO userLoginDTO, @ApiIgnore HttpSession session){
        log.debug("userLoginDTO = " + userLoginDTO);
        System.out.println();
        /*
            1.根據username查詢數據
            2.查到數據: 比較密碼
            3.未查到數據: 用戶名錯
         */

        //不需要額外定義Mapper接口方法,複用注册功能的接口方法即可
        UserVO userVO = userMapper.selectByUsername(userLoginDTO.getUsername());
        if (userVO == null){//用戶名錯誤
            return new JsonResult(StatusCode.USERNAME_ERROR);
        }
        if (userVO.getPassword().equals(userLoginDTO.getPassword())){
            /*
            登入成功後,將该用戶的相關信息保存到session會話中(服務器内存中)
            1.存：session.setAttribute(String, Object)
            2.取：session.getAttribute(String)
            3.删：session.removeAttribute(String)
            */
            session.setAttribute("user", userVO);
            System.out.println(session.getAttribute("user"));
            return new JsonResult(StatusCode.LOGIN_SUCCESS);//登入成功
        }
        return new JsonResult(StatusCode.PASSWORD_ERROR);//密碼錯誤
    }

    /**
     * 獲取當前用戶登入狀態：session.getAttribute(String)
     */
    @GetMapping("currentUser")
    @ApiOperation(value = "獲取當前用戶功能")
    public JsonResult currentUser(@ApiIgnore HttpSession session){
        UserVO userVO = (UserVO) session.getAttribute("user");
        //輸出日志信息DEBUG
        log.debug("userVO = " + userVO);
//        return new JsonResult(StatusCode.OPERATION_SUCCESS, userVO);
        return JsonResult.ok(userVO);
    }

    /** 退出登入功能 */
    @GetMapping("logout")
    @ApiOperation(value = "退出登入功能")
    public void logout(HttpSession session){
        // 在服務器内存中删除唯一標示
        session.removeAttribute("user");
        // "" + xxx,將獲取的结果轉為字符串輸出
        log.debug(""+session.getAttribute("user"));
    }
}
