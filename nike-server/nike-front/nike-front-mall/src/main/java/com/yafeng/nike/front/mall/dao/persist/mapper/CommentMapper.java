package com.yafeng.nike.front.mall.dao.persist.mapper;

import com.yafeng.nike.front.mall.pojo.entity.Comment;
import com.yafeng.nike.front.mall.pojo.vo.CommentListItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理評論評論數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根據商品查詢評論數據列表
     *
     * @param goodsId 商品ID
     * @return 評論數據列表
     */
    List<CommentListItemVO> list(Long goodsId);

}
