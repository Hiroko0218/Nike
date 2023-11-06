package cn.tedu.boot01.controller;

import cn.tedu.boot01.mapper.WeiboMapper;
import cn.tedu.boot01.pojo.dto.WeiboDTO;
import cn.tedu.boot01.pojo.entity.Weibo;
import cn.tedu.boot01.pojo.vo.WeiboVO;
import cn.tedu.boot01.response.JsonResult;
import cn.tedu.boot01.response.StatusCode;
import cn.tedu.boot01.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weibo/")
public class WeiboController {
    @Autowired
    WeiboMapper weiboMapper;

    @GetMapping("select")
    public JsonResult select(){
        List<WeiboVO> list =weiboMapper.select();
        return new JsonResult(list);
    }

    @PostMapping("insert")
    public JsonResult insert(@RequestBody WeiboDTO weiboDTO,
                             @AuthenticationPrincipal CustomUserDetails userDetails){
        //判斷如果沒有登入或登入超時通知客戶端
        if (userDetails==null){
            return new JsonResult(StatusCode.NOT_LOGIN);
        }

        Weibo weibo = new Weibo();
        BeanUtils.copyProperties(weiboDTO,weibo);
        weibo.setCreated(new Date());

        weiboMapper.insert(weibo);
        return JsonResult.ok();
    }
}
