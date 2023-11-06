package cn.tedu.baking.controller;


import cn.tedu.baking.mapper.CommentMapper;
import cn.tedu.baking.mapper.ContentMapper;
import cn.tedu.baking.pojo.dto.CommentDTO;
import cn.tedu.baking.pojo.entity.Comment;
import cn.tedu.baking.response.JsonResult;
import cn.tedu.baking.response.StatusCode;
import cn.tedu.baking.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/v1/comments/")
public class CommentController {

    @Autowired
    CommentMapper mapper;

    @Autowired
    ContentMapper contentMapper;

    @PostMapping("add-new")
    public JsonResult addNew(@RequestBody CommentDTO commentDTO,
                             @AuthenticationPrincipal CustomUserDetails userDetails){
        if (userDetails==null){
            return new JsonResult(StatusCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setCreateTime(new Date());
        mapper.insert(comment);
        //讓評論量+1
        contentMapper.updateCommentCountById(commentDTO.getContentId());
        return JsonResult.ok();
    }

    @GetMapping("{id}")
    public JsonResult select(@PathVariable Long id){
        return JsonResult.ok(mapper.selectByContentId(id));
    }
}
