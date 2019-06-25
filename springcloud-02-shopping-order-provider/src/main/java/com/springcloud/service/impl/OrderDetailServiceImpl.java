package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public PageInfo<OrderDetail> selctOrderDetailByOrderId(Integer orderId, Integer pageNumber) {
		
		List<OrderDetail> list = this.orderDetailMapper.selectOrderDetail(orderId);
		PageHelper.startPage(pageNumber+1, 5); // 直接设置每页显示5条数据
		return new PageInfo<>(list);
	}
	@Transactional
	@Override
	public boolean addShopping(Integer userId, OrderDetail orderDetail) {
		try {
			@SuppressWarnings("unchecked")
			ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
			//创建Redis数据库中保存数据的键
			String key = "user" + userId;
			
			//向购物车中添加数据
			//1.获得指定键的list的长度(获得此用户购物车中订单明细的数量)
			Long size = opsForList.size(key);
			
			if(size == 0) {
				//当前用户的购物车为空，直接将订单明细存入list即可
				opsForList.leftPush(key,orderDetail);
			}else {
				//当前用户的购物车不为空，需要判断购物车中是否存在新购买的订单明细
				//1.获得Redis中指定键的list中所有数据
				List<OrderDetail> list = opsForList.range(key, 0, -1);
				//2.在list中查找新的订单明细是否存在
				int indexOf = list.indexOf(orderDetail);
				list.contains(orderDetail);
				
				if(indexOf == -1) {
					//在购物车中没有找到新购买的订单明细，直接将新的订单明细添加到redis中指定键的list中集合
					opsForList.leftPush(key,orderDetail);
				}else {
					//购物车中找到了新购买的订单明细
					//获得redis中指定键list的第N个元素
					OrderDetail o = opsForList.index(key, indexOf);
					Integer num1 = o.getTransactionCount();
					Integer num2 = orderDetail.getTransactionCount();
					//修改订单明细的数量
					o.setTransactionCount(num1 + num2);
					//将修改后的订单明细重新找回到redis中指定键list原来的位置
					opsForList.set(key, indexOf, o);
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> selectShopping(Integer userId) {
		@SuppressWarnings("rawtypes")
		ListOperations opsForList = this.redisTemplate.opsForList();
		@SuppressWarnings({ "rawtypes" })
		List list = opsForList.range("user"+userId, 0, -1); // 获得redis指定键所有的list
		return list;
	}
	@SuppressWarnings({"unchecked" })
	@Override
	public boolean removeShopping(Integer userId, OrderDetail orderDetail) {
		ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
		// 获得用户编号生成redis中list集合对应的键
		String key = "user" +userId;
		// 获得redis中指定键的长度
		Long size = opsForList.size(key);
		// 如果没有元素 结束方法
		if(size == 0) {
			return false;
		}
		// 获得list中所有的元素
		List<OrderDetail> list = opsForList.range(key, 0, -1);
		// 在list集合中查找参数首次出现的位置，没找到返回-1
		int indexOf = list.indexOf(orderDetail);
		if(indexOf == -1) {
			return false;
		}
		list.remove(indexOf);
		this.redisTemplate.delete(key);
		for (OrderDetail Orders : list) {
			opsForList.rightPush(key, Orders);
		}
		return true;
	}
}
