package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.OrderDetail;
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
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
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
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public boolean insert(Orders orders) {
		int insert = this.ordersMapper.insert(orders);
		if(insert > 0) {
			Integer insertOrderDetail = this.orderDetailMapper.insertOrderDetail(orders);
			String keys = "user"+orders.getUserId(); //获得指定用户的键
			//this.redisTemplate.delete(keys);// 删除redis指定中指定的键（这尼玛全删吧key里面的数据全删了）
			ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
			// 获得用户在redis的购物车所有信息
			List<OrderDetail> range = opsForList.range(keys, 0, -1);
			for (OrderDetail orderDetail : range) {
				System.out.println(orderDetail+"\t");
			}
			List<OrderDetail> orderDetailsList = orders.getOrderDetailsList();
			if(range.size() == orderDetailsList.size()) {
				// 当两个集合的长度相等，表示用户对购物车中所有的商品进行结算
				Boolean delete = this.redisTemplate.delete(keys);
				if(delete) {
					return true;
				}
			}else {
				for (OrderDetail o : orderDetailsList) {
					// 在o中查找首次出现的位置
					int index = range.indexOf(o);
					if(index != -1) { // 找到了o首次出现的位置
						range.remove(index); //删除redis中选择的redis
					}
				}
				this.redisTemplate.delete(keys);
				for (OrderDetail o : range) {
					opsForList.rightPush(keys, o); //重新把未选择的购物车信息添加到redis
				}
				return true;
			}
			if(insertOrderDetail > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Orders> selectByUserId(Integer userId) {
		return this.ordersMapper.selectByUserId(userId);
	}

}
