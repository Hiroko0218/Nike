package cn.tedu._04mybatis.mapper;

import cn.tedu._04mybatis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Mapper注解: MyBatis注解，告訴底層為該接口創建實現類，
 * 在實現類中定義數據訪問的邏輯
 */
@Mapper
public interface UserMapper {
    /**
     * 接口方法:MyBatis會將接口方法中 @Insert()里面的SQL語句轉為
     * 底層的 JDBC代碼，操作數據表
     * @param user
     */
//    @Insert("INSERT INTO user VALUES (NULL,#{username},#{password},#{nickname},#{created})")
    int insertUser(User user);
}