package cn.tedu.egmvc1;

import cn.tedu.egmvc1.mapper.EmpMapper;
import cn.tedu.egmvc1.pojo.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Egmvc1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired(required = false)
    private EmpMapper empMapper;

    @Test
    void InsertEmployeeTest(){
        Employee emp = new Employee();
        emp.setTitle("高級工程師");
        emp.setSalary(500000.0);
        emp.setJob("設計網頁");
        System.out.println(empMapper.insertEmp(emp));
    }

    @Test
    void SelectEmpListTest(){
        System.out.println(empMapper.selectEmpList());
    }

    @Test
    void EmpUpdateTest(){
        Employee emp = new Employee();
        emp.setId(1);
        emp.setJob("前後端工程師");
        emp.setTitle("超級工程師");
        System.out.println(empMapper.empUpdate(emp));
    }

    @Test
    void EmpDeleteTest(){
        System.out.println(empMapper.empDelete(3));
    }

}
