package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 登入驗證與SQL注入攻擊
 * 用戶輸入用戶名、密碼,數據庫校驗
 * 正確:登入成功,歡迎你:暱稱
 * 錯誤:用戶名或密碼錯誤
 * SELECT id,username,password,nickname FROM userinfo
 * WHERE username='?' AND password='?'
 */
public class JDBCDemo7 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection();){
            // 接收用戶輸入
            Scanner scanner = new Scanner(System.in);
            System.out.println("登入功能");
            System.out.println("請輸入用戶名:");
            String username = scanner.nextLine();
            System.out.println("請輸入密碼:");
            String password = scanner.nextLine();

            // 執行SQL語句
            Statement statement = connection.createStatement();
            String sql = "SELECT id,username,password,nickname " +
                    "FROM userinfo " +
                    "WHERE username='"+username+"' " +
                    "AND password='"+password+"'";
            ResultSet rs = statement.executeQuery(sql);

            // 判断结果
            if (rs.next()){
                String nickname = rs.getString("nickname");
                System.out.println("登入成功,歡迎你:" + nickname);
            }else{
                System.out.println("用戶名或密碼錯誤");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}