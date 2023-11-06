package V20.src.main.java.com.birdboot.controller;

import V20.src.main.java.com.birdboot.annotation.Controller;
import V20.src.main.java.com.birdboot.annotation.RequestMapping;
import V20.src.main.java.com.birdboot.http.HttpServletRequest;
import V20.src.main.java.com.birdboot.http.HttpServletResponse;
import V20.src.main.java.com.birdboot.util.DBUtil;


import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用於處理與用戶數據相關的業務
 */
@Controller
public class UserController {
    //表示保存所有用戶信息的資料夾users
    private static File userDir;

    static {
        userDir = new File("./users");
        if (!userDir.exists()){
            userDir.mkdirs();
        }
    }

    /**
     * 註冊功能
     * @param request
     * @param response
     */
    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("開始處理用戶註冊!!!");

        request.getHeader("Content-Length");

        //1獲取表單據數
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username + "," + password + "," + nickname + "," + ageStr);
        //必要的验证
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() ||
                ageStr == null || ageStr.isEmpty() ||
                !ageStr.matches("[0-9]+")
        ) {
            response.sendRedirect("/reg_info_error.html");
            return;
        }

        int age = Integer.parseInt(ageStr);

        /*
            註冊邏輯 - 數據庫
         */
        try (Connection connection = DBUtil.getConnection()) {
            /*
                校驗用戶名是否被占用
             */
            String checkSql = "SELECT id FROM userinfo WHERE username=?";
            PreparedStatement ps1 = connection.prepareStatement(checkSql);
            ps1.setString(1, username);
            ResultSet resultSet = ps1.executeQuery();
            if (resultSet.next()) { //用戶名被占用
                response.sendRedirect("/have_user.html");
            } else { //未被占用
                /*
                    執行註冊用戶流程
                 */
                String insSql = "INSERT INTO userinfo(username,password,nickname,age) VALUES(?,?,?,?)";
                PreparedStatement ps2 = connection.prepareStatement(insSql);
                ps2.setString(1, username);
                ps2.setString(2, password);
                ps2.setString(3, nickname);
                ps2.setInt(4, age);
                int i = ps2.executeUpdate();
                if (i > 0) {
                    response.sendRedirect("/reg_success.html");
                } else {
                    response.sendRedirect("/reg_info_error.html");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 登入功能
     * @param request
     * @param response
     */
    @RequestMapping("/loginUser")
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

        /*
            登入功能判斷 - 數據庫版本
            1.userinfo表中查詢數據(用戶名和密碼)
            2.查詢结果判斷
              2.1 正確：登入成功, 到 login_success.html
              2.2 失敗：用戶名或密碼錯誤, 到 login_fail.html
         */
        try (Connection connection = DBUtil.getConnection();) {
            String sql = "SELECT username,password,nickname " +
                    "FROM userinfo " +
                    "WHERE username=? AND password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            // 结果判斷
            if (resultSet.next()) {
                response.sendRedirect("/login_success.html");
            } else {
                response.sendRedirect("/login_fail.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 修改密碼
     * @param request
     * @param response
     */
    @RequestMapping("/updatePWD")
    public void updatePWD(HttpServletRequest request,HttpServletResponse response){
        /*
            1.接收數據(用戶名、原密碼、新密碼)
            2.連接數據庫,完成修改密碼功能
            3.校驗原密碼是否正確
              正確: 執行修改密碼功能：pwd_success.html  <h1>修改密碼成功</h1>
              錯誤: 修改密碼失敗：pwd_fail.html     <h1>原密碼錯誤</h1>
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");

        // 連接數據庫
        try (Connection connection = DBUtil.getConnection();){
            // 校驗原密碼
            String pwdSql = "SELECT id FROM userinfo " +
                    "WHERE username=? AND password=?";
            PreparedStatement ps1 = connection.prepareStatement(pwdSql);
            ps1.setString(1, username);
            ps1.setString(2, password);
            ResultSet resultSet = ps1.executeQuery();
            if (resultSet.next()){
                // 原密碼正確,開始修改密碼
                String updSql = "UPDATE userinfo SET password=? " +
                        "WHERE username=?";
                PreparedStatement ps2 = connection.prepareStatement(updSql);
                ps2.setString(1, newPassword);
                ps2.setString(2, username);
                int i = ps2.executeUpdate();
                if (i>0){
                    // 修改密碼成功頁面
                    response.sendRedirect("/pwd_success.html");
                }else{
                    // 修改密碼失敗頁面
                    response.sendRedirect("/pwd_fail.html");
                }
            }else{
                // 修改密碼失敗頁面
                response.sendRedirect("/pwd_fail.html");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
