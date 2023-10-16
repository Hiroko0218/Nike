package com.yafeng.nike.basic.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.basic.service.IDistrictService;
import com.yafeng.nike.common.pojo.po.DistrictSimplePO;
import com.yafeng.nike.common.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 處理市區相關請求的控制器
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/dict/district")
@Validated
@Api(tags = "1. 字典數據-市區")
public class DistrictController {

    @Autowired
    private IDistrictService districtService;

    public DistrictController() {
        log.debug("創建控制器類對象：DistrictController");
    }

    @GetMapping("/list-by-parent")
    @ApiOperation("根據父級查詢子級地區列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父級單位ID，如果無父級使用0", required = true, dataType = "long", example = "0")
    })
    public JsonResult listByParent(@RequestParam @Range(max = 5000, message = "請提交有效的父級單位ID值！") Long parentId) {
        log.debug("開始處理【根據父級查詢子級地區列表】的請求，參數：{}", parentId);
        List<DistrictSimplePO> districtList = districtService.listByParent(parentId);
        return JsonResult.ok(districtList);
    }

}
