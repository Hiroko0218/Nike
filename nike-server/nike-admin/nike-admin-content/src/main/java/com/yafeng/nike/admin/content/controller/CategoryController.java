package com.yafeng.nike.admin.content.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.admin.content.pojo.param.CategoryAddNewParam;
import com.yafeng.nike.admin.content.pojo.param.CategoryUpdateInfoParam;
import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.admin.content.service.ICategoryService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 處理類別相關請求的控制器類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/categories")
@Validated
@Api(tags = "1. 類別管理")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    public CategoryController() {
        log.debug("創建控制器類對象：CategoryController");
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("添加類別")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "enable", value = "是否啟用，1=啟用，0=未啟用", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "isDisplay", value = "是否顯示在導航欄中，1=啟用，0=未啟用", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "keywords", value = "關鍵詞列表，各關鍵詞使用英文的逗號分隔", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "類別名稱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序序號", required = true, dataType = "Integer"),
    })
    public JsonResult addNew(@Valid CategoryAddNewParam categoryAddNewParam) {
        log.debug("開始處理【添加類別】的請求，參數：{}", categoryAddNewParam);
        categoryService.addNew(categoryAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/content/category/delete')")
    @ApiOperation("根據ID刪除類別")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id) {
        log.debug("開始處理【根據ID刪除類別】的請求，參數：{}", id);
        categoryService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("啟用類別")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setEnable(@PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id) {
        log.debug("開始處理【啟用類別】的請求，參數：{}", id);
        categoryService.setEnable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("禁用類別")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setDisable(@PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id) {
        log.debug("開始處理【禁用類別】的請求，參數：{}", id);
        categoryService.setDisable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/display")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("顯示類別")
    @ApiOperationSupport(order = 312)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setDisplay(@PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id) {
        log.debug("開始處理【顯示類別】的請求，參數：{}", id);
        categoryService.setDisplay(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/hidden")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("隱藏類別")
    @ApiOperationSupport(order = 313)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult setHidden(@PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id) {
        log.debug("開始處理【隱藏類別】的請求，參數：{}", id);
        categoryService.setHidden(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/update")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("修改類別詳情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "keywords", value = "關鍵詞列表，各關鍵詞使用英文的逗號分隔", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "類別名稱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序序號", required = true, dataType = "Integer")
    })
    public JsonResult updateInfoById(@PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id,
                                     @Valid CategoryUpdateInfoParam categoryUpdateInfoParam) {
        log.debug("開始處理【修改類別詳情】的請求，ID：{}，新數據：{}", id, categoryUpdateInfoParam);
        categoryService.updateInfoById(id, categoryUpdateInfoParam);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("根據ID查詢類別")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "類別ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(
            @PathVariable @Range(min = 1, message = "請提交有效的類別ID值！") Long id) {
        log.debug("開始處理【根據ID查詢類別】的請求，參數：{}", id);
        CategoryStandardVO queryResult = categoryService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/content/category/simple')")
    @ApiOperation("查詢類別列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查詢類型", example = "all")
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值！") Integer page,
                           String queryType) {
        log.debug("開始處理【查詢類別列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<CategoryListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = categoryService.list(pageNum, Integer.MAX_VALUE);
        } else {
            pageData = categoryService.list(pageNum);
        }
        return JsonResult.ok(pageData);
    }


    @PostMapping("/rebuild-cache")
    @PreAuthorize("hasAuthority('/content/category/rebuild-cache')")
    @ApiOperation("重建緩存")
    @ApiOperationSupport(order = 500)
    public JsonResult rebuildCache() {
        log.debug("開始處理【重建緩存】的請求，無參數");
        categoryService.rebuildCache();
        return JsonResult.ok();
    }

}
