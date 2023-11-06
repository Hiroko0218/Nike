package cn.tedu.egmybatis3.mapper;

import cn.tedu.egmybatis3.pojo.Customers;
import cn.tedu.egmybatis3.pojo.CustomersVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomersMapper {
    /* 1.在客戶信息表中插入一條數據：0004, 13811112222, Tony, 成都 */
    int insertCus(Customers customers);

    /* 2.根據客戶编號查詢某個客戶信息，结果：Customers */
    Customers selectById(String custId);

    /* 3.查詢所有客戶的信息，结果放到 List 集合中 */
    List<Customers> selectByIds();

    /* 4.查詢指定城市的客戶信息，结果集放到 List集合 中，比如查詢北京的客戶信息 */
    List<Customers> selectByAddress(String address);

    /* 5.查詢指定城市的客戶信息，只查詢 姓名 cust_name 和 城市 address，查詢结果放到 List<CustomersVO>中 */
    List<CustomersVO> selectByAddressVO(String address);

    /* 6.根據客戶的id動態修改客戶信息 */
    int updateById(Customers customers);

    /* 7.根據訂單编號動態删除訂單信息（數組方式和 List集合方式都可以） */
    int deleteByIds(List<String> ids);
}
