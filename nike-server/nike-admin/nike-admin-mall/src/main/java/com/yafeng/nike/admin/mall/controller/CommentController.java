package com.yafeng.nike.admin.mall.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.admin.mall.pojo.vo.CommentListItemVO;
import com.yafeng.nike.admin.mall.service.ICommentService;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;

/**
 * 處理評論相關請求的控制器類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/comments")
@Validated
@Api(tags = "3. 評論管理")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    public CommentController() {
        log.debug("創建控制器類對象：CommentController");
    }

    @PostMapping("/{id:[0-9]+}/check-state/pass")
    @PreAuthorize("hasAuthority('/mall/comment/check')")
    @ApiOperation("審核通過評論")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備注信息", required = true, paramType = "query", dataType = "string")
    })
    public JsonResult passCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請提交有效的評論ID值！") Long id,
                                @NotBlank(message = "請提交審核備注信息") String remarks) {
        log.debug("開始處理【審核通過評論】的請求，參數：{}", id);
        commentService.passCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/reject")
    @PreAuthorize("hasAuthority('/mall/comment/check')")
    @ApiOperation("拒絕審核評論")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "評論ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備注信息", required = true, paramType = "query", dataType = "string")
    })
    public JsonResult rejectCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @PathVariable @Range(min = 1, message = "請提交有效的評論ID值！") Long id,
                                  @NotBlank(message = "請提交審核備注信息") String remarks) {
        log.debug("開始處理【拒絕審核評論】的請求，參數：{}", id);
        commentService.rejectCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/mall/comment/simple')")
    @ApiOperation("查詢商品的評論列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型", example = "all")
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值！") Integer page, String queryType) {
        log.debug("開始處理【查詢商品的評論列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CommentListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = commentService.list(pageNum, Integer.MAX_VALUE);
        } else {
            pageData = commentService.list(pageNum);
        }
        return JsonResult.ok(pageData);
    }

}
