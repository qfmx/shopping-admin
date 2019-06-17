package com.springcloud.dao;

import com.springcloud.entity.Goods;
import java.util.List;
/**
 * 商品的数据持久化层
 * @author Ya
 *
 */
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodsId);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer goodsId);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);
    /**
     * 更新商品信息
     * @param record
     * @return 成功返回大于0的数
     */
    public abstract int updateGoodsByPrimaryKey(Goods goods);
    /**
     * 	查询满足条件的商品信息
     * @param goods 查询条件
     * @return 成功返回java.util.List类型的实例，失败返回null
     */
    public abstract List<Goods> select(Goods goods);
    /**
     * 更新GOODS表中指定goods_id的商品信息
     * @param goods 需要修改的商品信息
     * @return 成功返回大于0的整数
     */
    public abstract Integer updateGoodsById(Goods goods);
    /**
     * 查询商品前10的销量
     * @return 成功返回java.util.List类型的实例，失败返回null
     */
    public abstract List<Goods> selectGoodsGroup();
}
