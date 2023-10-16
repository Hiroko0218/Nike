package com.yafeng.nike.front.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.front.content.pojo.entity.Article;
import com.yafeng.nike.front.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.front.content.pojo.vo.ArticleStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理文章數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根據ID查詢文章
     *
     * @param id 文章ID
     * @return 匹配的文章，如果沒有匹配的數據，則返回null
     */
    ArticleStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的文章列表
     *
     * @return 文章列表
     */
    List<ArticleListItemVO> listByRecommend();

    /**
     * 根據文章類別查詢其文章列表
     *
     * @param categoryId 文章類別的ID
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategory(Long categoryId);

}
