package cn.tedu._053mvcweibo.mapper;

import cn.tedu._053mvcweibo.pojo.entity.Weibo;
import cn.tedu._053mvcweibo.pojo.vo.WeiboDetailVO;
import cn.tedu._053mvcweibo.pojo.vo.WeiboIndexVO;

import java.util.List;

public interface WeiboMapper {
    /** 發布微博功能 */
    int insert(Weibo weibo);

    /** 微博首頁列表展示 */
    List<WeiboIndexVO> selectIndex();

    /** 微博詳情頁展示功能 */
    WeiboDetailVO selectById(int id);


}
