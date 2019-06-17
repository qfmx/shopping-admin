package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 封装类别一表的中的一行数据
 * @author Ya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class1 implements java.io.Serializable{
    /**
	 * 序列化编号
	 */
	private static final long serialVersionUID = -5094573667413124189L;
	/**
	 * 类别一编号
	 */
	private Integer class1Id;
	/**
	 * 类别一名称
	 */
    private String class1Name;
    /**
     * 类别一数量
     */
    private Integer class1Number;
    /**
     * 备注
     */
    private String remarks;

}