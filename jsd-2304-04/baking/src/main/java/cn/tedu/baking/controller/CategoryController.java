package cn.tedu.baking.controller;

import cn.tedu.baking.mapper.CategoryMapper;
import cn.tedu.baking.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories/")
public class CategoryController {

    @Autowired
    CategoryMapper mapper;

    @GetMapping("{type}/sub")
    public JsonResult sub(@PathVariable Integer type){

        return JsonResult.ok(mapper.selectByType(type));
    }
}
