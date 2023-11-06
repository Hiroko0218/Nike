package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 在 tedu庫 下創建表 userinfo (id、username、password、nickname、age)
 */
public class JDBCDemo1 {
    public static void main(String[] args) {
        /*
            1.加載驅動
            2.創建數據庫連接對象:DriverManger
            3.創建執行SQL語句的對象:Statement
            4.執行SQL語句(CREATE TABLE ...)
            5.獲得SQL語句執行结果[有必要的話]
            6.關閉連接
         */

        try {
            // 1.加載驅動,Alt+Enter處理異常 try/catch
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.創建數據庫連接對象,Alt+Enter處理異常 Add ...
            Connection connection = DriverManager.getConnection(
                    // 注意更改庫名為:tedu ,此項配置直接到文檔服務器中複製
                    "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                    "root",
                    "root"
            );
            System.out.println("數據庫連接成功!");
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE userinfo( " +
                    " id INT PRIMARY KEY AUTO_INCREMENT, " +
                    " username VARCHAR(30), " +
                    " password VARCHAR(30), " +
                    " nickname VARCHAR(30), " +
                    " age INT " +
                    ")";
            /*
                執行SQL語句的方法:
                DDL語句: execute(sql)  返回值: boolean
                DML語句: executeUpdate(sql)
                DQL語句: executeQuery(sql)
             */
            statement.execute(sql);
            // 關閉連接
            connection.close();
            System.out.println("表創建成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}