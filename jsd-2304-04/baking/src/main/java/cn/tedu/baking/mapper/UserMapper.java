package cn.tedu.baking.mapper;


import cn.tedu.baking.pojo.entity.User;
import cn.tedu.baking.pojo.vo.UserAdminVO;
import cn.tedu.baking.pojo.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {
    UserVO selectByUserName(String userName);

    int insert(User user);

    int update(User user);

    String selectImgUrlById(Long id);

    List<UserAdminVO> select();

    int deleteById(Long id);
}
