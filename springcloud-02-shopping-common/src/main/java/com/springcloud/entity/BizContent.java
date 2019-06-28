package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BizContent implements java.io.Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3345093861153466728L;
	private String out_trade_no;
	private String product_code;
	private Float total_amount;
	private String subject;
	private String body;
	private String passback_params;
	private SysServiceProvide  extend_params;
}
