package cn.tedu.baking.controller;


import cn.tedu.baking.mapper.ContentMapper;
import cn.tedu.baking.pojo.dto.ContentDTO;
import cn.tedu.baking.pojo.entity.Content;
import cn.tedu.baking.pojo.vo.ContentIndexVO;
import cn.tedu.baking.pojo.vo.ContentUpdateVO;
import cn.tedu.baking.response.JsonResult;
import cn.tedu.baking.security.CustomUserDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/contents/")
public class ContentController {

    @Autowired
    ContentMapper mapper;

    @PostMapping("add-new")
    public JsonResult addNew(@RequestBody ContentDTO contentDTO,
                             @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        System.out.println("contentDTO = " + contentDTO);
        Content content = new Content();
        BeanUtils.copyProperties(contentDTO, content);

        if (content.getId() != null) {//修改
            content.setUpdateTime(new Date());
            content.setUpdateBy(customUserDetails.getId());
            mapper.update(content);
        } else {//添加
            content.setCreateTime(new Date());
            mapper.insert(content);
        }


        return JsonResult.ok();
    }

    @GetMapping("{type}/management")
    public JsonResult management(@PathVariable Integer type,
                                 @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //通過傳遞過來的type和當前登入的用戶id查詢相關内容
        return JsonResult.ok(
                mapper.selectByType(type, customUserDetails.getId()));
    }

    @GetMapping("{id}/update")
    public JsonResult selectForUpdate(@PathVariable Long id) {

        return JsonResult.ok(mapper.selectByIdForUpdate(id));
    }

    @Value("${filePath}")
    private String dirPath;

    @PostMapping("{id}/delete")
    public JsonResult delete(@PathVariable Long id) {
        //得到封面圖片路徑並删除
        ContentUpdateVO contentUpdateVO = mapper.selectByIdForUpdate(id);
        new File(dirPath + contentUpdateVO.getImgUrl()).delete();
        if (contentUpdateVO.getType() == 2) { //判斷如果是影片則删除影片文件
            new File(dirPath + contentUpdateVO.getVideoUrl()).delete();
        }
        mapper.deleteById(id);
        return JsonResult.ok();
    }

    @GetMapping("{type}/{categoryId}/index")
    public JsonResult selectIndex(@PathVariable Integer type,
                                  @PathVariable Long categoryId) {
        System.out.println("type = " + type + ", categoryId = " + categoryId);

        List<ContentIndexVO> list = mapper.selectByTypeAndCategoryId(type, categoryId);

        return JsonResult.ok(list);
    }

    @GetMapping("{id}/detail")
    public JsonResult selectDetail(@PathVariable Long id) {
        //讓瀏覽量+1
        mapper.updateViewCountById(id);

        return JsonResult.ok(mapper.selectByIdForDetail(id));
    }

    @GetMapping("{userId}/other")
    public JsonResult selectOther(@PathVariable Long userId) {
        return JsonResult.ok(mapper.selectByUserId(userId));
    }

    @GetMapping("hot")
    public JsonResult selectHot() {
        return JsonResult.ok(mapper.selectHot());
    }

    @GetMapping("{type}/list")
    public JsonResult selectList(@PathVariable Integer type){
        return JsonResult.ok(mapper.selectListByType(type));
    }

    @GetMapping("{wd}/search")
    public JsonResult search(@PathVariable String wd){
        return JsonResult.ok(mapper.selectByWd(wd));
    }

    @GetMapping("{type}/admin")
    public JsonResult selectAdmin(@PathVariable Integer type){
        return JsonResult.ok(mapper.selectByTypeForAdmin(type));
    }
}
