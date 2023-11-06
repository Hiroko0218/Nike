package cn.tedu._053mvcweibo.controller;

import cn.tedu._053mvcweibo.common.response.JsonResult;
import cn.tedu._053mvcweibo.common.response.StatusCode;
import cn.tedu._053mvcweibo.mapper.WeiboMapper;
import cn.tedu._053mvcweibo.pojo.dto.WeiboDTO;
import cn.tedu._053mvcweibo.pojo.entity.Weibo;
import cn.tedu._053mvcweibo.pojo.vo.UserVO;
import cn.tedu._053mvcweibo.pojo.vo.WeiboDetailVO;
import cn.tedu._053mvcweibo.pojo.vo.WeiboIndexVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 校驗方法参數
 */
@Validated
@RestController
@Api(tags = "02.微博模塊")
@RequestMapping("/v1/weibo/")
public class WeiboController {

    @Autowired(required = false)
    private WeiboMapper weiboMapper;

    /**
     * 發布微博功能
     */
    @PostMapping("insert")
    @ApiOperation(value = "發布微博功能")
    public JsonResult insert(@RequestBody WeiboDTO weiboDTO, @ApiIgnore HttpSession session){
        /*
            1.確認登入狀態
            2.登入狀態
              2.2 未登入: 直接返回2
              2.1 登入: 發微博(微博表中插入1條數據),返回 1
         */
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userVO == null){//未登入
            return new JsonResult(StatusCode.NOT_LOGIN);
        }
        Weibo weibo = new Weibo();
        BeanUtils.copyProperties(weiboDTO, weibo);
        weibo.setCreated(new Date());
        weibo.setUserId(userVO.getId());
        // 調用接口方法
        weiboMapper.insert(weibo);
        return new JsonResult(StatusCode.OPERATION_SUCCESS);
    }

    /** 微博首頁展示功能 */
    @GetMapping("selectIndex")
    @ApiOperation(value = "微博列表功能")
    public JsonResult selectIndex(){
        List<WeiboIndexVO> list = weiboMapper.selectIndex();
        // 查询所需数据直接返回
        return new JsonResult(StatusCode.OPERATION_SUCCESS, list);
    }

    /** 微博詳情頁展示功能
     * ApiImplicitParam註解：
     * name：指定参數名稱
     * value：Knife4j頁面中參數描述
     * required：是否必須填寫
     * dataType：指定参數類型,此處一定要指定,否則默認都為 字符串
     * */
    @GetMapping("selectById")
    @ApiOperation(value = "微博詳情功能")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="id",value = "微博id",required = true,dataType = "int"),
            @ApiImplicitParam(name="username", value = "用戶名", required = true)
    })
    public JsonResult selectById(@Range(min=1, max=200, message = "ID值必須在1-200之間")int id, String username){
        //手動抛出異常
        if (id < 0){
            throw new IllegalArgumentException("ID值不能小於0");
        }
        WeiboDetailVO weiboDetailVO = weiboMapper.selectById(id);
        return new JsonResult(StatusCode.OPERATION_SUCCESS, weiboDetailVO);
    }
}
