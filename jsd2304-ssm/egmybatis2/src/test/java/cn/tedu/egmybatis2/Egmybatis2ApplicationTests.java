package cn.tedu.egmybatis2;

import cn.tedu.egmybatis2.mapper.OrderMapper;
import cn.tedu.egmybatis2.pojo.Orders;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Egmybatis2ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderMapper orderMapper;

    /* 添加一個訂單：insert */
    @Test
    void OrdersInsertTest(){
       Orders orders = new Orders();
       orders.setState("已付款");
       orders.setAmount(300.0);
       orders.setCreated(new Date());
       orderMapper.ordersInsert(orders);
    }

    /* 查詢所有訂單：selectAll */
    @Test
    void SelectAllTest(){
        System.out.println(orderMapper.selectAll());
    }

    /* 通過id查詢1個訂單：selectOne */
    @Test
    void SelectOneTest(){
        System.out.println(orderMapper.selectOne(2));
    }

    /* 根據id查詢一個訂單,只要訂單的id和訂單的狀態 */
    @Test
    void SelectByIdTest(){
        System.out.println(orderMapper.selectById(3));
    }

    /* 動態修改訂單：dynamicUpdate */
    @Test
    void DynamicUpdate(){
        Orders orders = new Orders();
        orders.setId(1);
        orders.setState("已匯款");
        orders.setAmount(600.0);
        orders.setCreated(new Date());
        orderMapper.dynamicUpdate(orders);
    }

    /* 通過一個id删除訂單：deleteById */
    @Test
    void DeleteByIdTest(){
        orderMapper.deleteById(1);
    }

    /* 通過多個id批量删除訂單：deleteMany - 數組 */
    @Test
    void DeleteManyTest(){
        Integer[] ids ={1,3,5};
        System.out.println(orderMapper.deleteMany(ids));
    }

    /* 通過多個id批量删除訂單：deleteMany - List */
    @Test
    void DeleteManyTest1(){
        List<Integer> ids =new ArrayList();
        ids.add(2);
        ids.add(4);
        orderMapper.deleteMany1(ids);
    }

    /* 統計訂單總數：selectCount */
    @Test
    void SelectCountTest(){
        System.out.println(orderMapper.selectCount());
    }
}
