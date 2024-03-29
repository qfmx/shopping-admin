package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;
import com.springcloud.vo.ResultValue;

@RestController
@RequestMapping("orderDetails")
public class orderDetailsController {
	@Autowired
	private OrderDetailService orderDetailService;
	
	
	@RequestMapping(value = "/selectOrderDetails")
	public ResultValue selectOrderDetails(@RequestParam("orderId") Integer orderId, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			PageInfo<OrderDetail> pageInfo = this.orderDetailService.selctOrderDetailByOrderId(orderId, pageNumber);
			// 从分页数据获取订单细节信息
			List<OrderDetail> list = pageInfo.getList();
			if(list != null && list.size() >0) {
				// 当查询成功时，设置状态为0
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				// 将订单明细表以指定的键存入map集合中
				map.put("orderDetailList", list);
				PageUtils pageUtils = new PageUtils(5, pageInfo.getPages() * 5);
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
	 * 
	 * @param userId
	 * @param orderDetail
	 * @return
	 */
	@RequestMapping(value = "/addShopping")
	public ResultValue addShopping(@RequestParam("userId") Integer userId,OrderDetail orderDetail) {
		ResultValue rv = new ResultValue();
		try {
			boolean add = this.orderDetailService.addShopping(userId, orderDetail);
			if(add) {
				rv.setCode(0);
				rv.setMessage("购物车添加成功");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("添加购物车失败");
		return rv;
	}
	/**
	 * 从Redis中查询指定用户编号的购物车中商品的信息
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value = "/selectShopping")
	public ResultValue selectShopping(@RequestParam("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			List<OrderDetail> list = this.orderDetailService.selectShopping(userId);
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("shoppingList", list);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获取购物车失败");
		return rv;
	}
	/**
	 * 根据用户编号移除购物车指定商品编号的商品信息
	 * @param orderDetail
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/removeShopping")
	public ResultValue removeShopping(OrderDetail orderDetail,@RequestParam("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			boolean remove = this.orderDetailService.removeShopping(userId, orderDetail);
			if(remove) {
				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("移除购物车失败");
		return rv;
	}
}
