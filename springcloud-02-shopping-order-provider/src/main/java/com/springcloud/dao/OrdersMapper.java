package com.springcloud.dao;

import com.springcloud.entity.Orders;
import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);
    /**
     * 查询orders中满足条件的数据
     * @param orders
     * @return 成功返回java.util.List类型的实例,否则返回null
     */
    public abstract List<Orders> selectOrders(Orders orders);
    /**
     * 更新指定编号的订单状态
     * @param orders 查询条件
     * @return 成功返回大于0的整数
     */
    public abstract Integer updateOrdersStatus(Orders orders);
    /**
     * 查询指定日期范围内的销售额
     * @param orders 查询条件
     * @return 成功返回java.util.List类型的实例,否则返回null
     */
    public abstract List<Orders> selectGroupByDate(Orders orders);
    /**
     * 查询GOODS表中指定用户编号的订单信息
     * @param useraId
     * @return
     */
    public abstract List<Orders> selectByUserId(Integer useraId);
}