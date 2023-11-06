package cn.tedu.boot01.mapper;

import cn.tedu.boot01.pojo.entity.Weibo;
import cn.tedu.boot01.pojo.vo.WeiboVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeiboMapper {
    List<WeiboVO> select();

    void insert(Weibo weibo);
}
