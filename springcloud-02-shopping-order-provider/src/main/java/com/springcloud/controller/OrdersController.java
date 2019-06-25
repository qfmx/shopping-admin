package com.springcloud.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

@RestController // 会返回结果为JSON对象
@RequestMapping("order")
public class OrdersController {
	@Autowired //自动装载,目的消除getter/setter方法
	private OrdersService ordersService;
	/**
	 * 查询满足条件的订单信息
	 * @param orders 查询条件
	 * @param pageNumber 页码
	 * @return 成功返回0，失败返回1 对应ResultValue类的setCode(),返回到视图层
	 */
	@RequestMapping(value = "/selectOrders")
	public ResultValue selectOrders(Orders orders, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			// 调用模型层的方法查询满足体条件的订单信息
			PageInfo<Orders> pageInfo = this.ordersService.selectOrders(orders, pageNumber);
			// 从分页信息中获得订单信息
			List<Orders> list = pageInfo.getList();
			// 如果查询到满足条件的商品信息
			if (list != null && list.size() > 0) {
				rv.setCode(0); // 成功时设置状态为0

				Map<String, Object> map = new HashMap<>();
				// 将订单信息以指定的键存入map中
				map.put("ordersList", list);
				// 默认查询10条数据,自定义为12条数据 总数据=总页数*数据的条数
				PageUtils pageUtils = new PageUtils(12, pageInfo.getPages() * PageUtils.PAGE_ROW_COUNT);
				// 设置页码
				pageUtils.setPageNumber(pageNumber);
				// 将分页信息以指定的名字存入map中
				map.put("pageUtils", pageUtils);

				rv.setDataMap(map);

				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	/**
	 * 根据指定编号更新订单的状态
	 * @param orders 更新的条件
	 * @return 更新成功返回0，失败返回1
	 */
	@RequestMapping(value = "/updateOrdersStatus")
	public ResultValue updateOrdersStatus(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateStatus = this.ordersService.updateStatus(orders);
			if(updateStatus > 0) {
				rv.setCode(0); //更新成功返回0
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
	/**
	 * 查询指定时间范围内的销售量
	 * @param orders 查询条件
	 * @return
	 */
	@RequestMapping(value = "/selectOrderByDate")
	public ResultValue selectOrderByDate(Orders orders) {
		ResultValue rv = new ResultValue();
		try {
			List<Orders> list = this.ordersService.selectGroupByDate(orders);
			if(list.size() > 0 && list != null) {
				rv.setCode(0); //更新成功返回0
				List<String> x = new ArrayList<>(); // 用于保存查询结果的月份
				List<Double> y = new ArrayList<>(); // 用于保存查询结果的销售额
				for (Orders o : list) {
					x.add(o.getOrderMonth());
					y.add(o.getOrderPrice());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				map.put("groupList", list);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
	
	@RequestMapping(value = "/insert")
	public ResultValue insert(@RequestBody Orders orders) {
		ResultValue rv = new ResultValue();
		orders.setOrderTime(new Date());
		try {
			boolean insert = this.ordersService.insert(orders);
			if(insert) {
				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
}
