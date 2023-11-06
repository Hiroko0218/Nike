package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 執行DML語句: executeUpdate(sql)方法
 * 註冊功能: 在 userinfo表中插入一條表紀錄（INSERT語句）
 */

public class JDBCDemo2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    // 注意更改庫名為:tedu ,此項配置直接到文檔服務器中複製
                    "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                    "root",
                    "root"
            );
            // 快捷键: connection.createStatement().var + Enter
            Statement statement = connection.createStatement();

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
            String sql = "INSERT INTO userinfo (username,password,nickname,age) VALUES('"+username+"','"+password+"','"+nickname+"',"+age+")";
            // executeUpdate(sql)返回值:受影响的数据条数(整数 0 或 非0)
            int num = statement.executeUpdate(sql);
            if (num>0){
                System.out.println("註冊成功");
            }else {
                System.out.println("註冊失敗");
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
