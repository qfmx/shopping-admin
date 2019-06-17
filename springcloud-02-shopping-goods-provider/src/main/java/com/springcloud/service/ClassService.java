package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;

public interface ClassService {

	public abstract List<Class2> selectByClass1Id(Integer class1Id);

	public abstract List<Class1> selectAllClass1();

}
