package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 輸入老師的姓名，查詢該老師的 姓名，年齡，職稱，工資
 * SELECT name,age,title,salary FROM teacher WHERE name='?'
 */
public class Teat1 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection()){
            // 接收用戶輸入
            Scanner scanner = new Scanner(System.in);
            System.out.println("請輸入老師姓名:");
            String name = scanner.nextLine();

            // 創建執行對象
            Statement statement = connection.createStatement();
            String sql = "SELECT name,age,title,salary FROM teacher WHERE name ='"+name+"'";
            ResultSet rs = statement.executeQuery(sql);

            // 獲取查詢結果
            while (rs.next()){
                // 根據索引值獲取數據
                int age = rs.getInt("age");
                String title = rs.getString("title");
                int salary = rs.getInt("salary");
                System.out.println(name + "," + age + "," + title +","+salary);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
