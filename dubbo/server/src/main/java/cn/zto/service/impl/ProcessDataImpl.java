package cn.zto.service.impl;

import cn.zto.service.IProcessData;

public class ProcessDataImpl implements IProcessData {
	@Override
	public String hello(String name) {
		System.out.println("123456server: "+ name);
		return "hello : " + name;
	}
}
