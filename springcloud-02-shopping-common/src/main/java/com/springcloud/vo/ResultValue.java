package com.springcloud.vo;

import java.util.Map;

import lombok.Data;

/**
 * 定义本项目中所有的Controller返回结果类型
 * @author hp
 *
 */
@Data
public class ResultValue implements java.io.Serializable{


	private static final long serialVersionUID = -7333058567132357663L;
	/**
	 * 设置当前返回结果状态：0表示成功，1表示失败
	 */
	private Integer code;
	/**
	 * 设置返回信息
	 */
	private String message;
	/**
	 * 设置返回的数据
	 */
	private Map<String,Object> dataMap;
}
