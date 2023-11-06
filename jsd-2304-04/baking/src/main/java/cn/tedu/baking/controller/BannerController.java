package cn.tedu.baking.controller;


import cn.tedu.baking.mapper.BannerMapper;
import cn.tedu.baking.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/banners/")
public class BannerController {

    @Autowired
    BannerMapper mapper;

    public BannerController() {
        System.out.println("BannerController.BannerController");
    }

    @GetMapping("")
    public JsonResult select(){
        return JsonResult.ok(mapper.select());
    }

    @GetMapping("admin")
    public JsonResult selectForAdmin(){
        return JsonResult.ok(mapper.selectForAdmin());
    }

    @PostMapping("{id}/delete")
    public JsonResult delete(@PathVariable Long id){
        mapper.deleteById(id);
        return JsonResult.ok();
    }
}
