package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Orders;
/**
 * 订单模块模型层接口，用于定义模型层方法
 * @author Ya
 *
 */
public interface OrdersService {
	/**
	 * 查询满足条件订单的所有信息(有分页功能)
	 * @param orders 查询条件
	 * @pageNumber 页码
	 * @return 返回具有分页功能的PageInfo<Orders>类型的实例
	 */
	public abstract PageInfo<Orders> selectOrders(Orders orders,Integer pageNumber);
	/**
	 * 更新指定条件的订单信息
	 * @param orders
	 * @return 成功返回大于0的数，失败返回小于等于0的数
	 */
	public abstract Integer updateStatus(Orders orders);
	/**
	 * 
	 * @param orders
	 * @return
	 */
	public abstract List<Orders> selectGroupByDate(Orders orders);
	/**
	 * 添加订单信息以及订单明细
	 * @param record
	 * @return
	 */
	public abstract boolean insert(Orders orders);
}
