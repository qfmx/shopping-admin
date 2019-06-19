package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * CLASS1表对应的实体类，用于保存表中一行一级类别信息
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
     * 类别一序号
     */
    private Integer class1Number;
    /**
     * 备注
     */
    private String remarks;

}