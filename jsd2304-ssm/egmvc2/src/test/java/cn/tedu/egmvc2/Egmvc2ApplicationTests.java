package cn.tedu.egmvc2;

import cn.tedu.egmvc2.mapper.AddMapper;
import cn.tedu.egmvc2.pojo.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Egmvc2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired(required = false)
    private AddMapper addMapper;

    @Test
    void InstAddTest(){
        Address add = new Address();
        add.setReceiver("嘿嘿嘿");
        add.setEmail("xidl123");
        System.out.println(addMapper.instAdd(add));
    }

    @Test
    void SelectAddTest(){
        System.out.println(addMapper.selectAdd());
    }

    @Test
    void DeleteAddTest(){
        System.out.println(addMapper.deleteAdd(2));
    }

    @Test
    void UpdateAddTest(){
        Address add = new Address();
        add.setId(1);
        add.setEmail("coco123456");
        System.out.println(addMapper.updateAdd(add));
    }

}
