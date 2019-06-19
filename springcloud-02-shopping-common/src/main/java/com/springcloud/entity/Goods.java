package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GOODS表中的实体类，用于保存表中的一行商品信息
 * @author Ya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements java.io.Serializable {
    /**
	 * 序列化编号
	 */
	private static final long serialVersionUID = -6373448256321508259L;
	/**
	 * 商品编号
	 */
	private Integer goodsId;
	/**
	 * 商品姓名
	 */
    private String goodsName;
    /**
     * 商品价格
     */
    private Double goodsPrices;
    /**
     * 商品数量
     */
    private Double goodsDiscount;
    /**
     * 	商品状态：0预售，1热卖中，2已下架
     */
    private Integer goodsStatus;
    /**
     * 	商品折扣价
     */
    private Integer goodsCount;
    /**
     * 	商品是否为新品：0是新品，1不是新品
     */
    private Integer goodsIsNew;
    /**
     *	 商品是否热卖 0热卖，1非热卖
     */
    private Integer goodsIsHot;
    /**
     * 	商品级别：0-4，5个级别
     */
    private Integer goodsLevel;
    /**
     * 	商品详情
     */
    private String goodsBrief;
    /**
     * 	商品细节
     */
    private String goodsDetails;
    /**
     * 	商品图片
     */
    private String goodsPhoto;
    /**
     * 	查询条件：一级类型编号
     */
    private Integer class1Id;
    /**
     * 	二级类别编号
     */
    private Integer class2Id;
    /**
     * 	查询条件：商品价格下限
     */
    private Double goodsPricesMin;
    /**
     * 	查询条件：商品价格上限
     */
    private Double goodsPricesMax;
    /**
     * 	商品销量：用于保存统计分组的结果
     */
    private Integer goodsSum;
}