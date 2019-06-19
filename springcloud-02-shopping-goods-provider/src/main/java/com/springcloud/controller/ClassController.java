package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.ClassService;
import com.springcloud.vo.ResultValue;
/**
 * 一级类别与二级类别的控制器
 * @author Ya
 *
 */
@RestController
@RequestMapping("class")
public class ClassController {
	@Autowired
	private ClassService classService;
	/**
	 * 根据一级类别编号查询相应二级类别信息
	 * @param class1Id 一级类别编号
	 * @return
	 */
	@RequestMapping(value = "/selectByClass1Id")
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		ResultValue rv = new ResultValue();
		try {
			// 调用service相应的方法查询所有一级类别的信息，并保存查询的结果 NullPointerExcetion
			List<Class2> class2 = this.classService.selectByClass1Id(class1Id);
			// 如果查询成功
			if (class2 != null && class2.size() > 0) {
				// 设置结果的状态标记为0
				rv.setCode(0);
				// 创建Map集合
				Map<String, Object> map = new HashMap<>();
				// 将查询结果存入Map集合中
				map.put("class2", class2);
				// 将Map集合存入ResultValue对象中
				rv.setDataMap(map);
				// 返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品类别2信息查询失败");
		return rv;
	}

	@RequestMapping(value = "/selectAllClass1")
	public ResultValue selectAllClass1() {
		ResultValue rv = new ResultValue();
		try {
			// 调用service相应的方法查询相应二级类别的信息，并保存查询的结果
			List<Class1> class1 = this.classService.selectAllClass1();
			// 如果查询成功
			if (class1 != null && class1.size() > 0) {
				// 设置结果的状态标记为0
				rv.setCode(0);
				// 创建Map集合
				Map<String, Object> map = new HashMap<>();
				// 将查询结果存入Map集合中
				map.put("class1", class1);
				// 将Map集合存入ResultValue对象中
				rv.setDataMap(map);
				// 返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品类别1信息查询失败");
		return rv;
	}
}
