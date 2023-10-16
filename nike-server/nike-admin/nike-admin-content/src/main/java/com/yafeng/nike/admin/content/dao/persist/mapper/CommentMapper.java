package com.yafeng.nike.admin.content.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.content.pojo.entity.Comment;
import com.yafeng.nike.admin.content.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CommentStandardVO;
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
     * 根據id查詢評論數據詳情
     *
     * @param id 評論ID
     * @return 匹配的評論數據詳情，如果沒有匹配的數據，則返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查詢評論數據列表
     *
     * @param resourceType 資源類型
     * @return 評論數據列表
     */
    List<CommentListItemVO> listByResourceType(Integer resourceType);

}
