package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GOODS表中的实体类，用于封装表的一行信息
 * @author QFMX
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
     * 	商品状态
     */
    private Integer goodsStatus;
    /**
     * 	商品折扣价
     */
    private Integer goodsCount;
    /**
     * 	商品是否为新品
     */
    private Integer goodsIsNew;
    /**
     *	 商品是否热卖 0热卖，1非热卖
     */
    private Integer goodsIsHot;
    /**
     * 	商品级别
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
     * 	商品类别
     */
    private String goodsPhoto;
    /**
     * 	一级类别
     */
    private Integer class1Id;
    /**
     * 	二级类别
     */
    private Integer class2Id;
    /**
     * 	商品下限
     */
    private Double goodsPricesMin;
    /**
     * 	商品上限
     */
    private Double goodsPricesMax;
    /**
     * 	销量总数
     */
    private Integer goodsSum;
}