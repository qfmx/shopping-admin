package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

/**
 * 订单模块模型层的的实现类
 * 
 * @author Ya
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		// 在用户名的两端添加%%
		if (orders.getUser() != null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		System.out.println(orders.getUser());
		// 设置分页信息(PageHelper起始页默认为1,每页显示数据的条数)
		PageHelper.startPage(pageNumber + 1, PageUtils.PAGE_ROW_COUNT);
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		return new PageInfo<>(list);
	}

	@Override
	@Transactional
	public Integer updateStatus(Orders orders) {
		return this.ordersMapper.updateOrdersStatus(orders);
	}
	
	@Override
	public List<Orders> selectGroupByDate(Orders orders) {
		return this.ordersMapper.selectGroupByDate(orders);
	}

}
