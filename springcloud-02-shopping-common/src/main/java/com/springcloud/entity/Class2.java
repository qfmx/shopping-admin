package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * class2表对应的实体类，用于保存表中一行二级类别信息
 * @author Ya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class2 implements java.io.Serializable{
    /**
	 * 序列化编号
	 */
	private static final long serialVersionUID = 3136150258242720333L;
	/**
	 * 类别二编号
	 */
	private Integer class2Id;
	/**
	 * 类别二名称
	 */
    private String class2Name;
    /**
     * 类别一编号
     */
    private Integer class1Id;
    /**
     * 备注
     */
    private String remarks;

}