package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * ORDER_DETAILS表对应的实体类，用于封装一行订单明细信息
 * @author Ya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements java.io.Serializable{
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = -8959059229768491234L;
	/**
	 * 商品细节编号
	 */
    private Integer orderDetailId;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 商品编号
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 交易价格
     */
    private Double transactionPrice;
    /**
     * 交易数量
     */
    private String transactionCount;
    /**
     * 	商品的实体
     */
    private Goods goods;
}