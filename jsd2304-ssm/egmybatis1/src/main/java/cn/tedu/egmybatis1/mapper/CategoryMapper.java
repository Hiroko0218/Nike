package cn.tedu.egmybatis1.mapper;

import cn.tedu.egmybatis1.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("INSERT INTO t_category(id,name,intro,created) VALUES(NULL,#{name},#{intro},#{created})")
    int insertCategory(Category category);

    @Select("SELECT id,name,intro,created FROM t_category")
    List<Category> selectAll();

    @Select("SELECT id,name,intro,created FROM t_category WHERE id=#{id}")
    Category selectById(int id);

    @Update("UPDATE t_category SET name=#{name},intro=#{intro},created=#{created} WHERE id=#{id}")
    int updateCategoryById(Category category);

    @Delete("DELETE FROM t_category WHERE id=#{id}")
    int deleteById(int id);
}
