package cn.tedu.egmybatis3;

import cn.tedu.egmybatis3.mapper.CustomersMapper;
import cn.tedu.egmybatis3.mapper.UserOrdersMapper;
import cn.tedu.egmybatis3.pojo.Customers;
import cn.tedu.egmybatis3.pojo.UserOrders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Egmybatis3ApplicationTests {

    @Test
    void contextLoads() {
    }

    // 自動裝配
    @Autowired
    private CustomersMapper customersMapper;

    /* 1.在客戶信息表中插入一條數據：0004, 13811112222, Tony, 成都 */
    @Test
    void InsertCusTest(){
        Customers customers = new Customers();
        customers.setCustId("0004");
        customers.setCustName("Tony");
        customers.setCustTel("13811112222");
        customers.setAddress("成都");
        customersMapper.insertCus(customers);
    }

    /* 2.根據客戶编號查詢某個客戶信息，结果：Customers */
    @Test
    void SelectByIdTest(){
        System.out.println(customersMapper.selectById("0002"));
    }

    /* 3.查詢所有客戶的信息，结果放到 List 集合中 */
    @Test
    void SelectByIdsTest(){
        System.out.println(customersMapper.selectByIds());
    }

    /* 4.查詢指定城市的客戶信息，结果集放到 List集合 中，比如查詢北京的客戶信息 */
    @Test
    void SelectByAddressTest(){
        System.out.println(customersMapper.selectByAddress("北京"));
    }

    /* 5.查詢指定城市的客戶信息，只查詢 姓名 cust_name 和 城市 address，查詢结果放到 List<CustomersVO>中 */
    @Test
    void SelectByAddressVOTest(){
        System.out.println(customersMapper.selectByAddressVO("北京"));
    }

    /* 6.根據客戶的id動態修改客戶信息 */
    @Test
    void UpdateByIdTest(){
        Customers customers = new Customers();
        customers.setCustId("0003");
        customers.setCustTel("13333333333");
        customersMapper.updateById(customers);
    }

    /* 7.根據訂單编號動態删除訂單信息（數組方式和 List集合方式都可以） */
    @Test
    void DeleteByIdsTest(){
        List<String> ids =new ArrayList<>();
        ids.add("0006");
        System.out.println(customersMapper.deleteByIds(ids));
    }

    // 自動裝配
    @Autowired
    private UserOrdersMapper userOrdersMapper;

    /* 9.查詢所有訂單信息，放到List集合中 */
    @Test
    void SelectOrdersTest(){
        System.out.println(userOrdersMapper.selectOrders());
    }

    /* 10.查詢指定訂單狀態的信息，放到List集合中，比如：查詢訂單狀態為待付款 1 的訂單 */
    @Test
    void  SelectByStatusTest(){
        System.out.println(userOrdersMapper.selectByStatus("1"));
    }

    /* 11.查詢指定用戶的所有訂單: 客戶姓名、客戶地址、訂單編號、訂單狀態、訂單總金額 */
    @Test
    void SelectUserOrdersTest(){
        System.out.println(userOrdersMapper.selectUserOrders("0001"));
    }

    /*
      12.
      根據客戶編號，查詢該客戶的所有訂單，
      要求顯示：
      客戶編號 cust_id、
      客戶名字 cust_name、
      送貨地址 address、
      該客戶成交的所有訂單，放到List<UserOrders>集合中
    */
    @Test
    void SelectUserOrdersMapTest(){
        System.out.println(userOrdersMapper.selectUserOrdersMap("0001"));
    }
}
