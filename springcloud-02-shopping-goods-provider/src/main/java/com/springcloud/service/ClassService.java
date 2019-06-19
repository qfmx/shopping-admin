package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
/**
 * 用于定义一级类别与二级类别模块的接口
 * @author Ya
 *
 */
public interface ClassService {
	/**
	 * 根据一级类别编号查询相应的二级类别信息
	 * @param class1Id 一级类别编号
	 * @return 成功返回java.util.List类型的实例，否则返回null
	 */
	public abstract List<Class2> selectByClass1Id(Integer class1Id);
	/**
	 * 查询所有一级类别的信息
	 * @return 成功返回java.util.List类型的实例，否则返回null
	 */
	public abstract List<Class1> selectAllClass1();

}
