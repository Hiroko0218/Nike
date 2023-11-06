package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 查看王克晶老師所帶的班級的信息，列出老師的名字、老師性别、班级名稱、所在樓層
 * SELECT t.name,t.gender,c.name,c.floor
 * FROM teacher t JOIN class c
 * ON c.teacher_id=t.id
 * WHERE t.name='王克晶'
 */
public class Teat2 {
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getConnection()){
            // 創建執行對象
            Statement statement = connection.createStatement();
            String sql = "SELECT t.name,t.gender,c.name,c.floor " +
                    "FROM teacher t JOIN class c " +
                    "ON c.teacher_id=t.id " +
                    "WHERE t.name='王克晶'";
            ResultSet rs = statement.executeQuery(sql);

            // 獲取查詢結果
            while (rs.next()){
                String tname = rs.getString("t.name");
                String tgender = rs.getString("t.gender");
                String cname = rs.getString("c.name");
                int cfloor = rs.getInt("c.floor");
                System.out.println(tname+","+tgender+","+cname+","+cfloor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
