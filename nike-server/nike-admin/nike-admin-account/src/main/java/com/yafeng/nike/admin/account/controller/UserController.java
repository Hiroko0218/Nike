package com.yafeng.nike.admin.account.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.admin.account.pojo.param.UserAddNewParam;
import com.yafeng.nike.admin.account.pojo.param.UserUpdateInfoParam;
import com.yafeng.nike.admin.account.pojo.vo.UserListItemVO;
import com.yafeng.nike.admin.account.pojo.vo.UserStandardVO;
import com.yafeng.nike.admin.account.service.IUserService;
import com.yafeng.nike.common.consts.web.HttpConsts;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.validation.account.UserRules;
import com.yafeng.nike.common.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 處理用戶相關請求的控制器類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/users")
@Validated
@Api(tags = "1. 用戶管理")
public class UserController implements HttpConsts {

    @Autowired
    private IUserService userService;

    public UserController() {
        log.info("創建控制器對象：UserController");
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/account/user/add-new')")
    @ApiOperation("添加用戶")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "avatar", value = "頭像URL", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "電子郵箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "enable", value = "是否啟用，1=啟用，0=未啟用", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "password", value = "密碼", required = true, dataType = "long"),
            @ApiImplicitParam(name = "phone", value = "手機號碼", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleIds", value = "用戶的角色ID的數組", required = true, dataType = "Long[]"),
            @ApiImplicitParam(name = "username", value = "用戶名", required = true, dataType = "String")
    })
    public JsonResult addNew(@Valid UserAddNewParam userAddNewParam) {
        log.debug("開始處理【添加用戶】的請求，參數：{}", userAddNewParam);
        userService.addNew(userAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/account/user/delete')")
    @ApiOperation("根據ID刪除用戶")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable Long id) {
        log.debug("開始處理【根據ID刪除用戶】的請求，參數：{}", id);
        userService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/info/update")
    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改基本信息")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "description", value = "簡介", required = true, dataType = "String")
    })
    public JsonResult updateInfo(@PathVariable Long id, @Valid UserUpdateInfoParam userUpdateInfoParam) {
        log.debug("開始處理【修改基本信息】的請求，用戶：{}，新基本信息：{}", id, userUpdateInfoParam);
        userService.updateInfo(id, userUpdateInfoParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/password/update")
    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改密碼")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "newPassword", value = "新密碼", required = true, paramType = "query")
    })
    public JsonResult updatePassword(@PathVariable Long id,
                                     @Pattern(regexp = UserRules.PATTERN_PASSWORD, message = UserRules.MESSAGE_PASSWORD_PATTERN) String newPassword) {
        log.debug("開始處理【修改密碼】的請求，用戶：{}，原密碼：{}，新密碼：{}", id, newPassword);
        userService.updatePassword(id, newPassword);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/avatar/update")
    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改頭像")
    @ApiOperationSupport(order = 320)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "avatar", value = "新頭像的路徑", required = true, paramType = "query")
    })
    public JsonResult updateAvatar(@PathVariable Long id,
                                   @NotBlank(message = "請提交新頭像的路徑") String avatar) {
        log.debug("開始處理【修改頭像】的請求，用戶：{}，新頭像：{}", id, avatar);
        userService.updateAvatar(id, avatar);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/phone/update")
    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改手機號碼")
    @ApiOperationSupport(order = 330)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "phone", value = "手機號碼", required = true, paramType = "query")
    })
    public JsonResult updatePhone(@PathVariable Long id,
                                  @Pattern(regexp = UserRules.PATTERN_PHONE, message = UserRules.MESSAGE_PHONE_PATTERN) String phone) {
        log.debug("開始處理【修改手機號碼】的請求，用戶：{}，新手機號碼：{}", id, phone);
        userService.updatePhone(id, phone);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/email/update")
    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改電子郵箱")
    @ApiOperationSupport(order = 340)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "email", value = "電子郵箱", required = true, paramType = "query")
    })
    public JsonResult updateEmail(@PathVariable Long id,
                                  @Pattern(regexp = UserRules.PATTERN_EMAIL, message = UserRules.MESSAGE_EMAIL_PATTERN) String email) {
        log.debug("開始處理【修改電子郵箱】的請求，用戶：{}，新手機號碼：{}", id, email);
        userService.updateEmail(id, email);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/account/user/enable')")
    @ApiOperation("啟用用戶")
    @ApiOperationSupport(order = 350)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long")
    })
    public JsonResult setEnable(@PathVariable Long id) {
        log.debug("開始處理【啟用用戶】的請求，參數：{}", id);
        userService.setEnable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/account/user/enable')")
    @ApiOperation("禁用用戶")
    @ApiOperationSupport(order = 351)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long")
    })
    public JsonResult setDisable(@PathVariable Long id) {
        log.debug("開始處理【禁用用戶】的請求，參數：{}", id);
        userService.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("根據ID查詢用戶")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用戶ID", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/account/user/query')")
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "獲取用戶詳情失敗，請提交合法的ID值！") Long id) {
        log.debug("開始處理【根據ID查詢用戶】的請求，參數：{}", id);
        UserStandardVO tag = userService.getStandardById(id);
        return JsonResult.ok(tag);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/account/user/query')")
    @ApiOperation("查詢用戶列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", dataType = "long")
    })
    public JsonResult list(Integer page) {
        log.debug("開始處理【查詢用戶列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<UserListItemVO> pageData = userService.list(pageNum);
        return JsonResult.ok(pageData);
    }

}
