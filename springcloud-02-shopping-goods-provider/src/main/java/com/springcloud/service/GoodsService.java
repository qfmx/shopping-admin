package com.springcloud.service;


import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Goods;
/**
 * 商品模型层的接口：用于定义商品模块的方法
 * @author Ya
 *
 */

public interface GoodsService {
	/**
	 * 	添加商品信息
	 * @param goods 商品信息
	 * @return 成功返回大于0的数，失败返回0
	 */
	public abstract Integer insert(Goods goods);
	
	/**
	 * 	查询满足条件的商品信息（具有分页功能）
	 * @param goods 商品信息
	 * @param pageNumber 页数
	 * @return 返回com.github.pagehelper.PageInfo<Goods>类型的实例,否则返回null
	 */
	public abstract PageInfo<Goods> select(Goods goods,Integer pageNumber);
	/**
	 * 更新商品GOODS表中指定商品编号的商品信息
	 * @param goods 更新条件
	 * @return 成功返回大于0的整数，否则返回小于等于0的整数
	 */
	public abstract Integer updateGoodsById(Goods goods);
	/**
	 * 修改指定编号的商品信息
	 * @param goods 需要修改的商品信息
	 * @return 成功返回大于0的整数 失败返回小于等于0的整数
	 */
	public abstract int updateGoodsByPrimaryKey(Goods goods);
	/**
	 * 查询销量前10的商品信息
	 * @return 成功返回java.util.List类型的实例，否则返回null
	 */
	public abstract List<Goods> selectGroupSum();
	public abstract List<Goods> selectGroup();
	/**
	 * 
	 * @return
	 */
	public abstract List<Goods> selectGoodsHot();
	/**
	 * 
	 * @return
	 */
	public abstract List<Goods> selectGoodsNew();
}
