package com.harvest.demo.factory;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThriftPooledObjectFactory implements PooledObjectFactory {

	/** 日志记录器 */
	public static final Logger logger = LoggerFactory
			.getLogger(ThriftPooledObjectFactory.class);
	/** 服务的IP */
	private String serviceIP;
	/** 服务的端口 */
	private int servicePort;
	/** 超时设置 */
	private int timeOut;

	public ThriftPooledObjectFactory() {
		// TODO Auto-generated constructor stub
	}

	public ThriftPooledObjectFactory(String serviceIP, int servicePort,
			int timeOut) {
		this.serviceIP = serviceIP;
		this.servicePort = servicePort;
		this.timeOut = timeOut;
	}

	@Override
	public void activateObject(PooledObject arg0) throws Exception {

	}

	@Override
	public void destroyObject(PooledObject p) throws Exception {
		if (p instanceof TSocket) {
			TSocket socket = (TSocket) p;
			if (socket.isOpen()) {
				socket.close();
			}
		}
	}

	@Override
	public PooledObject makeObject() throws Exception {
//		try {
//			TTransport transport = new TSocket(this.serviceIP,
//					this.servicePort, this.timeOut);
//			transport.open();
//			return (PooledObject) transport;
//		} catch (Exception e) {
//			logger.error("error ThriftPooledObjectFactory()", e);
//			throw new RuntimeException(e);
//		}
		
		 TSocket socket = new TSocket(serviceIP,servicePort,timeOut);
		 socket.open();
		 return (PooledObject) socket;
	}
	

	@Override
	public void passivateObject(PooledObject arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateObject(PooledObject p) {
		try {
			if (p instanceof TSocket) {
				TSocket thriftSocket = (TSocket) p;
				if (thriftSocket.isOpen()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
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
