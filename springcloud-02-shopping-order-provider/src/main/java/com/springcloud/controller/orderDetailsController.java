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
}
