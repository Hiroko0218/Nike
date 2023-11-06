package cn.tedu.baking.mapper;

import cn.tedu.baking.pojo.entity.Content;
import cn.tedu.baking.pojo.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {
    int insert(Content content);

    List<ContentManagementVO> selectByType(Integer type, Long id);

    ContentUpdateVO selectByIdForUpdate(Long id);

    int update(Content content);

    int deleteById(Long id);

    List<ContentIndexVO> selectByTypeAndCategoryId(Integer type, Long categoryId);

    ContentDetailVO selectByIdForDetail(Long id);

    List<ContentSimpleVO> selectByUserId(Long userId);

    List<ContentSimpleVO> selectHot();

    int updateViewCountById(Long id);

    List<ContentIndexVO> selectListByType(Integer type);

    List<ContentIndexVO> selectByWd(String wd);

    int updateCommentCountById(Long contentId);

    List<ContentAdminVO> selectByTypeForAdmin(Integer type);
}
