package cn.tedu.egmybatis2.mapper;

import cn.tedu.egmybatis2.pojo.Orders;
import cn.tedu.egmybatis2.pojo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    /* 添加一個訂單：insert */
    int ordersInsert(Orders orders);

    /* 查詢所有訂單：selectAll */
    List<Orders> selectAll ();

    /* 通過id查詢1個訂單：selectOne */
    Orders selectOne(int id);

    /* 根據id查詢一個訂單,只要訂單的id和訂單的狀態 */
    OrdersVO selectById(int id);

    /* 動態修改訂單：dynamicUpdate */
    int dynamicUpdate(Orders orders);

    /* 通過一個id删除訂單：deleteById */
    int deleteById(int id);

    /* 通過多個id批量删除訂單：deleteMany - 數組 */
    int deleteMany (Integer[] ids);

    /* 通過多個id批量删除訂單：deleteMany - List */
    int deleteMany1 (List<Integer> ids);

    /* 統計訂單總數：selectCount */
    int selectCount();
}
