package com.yafeng.nike.admin.account.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.admin.account.pojo.vo.RoleListItemVO;
import com.yafeng.nike.admin.account.service.IRoleService;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 處理角色相關請求的控制器
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/roles")
@Validated
@Api(tags = "2. 角色管理")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    public RoleController() {
        log.debug("創建控制器類對象：RoleController");
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/account/user/query')")
    @ApiOperation("查詢角色列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", dataType = "long"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型，當需要查詢全部數據時，此參數值應該是all")
    })
    public JsonResult list(Integer page, String queryType) {
        log.debug("開始處理【查詢角色列表】的請求，頁碼：{}", page);
        if (page == null) {
            page = 1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<RoleListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = roleService.list(1, Integer.MAX_VALUE);
        } else {
            pageData = roleService.list(pageNum);
        }
        return JsonResult.ok(pageData);
    }

}
