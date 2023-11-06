package cn.tedu.spring.file;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class TestDBConn {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context =new AnnotationConfigApplicationContext("cn.tedu.spring.file");
        DBConn dbConn = context.getBean(DBConn.class);
        System.out.println("dbConn = " + dbConn);

        /**
         * 創建數據庫連接池對象
         * 1. 添加依賴,並刷新 maven
         * 2. 初始化連接池
         * 3. 設置連接池信息
         * 4. 獲取連接對象
         */
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dbConn.getDbUrl());
        ds.setUsername(dbConn.getUsername());
        ds.setPassword(dbConn.getPassword());
        ds.setMaxActive(dbConn.getMaxActive());
        ds.setInitialSize(dbConn.getInitActive());

        DruidPooledConnection connection = ds.getConnection();
        System.out.println("connection = " + connection);
    }
}
