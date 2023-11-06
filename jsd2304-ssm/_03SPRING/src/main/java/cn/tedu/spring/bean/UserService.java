package cn.tedu.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// @Service注解：將UserService標示為Spring中的組件，交由Spring管理
@Service
public class UserService {
    // 普通屬性值注入
    @Value("註冊業務")
    private String serName;
    // 對象類型數據注入
    @Autowired
    private UserDao userDao;

    @Override
    public String toString() {
        return "UserService{" +
                "serName='" + serName + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
