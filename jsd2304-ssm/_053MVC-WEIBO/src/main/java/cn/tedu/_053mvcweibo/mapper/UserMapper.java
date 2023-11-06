package cn.tedu._053mvcweibo.mapper;

import cn.tedu._053mvcweibo.pojo.entity.User;
import cn.tedu._053mvcweibo.pojo.vo.UserVO;

/**
 * 此接口未添加 @Mapper注解,是因為在config.MyBatisConfig中使用 @MapperScan注解 設置了自動掃描此包路徑
 */
public interface UserMapper {
    /** 注册功能-查詢 */
    UserVO selectByUsername(String username);

    /** 注册功能-插入數據 */
    int insertUser(User user);
}
