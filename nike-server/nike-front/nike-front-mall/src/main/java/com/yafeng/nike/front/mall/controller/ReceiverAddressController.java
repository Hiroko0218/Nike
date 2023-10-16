package com.yafeng.nike.front.mall.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.web.JsonResult;
import com.yafeng.nike.front.mall.pojo.param.ReceiverAddressAddNewParam;
import com.yafeng.nike.front.mall.pojo.param.ReceiverAddressUpdateParam;
import com.yafeng.nike.front.mall.pojo.vo.ReceiverAddressListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.ReceiverAddressStandardVO;
import com.yafeng.nike.front.mall.service.IReceiverAddressService;
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
import java.util.List;

/**
 * 處理收貨地址相關請求的控制器
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/receiver-addresses")
@Validated
@Api(tags = "4. 收貨地址管理")
public class ReceiverAddressController {

    @Autowired
    private IReceiverAddressService receiverAddressService;

    public ReceiverAddressController() {
        log.debug("創建控制器類對象：ReceiverAddressController");
    }

    @PostMapping("/add-new")
    @ApiOperation("添加收貨地址")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaCode", value = "區編碼", required = true, dataType = "String"),
            @ApiImplicitParam(name = "cityCode", value = "市編碼", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detail", value = "詳細地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isDefault", value = "是否默認", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "receiverName", value = "收貨人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "receiverPhone", value = "收貨電話", required = true, dataType = "String"),
    })
    public JsonResult addNew(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @Valid ReceiverAddressAddNewParam receiverAddressAddNewParam) {
        log.debug("開始處理【添加收貨地址】的請求，當事人：{}，參數：{}", currentPrincipal, receiverAddressAddNewParam);
        receiverAddressService.addNew(currentPrincipal, receiverAddressAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @ApiOperation("刪除收貨地址")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收貨地址ID", required = true, dataType = "long")
    })
    public JsonResult delete(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @PathVariable @Range(min = 1, message = "請提交有效的收貨地址ID值！") Long id) {
        log.debug("開始處理【刪除收貨地址】的請求，當事人：{}，參數：{}", currentPrincipal, id);
        receiverAddressService.delete(currentPrincipal, id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/update")
    @ApiOperation("修改收貨地址")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收貨地址ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "receiverName", value = "收貨人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "receiverPhone", value = "收貨電話", required = true, dataType = "String"),
            @ApiImplicitParam(name = "cityCode", value = "市編碼", required = true, dataType = "String"),
            @ApiImplicitParam(name = "areaCode", value = "區編碼", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detail", value = "詳細地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用戶ID", required = true, dataType = "long")
    })
    public JsonResult update(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @PathVariable @Range(min = 1, message = "請提交有效的收貨地址ID值！") Long id,
                             @Valid ReceiverAddressUpdateParam receiverAddressUpdateParam) {
        log.debug("開始處理【修改收貨地址】的請求，當事人：{}，參數：{}", currentPrincipal, receiverAddressUpdateParam);
        receiverAddressService.updateInfoById(currentPrincipal, id, receiverAddressUpdateParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/set-default")
    @ApiOperation("設置默認收貨地址")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收貨地址ID", required = true, dataType = "long")
    })
    public JsonResult setDefault(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                 @PathVariable @Range(min = 1, message = "請提交有效的收貨地址ID值！") Long id) {
        log.debug("開始處理【設置默認收貨地址】的請求，當事人：{}，參數：{}", currentPrincipal, id);
        receiverAddressService.setDefault(currentPrincipal, id);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @ApiOperation("根據ID查詢收貨地址")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收貨地址ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                      @PathVariable @Range(min = 1, message = "請提交有效的收貨地址ID值！") Long id) {
        log.debug("開始處理【根據ID查詢收貨地址】的請求，當事人：{}，參數：{}", currentPrincipal, id);
        ReceiverAddressStandardVO queryResult = receiverAddressService.getStandardById(currentPrincipal, id);
        return JsonResult.ok(queryResult);
    }

    @GetMapping("")
    @ApiOperation("查詢收貨地址列表")
    @ApiOperationSupport(order = 421)
    public JsonResult listByUser(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal) {
        log.debug("開始處理【查詢收貨地址列表】的請求，當事人：{}", currentPrincipal);
        List<ReceiverAddressListItemVO> list = receiverAddressService.listByUser(currentPrincipal);
        return JsonResult.ok(list);
    }

}
