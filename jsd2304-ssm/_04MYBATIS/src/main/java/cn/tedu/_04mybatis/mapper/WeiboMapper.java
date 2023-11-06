package cn.tedu._04mybatis.mapper;

import cn.tedu._04mybatis.pojo.Weibo;
import cn.tedu._04mybatis.pojo.WeiboIndexVO;
import cn.tedu._04mybatis.pojo.WeiboMapVO1;
import cn.tedu._04mybatis.pojo.WeiboMapVO2;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis注解：告知底層為此接口創建實現類並定義數據訪問邏輯
 */
@Mapper
public interface WeiboMapper {
    /* 微博表中插入數據*/
    /**
     * @Insert:
     * 根據註解中的 SQL轉為JDBC代碼操作數據庫，
     * 並返回接口方法指定的返回值類型
     */
//    @Insert("INSERT INTO weibo VALUES (NULL,#{content},#{created},#{userId})")
    int interweibo (Weibo weibo);

    /* 根據微博id查詢指定的微博信息*/
//    @Select("SELECT content,created,user_id userId FROM weibo WHERE id=#{id}")
    Weibo selectWeiboById (int id);

    /* 查詢所有微博信息*/
//    @Select("SELECT content,created,user_id userId FROM weibo")
    List<Weibo> selectWeiboIndex ();

    /* 刪除指定id的微博*/
//    @Delete("DELETE FROM weibo WHERE id=#{id}")
    int deleteWeiboById (int id);

    /* 修改指定id的微博信息*/
//    @Update("UPDATE weibo SET content=#{content},created=#{created},user_id=#{userId} WHERE id=#{id}" )
    int updateWeiboById(Weibo weibo);

    /* 微博首頁列展示:微博id,微博內容,用戶暱稱 */
    List<WeiboIndexVO> selectIndex();

    /* resultMap 單表查詢示例:根據微博的id查詢該條微博的信息，只顯示：微博編號id、内容content、用戶編號user_id */
    WeiboMapVO1 selectMapById(Integer wid);

    /**
     * resultMap一對多查詢示例
     * 查詢指定的用戶發佈的所有微博信息，要求將微博信息放到一個集合中
     * 要求顯示：用戶的 id、用戶暱稱、List<Weibo>集合
     */
    WeiboMapVO2 selectMapByUserId(Integer uid);
}