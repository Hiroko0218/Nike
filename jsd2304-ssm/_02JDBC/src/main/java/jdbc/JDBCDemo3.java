package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * JDBC執行DML語句
 * 修改密碼功能 UPDATE
 */
public class JDBCDemo3 {
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
            System.out.println("修改密碼");
            System.out.println("請輸入用戶名:");
            String username = scanner.nextLine();
            System.out.println("請輸入新密碼:");
            String password = scanner.nextLine();

            String sql = "UPDATE userinfo SET password='"+password+"' WHERE username='"+username+"'";
            // executeUpdate(sql)返回值:受影響的數據條數(整數 0 或 非0)
            int num = statement.executeUpdate(sql);
            if (num>0){
                System.out.println("密碼修改成功");
            }else {
                System.out.println("密碼修改失敗");
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
