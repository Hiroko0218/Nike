package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查詢每個老師所带班级各多少人?列出老師名字、班级名字、班級人數
 * SELECT t.name,c.name,COUNT(*) number
 * FROM teacher t JOIN class c ON c.teacher_id=t.id
 * JOIN student s ON s.class_id=c.id
 * GROUP BY t.name,c.name
 */
public class JDBCDemo6 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection()){
            // 執行SQL
            Statement statement = connection.createStatement();
            String sql = "SELECT t.name,c.name,COUNT(*) number " +
                    "FROM teacher t JOIN class c ON c.teacher_id=t.id " +
                    "JOIN student s ON s.class_id=c.id " +
                    "GROUP BY t.name,c.name";
            ResultSet rs = statement.executeQuery(sql);
            // 獲取结果
            while (rs.next()){
                String tname = rs.getString("t.name");
                String cname = rs.getString("c.name");
                int number = rs.getInt("number");
                System.out.println(tname + cname + number);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}