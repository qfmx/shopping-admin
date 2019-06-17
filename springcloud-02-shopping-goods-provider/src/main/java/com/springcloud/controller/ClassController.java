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

@RestController
@RequestMapping("class")
public class ClassController {
	@Autowired
	private ClassService classService;

	@RequestMapping(value = "/selectByClass1Id")
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		ResultValue rv = new ResultValue();
		try {
			List<Class2> class2 = this.classService.selectByClass1Id(class1Id);
			if (class2 != null && class2.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("class2", class2);
				rv.setDataMap(map);
			}
			return rv;
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
			List<Class1> class1 = this.classService.selectAllClass1();
			if (class1 != null && class1.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("class1", class1);
				rv.setDataMap(map);
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
