package cn.tedu.boot01.controller;

import cn.tedu.boot01.mapper.UserMapper;
import cn.tedu.boot01.pojo.dto.UserLoginDTO;
import cn.tedu.boot01.pojo.dto.UserRegDTO;
import cn.tedu.boot01.pojo.entity.User;
import cn.tedu.boot01.pojo.vo.UserVo;
import cn.tedu.boot01.response.JsonResult;
import cn.tedu.boot01.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserMapper mapper;

    @RequestMapping("/reg")
    public JsonResult reg (@RequestBody UserRegDTO userRegDTO){
        //查詢
        UserVo userVo = mapper.selectByUsername(userRegDTO.getUsername());
        if (userVo!=null){
            return new JsonResult(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        //把dto數據裝進entity
        User user = new User();
        BeanUtils.copyProperties(userRegDTO,user);
        user.setCreated(new Date());
        mapper.insert(user);
        return JsonResult.ok();
    }

    @Autowired
    AuthenticationManager manager;

    @RequestMapping("/login")
    public JsonResult login(@RequestBody UserLoginDTO userLoginDTO){
        //        UserVo userVo = mapper.selectByUsername(userLoginDTO.getUsername());
//        if (userVo!=null){
//            if (userVo.getPassword().equals(userLoginDTO.getPassword())){
//                return JsonResult.ok();
//            }else{
//                return new JsonResult(StatusCode.PASSWORD_ERROR);
//            }
//        }
//        return new JsonResult(StatusCode.USERNAME_ERROR);

        /*開啟Security框架的認證流程,會自動調用UserDetailsServiceImpl裡面的方法*/
        Authentication result = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),userLoginDTO.getPassword()
                ));
        //把認證完成之後的结果保存到Security框架的上下文中
        SecurityContextHolder.getContext().setAuthentication(result);
        //當順利執行完上面兩行代碼時說明登入無異常 代表登入成功
        //如果用戶名錯誤或密碼錯誤時,Security框架會抛出對應的異常,
        // 我們需要在全局異常處理的地方處理這兩個異常
        System.out.println("用戶訊息:"+result.getPrincipal());
        return JsonResult.ok(result.getPrincipal());//登入成功
//        return JsonResult.ok();//登入成功
    }

    @RequestMapping("/logout")
    public JsonResult logout(){
        //從Security的上下文中清除數據
        SecurityContextHolder.clearContext();

        return JsonResult.ok();
    }
}
