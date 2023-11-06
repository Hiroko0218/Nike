package cn.tedu._04mybatis.mapper;

import cn.tedu._04mybatis.pojo.Comment;
import cn.tedu._04mybatis.pojo.CommentMapVO;
import cn.tedu._04mybatis.pojo.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface CommentMapper {
    /* 在評論表中插入1條數據 */
//    @Insert("INSERT INTO comment VALUES (NULL,#{content},#{created},#{userId},#{weiboId})")
     int insertComment(Comment comment);

    /* 修改評論 */
//    @Update("UPDATE comment SET content=#{content},created=#{created},user_id=#{userId},weibo_id=#{weiboId} WHERE id=#{id}")
    int updateComment(Comment comment);

    /* 動態刪除微博訊息-數組方式 */
    int deleteComment1(Integer[] ids);

    /* 動態刪除微博訊息-List方式 */
    int deleteComment2(List<Integer> ids);

    /* 動態修改評論數據 */
    int updateComment1 (Comment comment);

    /* 获取指定微博的所有评论 */
    List<CommentVO> selectComment(int wid);

    /* 獲取指定微博的所有評論-ResultMap */
    CommentMapVO selectCommentMap(int wid);
}
