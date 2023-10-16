package com.yafeng.nike.admin.mall.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.admin.mall.pojo.param.GoodsAddNewParam;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsStandardVO;
import com.yafeng.nike.admin.mall.service.IGoodsService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 處理商品相關請求的控制器類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/goods")
@Validated
@Api(tags = "2. 商品管理")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    public GoodsController() {
        log.debug("創建控制器類對象：GoodsController");
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("發布商品")
    @ApiOperationSupport(order = 100)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "barCode", value = "條形碼", required = true, dataType = "String"),
            @ApiImplicitParam(name = "brief", value = "摘要", required = true, dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "類別ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "coverUrl", value = "封面圖", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detail", value = "詳情", required = true, dataType = "String"),
            @ApiImplicitParam(name = "keywords", value = "關鍵詞列表", required = true, dataType = "String"),
            @ApiImplicitParam(name = "salePrice", value = "售價", required = true, dataType = "long"),
            @ApiImplicitParam(name = "sort", value = "排序序號", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "標題", required = true, dataType = "String"),
    })
    public JsonResult addNew(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                             @ApiIgnore HttpServletRequest request,
                             @Valid GoodsAddNewParam goodsAddNewParam) {
        log.debug("開始處理【發布商品】的請求，參數：{}", goodsAddNewParam);
        String remoteAddr = request.getRemoteAddr();
        goodsService.addNew(currentPrincipal, remoteAddr, goodsAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/mall/goods/delete')")
    @ApiOperation("根據ID刪除商品")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id) {
        log.debug("開始處理【根據ID刪除商品】的請求，參數：{}", id);
        goodsService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/pass")
    @PreAuthorize("hasAuthority('/mall/goods/check')")
    @ApiOperation("審核通過商品")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備注信息", required = true, paramType = "query", dataType = "string")
    })
    public JsonResult passCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                @PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id,
                                @NotBlank(message = "請提交審核備注信息") String remarks) {
        log.debug("開始處理【審核通過商品】的請求，參數：{}", id);
        goodsService.passCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/check-state/reject")
    @PreAuthorize("hasAuthority('/mall/goods/check')")
    @ApiOperation("拒絕審核商品")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "remarks", value = "備注信息", required = true, paramType = "query", dataType = "string")
    })
    public JsonResult rejectCheck(@AuthenticationPrincipal @ApiIgnore CurrentPrincipal currentPrincipal,
                                  @PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id,
                                  @NotBlank(message = "請提交審核備注信息") String remarks) {
        log.debug("開始處理【拒絕審核商品】的請求，參數：{}", id);
        goodsService.rejectCheck(currentPrincipal, id, remarks);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/put-on")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("上架商品")
    @ApiOperationSupport(order = 320)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult putOn(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id) {
        log.debug("開始處理【上架商品】的請求，參數：{}", id);
        goodsService.putOn(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/pull-off")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("下架商品")
    @ApiOperationSupport(order = 321)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult pullOff(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id) {
        log.debug("開始處理【下架商品】的請求，參數：{}", id);
        goodsService.pullOff(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/set-recommend")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("推薦商品")
    @ApiOperationSupport(order = 330)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult setRecommend(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id) {
        log.debug("開始處理【推薦商品】的請求，參數：{}", id);
        goodsService.setRecommend(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/cancle-recommend")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("取消推薦商品")
    @ApiOperationSupport(order = 331)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult cancelRecommend(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id) {
        log.debug("開始處理【取消推薦商品】的請求，參數：{}", id);
        goodsService.cancelRecommend(id);
        return JsonResult.ok();
    }

    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("根據ID查詢商品")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "long")
    })
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "請提交有效的商品ID值！") Long id) {
        log.debug("開始處理【根據ID查詢商品】的請求，參數：{}", id);
        GoodsStandardVO queryResult = goodsService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }


    @GetMapping("")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("查詢商品列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "long"),
    })
    public JsonResult list(@Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【查詢商品列表】的請求，頁碼：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<GoodsListItemVO> pageData = goodsService.list(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/list-by-category")
    @PreAuthorize("hasAuthority('/mall/goods/simple')")
    @ApiOperation("根據類別查詢商品列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "商品類別ID", required = true, paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "page", value = "頁碼", defaultValue = "1", paramType = "query", dataType = "long")
    })
    public JsonResult listByCategory(@Range(message = "請提交有效的商品類別ID值！") Long categoryId,
                                     @Range(min = 1, message = "請提交有效的頁碼值！") Integer page) {
        log.debug("開始處理【根據類別查詢商品列表】的請求，父級商品：{}，頁碼：{}", categoryId, page);
        Integer pageNum = page == null ? 1 : page;
        PageData<GoodsListItemVO> pageData = goodsService.listByCategory(categoryId, pageNum);
        return JsonResult.ok(pageData);
    }

    @PostMapping("/rebuild-search")
    @PreAuthorize("hasAuthority('/mall/goods/rebuild-search')")
    @ApiOperation("重建商品的搜索數據")
    @ApiOperationSupport(order = 500)
    public JsonResult rebuildSearch() {
        log.debug("開始處理【重建商品的搜索數據】的請求");
        goodsService.rebuildSearch();
        return JsonResult.ok();
    }

}
