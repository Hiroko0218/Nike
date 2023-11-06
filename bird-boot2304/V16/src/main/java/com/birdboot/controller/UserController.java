package com.birdboot.controller;

import com.birdboot.entity.User;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.*;

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

        //驗證是否為重復用戶
        if(userFile.exists()){//如果該文件存在了，說明是重複用戶
            response.sendRedirect("/have_user.html");
            return;
        }


        try (
                FileOutputStream fos
                        = new FileOutputStream(userFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(user);
            //該用戶信息保存後，回復瀏覽器註冊成功
            response.sendRedirect("/reg_success.html");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("開始處理用戶登入!!!!");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "," + password);
        //添加必要的,用戶驗證名或密碼没輸入則跳轉login_info_error.html
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            response.sendRedirect("/login_info_error.html");

            return;
        }

        //創建File對象使用該用户名.obj作為文件名定位users資料夾下對應文件
        File userFile = new File(userDir, username + ".obj");

        /*
            分支:
            如果該文件存在則說明用戶存在,需要反序列化讀取文件中的User對象,該對象
            記錄著該用戶曾經的註冊信息,此時讀取後比較密碼
                分支:
                密碼一致,則登入成功,跳轉:login_success.html
                密碼不一致,則登入失敗,跳轉:login_fail.html

            如果該文件不存在說明没有這個註冊用戶,這屬於登入失敗,跳轉:login_fail.html
         */
        if (userFile.exists()) {//文件存在說明有該用戶
            //讀取該文件，反序列化註冊時的信息
            try (
                    FileInputStream fis = new FileInputStream(userFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();
                //若密碼一致，則表示登入成功
                if (user.getPassword().equals(password)) {
                    response.sendRedirect("/login_success.html");
                } else {
                    response.sendRedirect("/login_fail.html");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            //没有該用戶,登入失敗
            response.sendRedirect("/login_fail.html");
        }
    }
}
