package cn.tedu.egmvc1.controller;

import cn.tedu.egmvc1.mapper.EmpMapper;
import cn.tedu.egmvc1.pojo.dto.EmpAddDTO;
import cn.tedu.egmvc1.pojo.dto.EmpUpdateDTO;
import cn.tedu.egmvc1.pojo.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp/")
public class EmpController {

    @Autowired(required = false)
    private EmpMapper empMapper;

    @PostMapping("insert")
    public String addEmp(EmpAddDTO empAddDTO){
        Employee emp =new Employee();
        BeanUtils.copyProperties(empAddDTO,emp);
        int i = empMapper.insertEmp(emp);
        if (i>0){
            return "添加成功";
        }
        return "添加失敗";
    }

    @GetMapping("select")
    public List<Employee> empList(){
        return empMapper.selectEmpList();
    }

    @GetMapping("update")
    public String updateEmp(EmpUpdateDTO empUpdateDTO){
        Employee emp = new Employee();
        BeanUtils.copyProperties(empUpdateDTO,emp);
        int i = empMapper.empUpdate(emp);
        if (i>0){
            return "修改成功";
        }
        return "修改失敗";
    }

    @GetMapping("delete")
    public String deleteEmp(int id){
        int i = empMapper.empDelete(id);
        if(i>0){
            return "刪除成功";
        }
        return "刪除失敗";
    }
}
