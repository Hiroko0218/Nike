package cn.tedu._053mvcweibo;

import cn.tedu._053mvcweibo.mapper.WeiboMapper;
import cn.tedu._053mvcweibo.pojo.entity.Weibo;
import cn.tedu._053mvcweibo.pojo.vo.WeiboIndexVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class WeiboTests {

    @Autowired(required = false)
    private WeiboMapper weiboMapper;

    /** 發布微博功能測試 */
    @Test
    void insertTest(){
        Weibo weibo = new Weibo();
        weibo.setContent("今天出去玩，哈哈哈~~");
        weibo.setCreated(new Date());
        weibo.setUserId(101);
        System.out.println(weiboMapper.insert(weibo));
    }

}
