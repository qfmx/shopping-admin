package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;

public interface OrderDetailService {
	/**
	 * 	查询指定编号商品信息
	 * @param orderId 商品编号
	 * @param pageNumber 页码
	 * @return 成功返回PageInfo<OrderDetail>类型的实例，失败返回null
	 */
	public abstract PageInfo<OrderDetail> selctOrderDetailByOrderId(Integer orderId,Integer pageNumber);
	/**
	 * 向购物车中添加订单明细的信息
	 * @param userId
	 * @param orderDetails
	 * @return
	 */
	public abstract boolean addShopping(Integer userId,OrderDetail orderDetails);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public abstract List<OrderDetail> selectShopping(Integer userId);
}
