package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 執行預編譯SQL，實現註冊功能
 */
public class JDBCDemo9 {
    public static void main(String[] args) {
        // 接收用戶輸入
        Scanner scanner = new Scanner(System.in);
        System.out.println("歡迎註冊");
        System.out.println("請輸入用戶名:");
        String username = scanner.nextLine();
        System.out.println("請輸入密碼:");
        String password = scanner.nextLine();
        System.out.println("請輸入暱稱:");
        String nickname = scanner.nextLine();
        System.out.println("請輸入年齡:");
        int age = scanner.nextInt();

        try (Connection connection = DBUtil.getConnection();){
            String sql = "INSERT INTO userinfo(username,password,nickname,age) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, nickname);
            ps.setInt(4, age);
            int i = ps.executeUpdate();
            if (i>0){
                System.out.println("註冊成功");
            }else{
                System.out.println("註冊失敗");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}