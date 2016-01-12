package com.harvest.demo;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.harvest.demo.factory.ThriftPooledObjectFactory;

public class ConnectionProviderImpl implements ConnectionProvider,
		InitializingBean, DisposableBean {

	public static final Logger logger = LoggerFactory
			.getLogger(ConnectionProviderImpl.class);

	
	/** 对象缓存池 */
	private GenericObjectPool objectPool = null;
	
	@Override
	public void destroy() throws Exception {
		try {
			objectPool.close();
		} catch (Exception e) {
			throw new RuntimeException("erorr destroy()", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 设置factory
		ThriftPooledObjectFactory thriftPooledObjectFactory = new ThriftPooledObjectFactory();

		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE);
		config.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE);
		config.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL);
		config.setMaxWaitMillis(GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS);
		config.setTestOnBorrow(GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW);
		config.setTestOnReturn(GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN);
		config.setTestWhileIdle(GenericObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE);
		// 对象池
		objectPool = new GenericObjectPool(
				thriftPooledObjectFactory, config);

	}

	@Override
	public TSocket getConnection() {
		try {
			TSocket socket = (TSocket) objectPool.borrowObject();
			return socket;
		} catch (Exception e) {
			throw new RuntimeException("error getConnection()", e);
		}
	}

	@Override
	public void returnCon(TSocket socket) {
		try {
			objectPool.returnObject(socket);
		} catch (Exception e) {
			throw new RuntimeException("error returnCon()", e);
		}
	}

}
