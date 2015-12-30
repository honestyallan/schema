package com.harvest.demo.server.impl;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.harvest.demo.thrift.IHelloService.Iface;

public class HelloServiceImpl implements Iface {

	private static final Logger log = Logger
			.getLogger(HelloServiceImpl.class);
	@Override
	public void sayHello(String arg0) throws TException {
		log.info(arg0 + " in Shenzhen");
	}
}
