package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 預編譯 SQL，防止SQL注入攻擊
 * 1. 先將 SQL語句發送给數據庫，讓數據庫生成執行計畫理解 SQL執行意圖(?占位)
 * 2. 創建預編譯 SQL语句执行对象(prepareStatement)
 * 3. 設置 SQL語句中的?的值,並執行SQL語句(setString(index,value))
 */
public class JDBCDemo8 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection();){
            // 接收用戶輸入
            Scanner scanner = new Scanner(System.in);
            System.out.println("登入功能");
            System.out.println("請輸入用戶名:");
            String username = scanner.nextLine();
            System.out.println("請輸入密碼:");
            String password = scanner.nextLine();

            // 執行預編譯SQL
            String sql = "SELECT id,username,password,nickname FROM userinfo " +
                    "WHERE username=? AND password=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            /*
                設置 ? 對應的值
                ? 是整型,ps.setInt(...)
                ? 是字符串,ps.setString(...)
             */
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            // 判断结果
            if (rs.next()){
                String nickname = rs.getString("nickname");
                System.out.println("登入成功,歡迎:" + nickname);
            }else{
                System.out.println("用戶名或密碼錯誤");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
