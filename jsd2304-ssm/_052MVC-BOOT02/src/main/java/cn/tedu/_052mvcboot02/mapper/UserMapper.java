package cn.tedu._052mvcboot02.mapper;

import cn.tedu._052mvcboot02.pojo.dto.UserUpdateDTO;
import cn.tedu._052mvcboot02.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//@Mapper
public interface UserMapper {
    /** 添加用戶接口 */
    int insertUser(User user);

    /** 查詢用戶列表接口 */
    List<User> selectUserList();

    /** 删除用戶接口 */
    int deleteUser(int id);

    /** 修改用戶接口 */
    int updateUser(User user);

    /**根據 id查詢用戶信息*/
    User selectById(int id);

}
