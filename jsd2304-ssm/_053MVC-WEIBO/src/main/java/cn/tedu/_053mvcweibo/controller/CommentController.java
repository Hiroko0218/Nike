package cn.tedu._053mvcweibo.controller;

import cn.tedu._053mvcweibo.common.response.JsonResult;
import cn.tedu._053mvcweibo.common.response.StatusCode;
import cn.tedu._053mvcweibo.mapper.CommentMapper;
import cn.tedu._053mvcweibo.pojo.dto.CommentDTO;
import cn.tedu._053mvcweibo.pojo.entity.Comment;
import cn.tedu._053mvcweibo.pojo.vo.CommentVO;
import cn.tedu._053mvcweibo.pojo.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "03.評論模塊")
@RequestMapping("/v1/comment/")
public class CommentController {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    /** 發表評論 */
    @PostMapping("insert")
    @ApiOperation(value = "發布評論功能")
    public JsonResult insert(@RequestBody CommentDTO commentDTO, @ApiIgnore HttpSession session){
        /*
            1. 判斷登入狀態
            2. 未登入：直接返回 2
               登入：調用接口方法,存入數據,返回 1
         */
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userVO == null){//未登入
            return new JsonResult(StatusCode.NOT_LOGIN);
        }

        log.debug("commentDTO = " + commentDTO);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCreated(new Date());
        comment.setUserId(userVO.getId());
        // 調用接口方法
        commentMapper.insert(comment);

        return new JsonResult(StatusCode.OPERATION_SUCCESS);
    }

    /** 獲取指定微博的所有評論 */
    @GetMapping("selectByWeiboId")
    @ApiOperation(value = "獲取評論功能")
    public JsonResult selectByWeiboId(int id){
        List<CommentVO> commentVOList =commentMapper.selectByWeiboId(id);
        return new JsonResult(StatusCode.OPERATION_SUCCESS,commentVOList);
    }
}
