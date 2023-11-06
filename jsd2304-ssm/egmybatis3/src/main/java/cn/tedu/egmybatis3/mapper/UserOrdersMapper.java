package cn.tedu.egmybatis3.mapper;


import cn.tedu.egmybatis3.pojo.UserOrders;
import cn.tedu.egmybatis3.pojo.OrdersVO;
import cn.tedu.egmybatis3.pojo.UserOrdersMapVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrdersMapper {
    /* 9.查詢所有訂單信息，放到List集合中 */
    List<UserOrders> selectOrders();

    /* 10.查詢指定訂單狀態的信息，放到List集合中，比如：查詢訂單狀態為待付款 1 的訂單 */
    List<UserOrders> selectByStatus(String status);

    /* 11.查詢指定用戶的所有訂單: 客戶姓名、客戶地址、訂單編號、訂單狀態、訂單總金額 */
    List<OrdersVO> selectUserOrders(String userId);

    /*
      12.
      根據客戶編號，查詢該客戶的所有訂單，
      要求顯示：
      客戶編號 cust_id、
      客戶名字 cust_name、
      送貨地址 address、
      該客戶成交的所有訂單，放到List<UserOrders>集合中
    */
    UserOrdersMapVO selectUserOrdersMap(String cid);
}
