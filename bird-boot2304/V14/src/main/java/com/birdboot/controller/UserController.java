package com.birdboot.controller;

import com.birdboot.entity.User;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 用於處理與用戶數據相關的業務
 */
public class UserController {
    //表示保存所有用戶信息的資料夾users
    private static File userDir;

    static {
        userDir = new File("./users");
        if (!userDir.exists()){
            userDir.mkdirs();
        }
    }
    public void reg(HttpServletRequest request, HttpServletResponse response){
        System.out.println("開始處理用戶註冊!!!");

        request.getHeader("Content-Length");

        //1獲取表單據數
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username+","+password+","+nickname+","+ageStr);
        //必要的验证
        if(username==null||username.isEmpty()||
                password==null||password.isEmpty()||
                nickname==null||nickname.isEmpty()||
                ageStr==null||ageStr.isEmpty()||
                !ageStr.matches("[0-9]+")
        ){
            response.sendRedirect("/reg_info_error.html");
            return;
        }

        int age = Integer.parseInt(ageStr);
        User user = new User(username,password,nickname,age);
        File userFile = new File(userDir,username+".obj");

        //验证是否为重复用户
        if(userFile.exists()){//如果该文件存在了，说明是重复用户
            response.sendRedirect("/have_user.html");
            return;
        }


        try (
                FileOutputStream fos
                        = new FileOutputStream(userFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(user);
            //该用户信息保存后，回复浏览器注册成功
            response.sendRedirect("/reg_success.html");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
