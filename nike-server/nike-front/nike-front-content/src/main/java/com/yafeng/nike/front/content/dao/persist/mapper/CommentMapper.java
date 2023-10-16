package com.yafeng.nike.front.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.front.content.pojo.entity.Comment;
import com.yafeng.nike.front.content.pojo.vo.CommentListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據文章查詢評論列表
     *
     * @param articleId 文章ID
     * @return 評論列表
     */
    List<CommentListItemVO> listByArticle(Long articleId);

}
