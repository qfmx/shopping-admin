package com.springcloud.dao;

import com.springcloud.entity.OrderDetail;
import com.springcloud.entity.Orders;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderDetailId);
    /**
     * 添加商品信息
     * @param record
     * @return
     */
    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer orderDetailId);

    List<OrderDetail> selectAll();

    int updateByPrimaryKey(OrderDetail record);
    /**
     *	 查询指定订单编号的订单明细
     * @param orderDetailId
     * @return 成功返回java.util.List类型的实例,失败返回null
     */
    public abstract List<OrderDetail> selectOrderDetail(Integer orderDetailId);
    /**
     * 查询指定订单编号的商品图片
     * @param orderDetailId 订单细节编号
     * @return 商品图片地址
     */
    public abstract List<OrderDetail> selectGoodsPhotoByOrderDetailId(Integer orderDetailId);
    /**
     * 批量添加订单明细
     * @param orders 一组订单的明细
     * @return
     */
    public abstract Integer insertOrderDetail(Orders orders);
    
}