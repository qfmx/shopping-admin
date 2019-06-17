package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;
/**
 * 订单明细模块模型层的实现类
 * @author Ya
 *
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Override
	public PageInfo<OrderDetail> selctOrderDetailByOrderId(Integer orderId, Integer pageNumber) {
		
		List<OrderDetail> list = this.orderDetailMapper.selectOrderDetail(orderId);
		PageHelper.startPage(pageNumber+1, 5); // 直接设置每页显示5条数据
		return new PageInfo<>(list);
	}

}
