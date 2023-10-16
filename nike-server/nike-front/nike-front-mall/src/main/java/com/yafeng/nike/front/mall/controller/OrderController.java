package com.yafeng.nike.front.mall.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.web.JsonResult;
import com.yafeng.nike.front.mall.pojo.param.OrderAddNewParam;
import com.yafeng.nike.front.mall.pojo.vo.OrderListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.OrderStandardVO;
import com.yafeng.nike.front.mall.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * 處理訂單相關請求的控制器類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/orders")
@Validated
@Api(tags = "6. 訂單管理")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    public OrderController() {
        log.debug("創建控制器類對象：OrderController");
    }

    @PostMapping("/create")
    @ApiOperation("創建訂單")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsIdList", value = "商品ID", required = true, dataType = "long[]"),
            @ApiImplicitParam(name = "receiverAddressId", value = "收貨地址ID", required = true, dataType = "long")
    })
    public JsonResult add(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                          @Valid OrderAddNewParam orderAddNewParam) {
        log.debug("開始處理【創建訂單】的請求，當事人：{}，參數：{}", currentPrincipal, orderAddNewParam);
        Long orderId = orderService.create(currentPrincipal, orderAddNewParam);
        return JsonResult.ok(orderId);
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根據ID查詢訂單")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "訂單ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                      @PathVariable @Range(min = 1, message = "請提交有效的訂單ID值！") Long id) {
        log.debug("開始處理【根據ID查詢訂單】的請求，參數：{}", id);
        OrderStandardVO order = orderService.getStandardById(currentPrincipal, id);
        return JsonResult.ok(order);
    }

    @GetMapping("")
    @ApiOperation("查詢訂單列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "int")
    })
    public JsonResult listByCategory(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                     @Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【查詢訂單列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<OrderListItemVO> pageData = orderService.listByUser(currentPrincipal, pageNum);
        return JsonResult.ok(pageData);
    }

}