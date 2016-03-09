package com.harvest.demo.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftPoolFactory<T> extends BasePooledObjectFactory<T> {
	/** 日志记录器 */
	public static final Logger logger = LoggerFactory
			.getLogger(ThriftPoolFactory.class);
	/** 服务的IP */
	private String serviceIP;
	/** 服务的端口 */
	private int servicePort;
	/** 超时设置 */
	private int timeOut;

	public ThriftPoolFactory() {
		// TODO Auto-generated constructor stub
	}

	public ThriftPoolFactory(String serviceIP, int servicePort,
			int timeOut) {
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeOut = timeOut;
	}
	
	@Override
	public T create() throws Exception {
		logger.info("create ~~~");
		TSocket socket = new TSocket(serviceIP,servicePort,timeOut);
		socket.open();
		return  (T) socket;
	}

	@Override
	public PooledObject<T> wrap(T obj) {
		logger.info("wrap ~~~");
		return new DefaultPooledObject<T>(obj);
	}

	public String getServiceIP() {
		return serviceIP;
	}

	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}

	public int getServicePort() {
		return servicePort;
	}

	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	
}
