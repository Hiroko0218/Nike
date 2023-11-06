package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 執行 DML: DELETE語句
 * 删除指定用戶: 用戶輸入用戶名,將該用戶删除(userinfo表)
 * DELETE FROM userinfo WHERE username='?'
 */
public class JDBCDemo4 {
    public static void main(String[] args) {
        //try(){} 自動關閉特性
        try (Connection connection = DBUtil.getConnection()){
            // 快捷键: connection.createStatement().var + Enter
            Statement statement = connection.createStatement();

            // 接收用戶輸入
            Scanner scanner = new Scanner(System.in);
            System.out.println("請輸入用戶名:");
            String username = scanner.nextLine();

            //執行SQL語句
            String sql = "DELETE FROM userinfo WHERE username='"+username+"'";
            // executeUpdate(sql)返回值:受影響的數據條數(整數 0 或 非0)
            int num = statement.executeUpdate(sql);
            if (num>0){
                System.out.println("刪除用戶成功");
            }else {
                System.out.println("刪除用戶失敗");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
