package cn.tedu.egmvc2.controller;

import cn.tedu.egmvc2.mapper.AddMapper;
import cn.tedu.egmvc2.pojo.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address/")
public class AddController {

    @Autowired(required = false)
    private AddMapper addMapper;

    @PostMapping("insert")
    public String addAdd(Address add){
        int i = addMapper.instAdd(add);
        if (i>0){
            return "添加成功";
        }
        return "添加失敗";
    }

    @GetMapping("select")
    public List<Address> selectAdd(){
        return addMapper.selectAdd();
    }

    @PostMapping("delete")
    public String deleteAdd(int id){
        int i =addMapper.deleteAdd(id);
        if(i>0){
            return "刪除成功";
        }
        return "刪除失敗";
    }

    @PostMapping("update")
    public String updateAdd(Address add){
        int i =addMapper.updateAdd(add);
        if (i>0){
            return "修改成功";
        }
        return "修改失敗";
    }
}
