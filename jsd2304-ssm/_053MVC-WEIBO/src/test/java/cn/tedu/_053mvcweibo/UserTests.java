package cn.tedu._053mvcweibo;

import cn.tedu._053mvcweibo.mapper.UserMapper;
import cn.tedu._053mvcweibo.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    /** 注册功能-查詢是否被占用 */
    @Test
    void selectByUsernameTest(){
        System.out.println(userMapper.selectByUsername("Lucy"));
    }

    /** 注册功能：插入user表 */
    @Test
    void insertUserTest(){
        User user = new User();
        user.setUsername("Shasha");
        user.setPassword("123456");
        user.setNickname("shasha");
        user.setCreated(new Date());
        System.out.println(userMapper.insertUser(user));
    }
}
