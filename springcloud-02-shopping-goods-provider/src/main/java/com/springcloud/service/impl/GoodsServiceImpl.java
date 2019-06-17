package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.GoodsMapper;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	@Transactional
	public Integer insert(Goods goods) {
		return this.goodsMapper.insert(goods);
	}
	@Override
	public PageInfo<Goods> select(Goods goods, Integer pageNumber) {
		// 在商品的两端添加%%
		goods.setGoodsName("%" + goods.getGoodsName() + "%");
		// 设置分页信息(PageHelper起始页默认为1,每页显示数据的条数)
		PageHelper.startPage(pageNumber+1,PageUtils.PAGE_ROW_COUNT);
		// 查询满足条件的商品信息
		List<Goods> select = this.goodsMapper.select(goods);
		return new PageInfo<>(select);
	}
	@Transactional
	@Override
	public Integer updateGoodsById(Goods goods) {
		return this.goodsMapper.updateGoodsById(goods);
	}
	@Transactional
	@Override
	public int updateGoodsByPrimaryKey(Goods goods) {
		return this.goodsMapper.updateGoodsByPrimaryKey(goods);
	}
	@Override
	public List<Goods> selectGroupSum() {
		// TODO Auto-generated method stub
		return this.goodsMapper.selectGoodsGroup();
	}
	
}
