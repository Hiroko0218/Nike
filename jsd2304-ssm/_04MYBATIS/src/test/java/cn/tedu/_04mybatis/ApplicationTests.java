package cn.tedu._04mybatis;

import cn.tedu._04mybatis.mapper.CommentMapper;
import cn.tedu._04mybatis.mapper.UserMapper;
import cn.tedu._04mybatis.mapper.WeiboMapper;
import cn.tedu._04mybatis.pojo.Comment;
import cn.tedu._04mybatis.pojo.User;
import cn.tedu._04mybatis.pojo.Weibo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;


@SpringBootTest
class ApplicationTests {

    /**
     * 1.每個測試方法上需要添加 @Test注解
     * 2.測試方法要求無参無返回值
     */
    @Test
    void contextLoads() {
    }

    //自動裝配
    /**
     * Spring會協同 MyBatis為該接口 UserMapper創建唯一的實現類,
     * 並根據 @Insert()等相關注解定義數據訪問邏輯
     */
    @Autowired
    private UserMapper userMapper;

    /*用戶表中插入數據*/
    @Test
    void insertTest(){
        User user = new User();
        user.setUsername("Tony");
        user.setPassword("123456");
        user.setNickname("tony");
        user.setCreated(new Date());
        // 調用方法
        System.out.println(userMapper.insertUser(user));
    }

    //自動裝配
    @Autowired
    private WeiboMapper weiboMapper;

    /* 微博表中插入數據 */
    @Test
    void insertWeiboTest(){
        Weibo weibo = new Weibo();
        weibo.setContent("呵呵呵呵");
        weibo.setCreated(new Date());
        weibo.setUserId(1);
        weiboMapper.interweibo(weibo);
    }

    /* 根据微博id查詢指定的微博信息 */
    @Test
    void selectWeiboByIdTest (){
        System.out.println(weiboMapper.selectWeiboById(2));
    }

    /* 查詢所有微博信息 */
    @Test
    void selectWeiboIndexTest (){
        System.out.println(weiboMapper.selectWeiboIndex());
    }

    /* 刪除指定id的微博*/
    @Test
    void deleteWeiboByIdTest(){
        System.out.println(weiboMapper.deleteWeiboById(4));
    }

    /* 修改指定id的微博信息*/
    @Test
    void updateWeiboByIdTest(){
        Weibo weibo = new Weibo();
        weibo.setContent("哈哈哈");
        weibo.setCreated(new Date());
        weibo.setUserId(2);
        weibo.setId(1);
        weiboMapper.updateWeiboById(weibo);
    }

    //自動裝配
    @Autowired
    private CommentMapper commentMapper;

    /* 在評論表中插入1條數據 */
    @Test
    void InsertCommentTest(){
        Comment comment =new Comment();
        comment.setContent("呵呵呵呵呵~");
        comment.setCreated(new Date());
        comment.setUserId(1);
        comment.setWeiboId(1);
        commentMapper.insertComment(comment);
    }

    /* 修改評論 */
    @Test
    void UpdateCommentTest(){
        Comment comment =new Comment();
        comment.setId(2);
        comment.setContent("呵呵呵");
        comment.setCreated(new Date());
        comment.setUserId(1);
        comment.setWeiboId(1);
        commentMapper.updateComment(comment);
    }

    /* 動態刪除微博訊息-數組方式 */
    @Test
    void deleteComment1Test(){
        Integer[] ids ={1,3,5};
        System.out.println(commentMapper.deleteComment1(ids));
    }

    /* 動態刪除微博訊息-List方式 */
    @Test
    void deleteComment2Test(){
      ArrayList<Integer> ids =new ArrayList<>();
      ids.add(2);
      System.out.println(commentMapper.deleteComment2(ids));
    }

    /* 動態修改評論數據 */
    @Test
    void updateComment1Test(){
        Comment comment =new Comment();
        comment.setContent("嘿嘿嘿嘿");
        comment.setCreated(new Date());
        comment.setId(4);
        commentMapper.updateComment1(comment);
    }

    /* 微博首頁列展示:微博id,微博內容,用戶暱稱 */
    @Test
    void SelectIndexTest(){
        System.out.println(weiboMapper.selectIndex());
    }

    @Test
    void SelectCommentTest(){
        System.out.println(commentMapper.selectComment(200));
    }

    @Test
    void SelectMapByIdTest(){
        System.out.println(weiboMapper.selectMapById(200));
    }

    @Test
    void SelectMapByUserIdTest(){
        System.out.println(weiboMapper.selectMapByUserId(100));
    }

}
