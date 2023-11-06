package cn.tedu.egmvc1.mapper;

import cn.tedu.egmvc1.pojo.dto.EmpUpdateDTO;
import cn.tedu.egmvc1.pojo.entity.Employee;

import java.util.List;

public interface EmpMapper {
    int insertEmp(Employee emp);

    List<Employee> selectEmpList();

    int empUpdate(Employee emp);

    int empDelete(int id);
}
