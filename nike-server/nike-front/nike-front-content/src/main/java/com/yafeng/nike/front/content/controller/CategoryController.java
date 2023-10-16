package com.yafeng.nike.front.content.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.common.web.JsonResult;
import com.yafeng.nike.front.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.front.content.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("")
    @ApiOperation("查詢類別列表")
    @ApiOperationSupport(order = 420)
    public JsonResult list() {
        log.debug("開始處理【查詢類別列表】的請求，無參數");
        List<CategoryListItemVO> list = categoryService.list();
        return JsonResult.ok(list);
    }

}
