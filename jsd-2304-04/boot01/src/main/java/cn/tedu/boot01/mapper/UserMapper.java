package cn.tedu.boot01.mapper;

import cn.tedu.boot01.pojo.entity.User;
import cn.tedu.boot01.pojo.vo.UserVo;
import org.springframework.stereotype.Repository;

@Repository //添加此註解後 自動裝配時不需要添加required=false
public interface UserMapper {
    UserVo selectByUsername(String username);
    int insert(User user);
}
