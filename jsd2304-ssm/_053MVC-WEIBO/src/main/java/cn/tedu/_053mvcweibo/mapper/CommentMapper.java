package cn.tedu._053mvcweibo.mapper;

import cn.tedu._053mvcweibo.pojo.entity.Comment;
import cn.tedu._053mvcweibo.pojo.vo.CommentVO;

import java.util.List;

public interface CommentMapper {

    /** 發布評論 */
    int insert(Comment comment);

    /** 獲取指定微博的所有評論 */
    List<CommentVO> selectByWeiboId(int id);
}
