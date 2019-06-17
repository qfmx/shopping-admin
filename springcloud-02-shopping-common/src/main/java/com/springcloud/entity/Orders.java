package com.springcloud.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * ORDERS表的实体类，用于封装ORDERS表的一行信息
 * @author Ya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements java.io.Serializable{
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 3737212235896633127L;
	/**
	 * Users
	 */
	private Users user;
	/**
	 * 订单编号
	 */
    private Integer orderId;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 收货人姓名
     */
    private String consigneeName;
    /**
     * 收货人电话
     */
    private String consigneeNumber;
    /**
     * 收货人地址
     */
    private String consigneeSite;
    /**
     * 下单时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;
    /**
     * 订单数量
     */
    private Double orderAmount;
    /**
     * 订单状态 0未发货1已发货2待收货3已完成
     */
    private Integer orderStatus;
    /**
     * 订单的起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDateMin;
    /**
     * 订单的终止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDateMax;
    /**
     * 查询条件中的起始月份
     */
    private String startMonth;
    /**
     * 查询条件中的终止月份
     */
    private String endMonth;
    /**
     * 统计结果中的订单月份
     */
    private String orderMonth;
    /**
     * 统计结果中的销售总额
     */
    private Double orderPrice;
}