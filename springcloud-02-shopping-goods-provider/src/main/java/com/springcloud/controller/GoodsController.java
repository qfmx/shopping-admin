package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;
/**
 * 商品模块控制层
 * @author Ya
 *
 */
@RequestMapping("goods")
@RestController
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	/**
	 * 录入用户信息
	 * @param goods
	 * @return
	 */
	@RequestMapping("/insert")
	public ResultValue insert(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer insert = this.goodsService.insert(goods);
			if(insert > 0) {
				rv.setCode(0);
				rv.setMessage("添加成功");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setMessage("添加失败");
		rv.setCode(1);
		return rv;
	}
	/**
	 * 	查询满足条件的商品信息
	 * @param goods	查询条件
	 * @param pageNumber 页数
	 * @return ResultValue结果集到前台页面
	 */
	@RequestMapping(value = "/select")
	public ResultValue select(Goods goods,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			// 查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			// 从分页信息中获得商品信息
			List<Goods> list = pageInfo.getList();
			// 如果查询到满足条件的商品信息
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String,Object> map = new HashMap<>();
				// 将商品信息以指定的键存入map中
				map.put("goodsList", list);
				
				PageUtils pageUtils = new PageUtils(12,pageInfo.getPages()*PageUtils.PAGE_ROW_COUNT);
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
		rv.setMessage("没有找到满足条件的分页信息！！！");
		return rv;
	}
	/**
	 * 更新指定编号的商品信息（热卖，新品，状态，商品图片）
	 * @param goods 更新条件
	 * @return 将ResultValue对象结果返回的视图层
	 */
	@RequestMapping(value = "/updateGoodsById")
	public ResultValue updateGoodsById(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateGoodsById = this.goodsService.updateGoodsById(goods);
			if(updateGoodsById > 0) {
				rv.setCode(0);
				rv.setMessage("更新成功！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("更新失败！！");
		return rv;
	}
	/**
	 * 修改指定编号的商品信息
	 * @param goods 更新条件
	 * @return
	 */
	@RequestMapping(value = "/updateGoodsMessage")
	public ResultValue updateGoodsMessage(Goods goods) {
		ResultValue rv = new ResultValue();
		try {
			int updateGoodsByPrimaryKey = this.goodsService.updateGoodsByPrimaryKey(goods);
			if(updateGoodsByPrimaryKey > 0) {
				rv.setCode(0);
				rv.setMessage("更新成功！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("更新失败！！");
		return rv;
	}
	/**
	 * 查询销量前十的商品信息
	 * @return
	 */
	@RequestMapping(value = "/selectGroupSum")
	public ResultValue selectGroupSum() {
		ResultValue rv = new ResultValue();
		try {
			List<Goods> list = this.goodsService.selectGroupSum();
			if(list !=null && list.size() > 0) {
				rv.setCode(0);
				// 将查询的结果分成X轴(商品名称) Y轴(商品销量)
				List<String> X = new ArrayList<>(); //X轴(商品名称
				List<Integer> Y = new ArrayList<>(); //Y轴(商品销量)
				// 将查询的结果相应的放入X轴和Y轴集合
				for (Goods goods : list) {
					X.add(goods.getGoodsName()); //X轴(商品名称
					Y.add(goods.getGoodsSum()); ///Y轴(商品销量)
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", X);
				map.put("y", Y);
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
	 * 获得index页面的初始化商品信息（目前仅仅有商品热卖以及商品是否是新品两组信息）
	 * @return
	 */
	@RequestMapping(value = "/indexGoodsMessage")
	public ResultValue selectGoodsHot() {
		ResultValue resultValue = new ResultValue();
		try {
			List<Goods> selectGoodsHot = this.goodsService.selectGoodsHot();
			List<Goods> selectGoodsNew = this.goodsService.selectGoodsNew();
			if(selectGoodsHot != null && selectGoodsNew!=null && selectGoodsHot.size() > 0 && selectGoodsNew.size() > 0) {
				resultValue.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("goodsHotList", selectGoodsHot);
				map.put("goodsNewList", selectGoodsNew);
				resultValue.setDataMap(map);
				return resultValue;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		resultValue.setCode(1);
		return resultValue;
	}
}
