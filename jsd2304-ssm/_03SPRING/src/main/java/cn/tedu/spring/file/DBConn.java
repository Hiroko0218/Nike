package cn.tedu.spring.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 引入外部屬性文件註解
 */
@PropertySource("classpath:database.properties")
@Component
public class DBConn {
    @Value("${jdbc.url}")
    private String dbUrl;
    @Value("${jdbc.user}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.max.actives}")
    private Integer maxActive;
    @Value("${jdbc.initial.actives}")
    private Integer initActive;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getInitActive() {
        return initActive;
    }

    public void setInitActive(Integer initActive) {
        this.initActive = initActive;
    }

    @Override
    public String toString() {
        return "DBConn{" +
                "dbUrl='" + dbUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxActive=" + maxActive +
                ", initActive=" + initActive +
                '}';
    }
}
