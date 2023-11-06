package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查看1年级1班共有多少學生
 * SELECT c.name,COUNT(*) number
 * FROM class c JOIN student s
 * ON s.class_id=c.id
 * WHERE c.name='1年級1班'
 */
public class Test3 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection();){
            Statement statement = connection.createStatement();
            String sql = "SELECT c.name,COUNT(*) number " +
                    "FROM class c JOIN student s " +
                    "ON s.class_id=c.id " +
                    "WHERE c.name='1年級1班'";
            ResultSet resultSet = statement.executeQuery(sql);
            // 獲取結果
            while (resultSet.next()){
                String cname = resultSet.getString("c.name");
                int number = resultSet.getInt("number");
                System.out.println(cname + "人數:" + number);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
