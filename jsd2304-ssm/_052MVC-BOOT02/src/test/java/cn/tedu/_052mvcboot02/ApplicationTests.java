package cn.tedu._052mvcboot02;

import cn.tedu._052mvcboot02.mapper.UserMapper;
import cn.tedu._052mvcboot02.pojo.dto.UserUpdateDTO;
import cn.tedu._052mvcboot02.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    /** 添加用戶測試 */
    @Test
    void InsertUserTest(){
        User user =new User();
        user.setUsername("Too");
        user.setPassword("123456");
        user.setNickname("too");
        user.setCreated(new Date());
        userMapper.insertUser(user);
    }

    /** 用戶列表測試 */
    @Test
    void SelectUserListTest(){
        System.out.println(userMapper.selectUserList());
    }

    /** 删除用戶測試 */
    @Test
    void DeleteUserTest(){
        System.out.println(userMapper.deleteUser(104));
    }

    /** 更新用戶信息測試 */
    @Test
    void UpdateUserTest(){
        User user = new User();
        user.setId(101);
        user.setUsername("Too");
        System.out.println(userMapper.updateUser(user));
    }

    /**根據 id查詢用戶信息*/
    @Test
    void SelectByIdTest(){
        System.out.println(userMapper.selectById(100));
    }
}
