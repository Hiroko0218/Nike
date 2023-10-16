package com.yafeng.nike.admin.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.content.pojo.entity.Article;
import com.yafeng.nike.admin.content.pojo.vo.ArticleListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.ArticleStandardVO;
import com.yafeng.nike.admin.content.pojo.vo.search.ArticleSearchVO;
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
     * 查詢文章數據列表
     *
     * @return 文章數據列表
     */
    List<ArticleListItemVO> list();

    /**
     * 查詢文章數據列表，用於讀取數據後存入到elasticsearch中
     *
     * @return 文章數據列表
     */
    List<ArticleSearchVO> listSearchVO();

    /**
     * 根據類別查詢文章列表
     *
     * @param categoryId 文章類別ID
     * @return 文章列表
     */
    List<ArticleListItemVO> listByCategory(Long categoryId);

}
