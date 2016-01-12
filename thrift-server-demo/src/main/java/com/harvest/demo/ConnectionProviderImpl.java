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

	/** 服务的IP地址 */
    private String serviceIP;
    /** 服务的端口 */
    private int servicePort;
    /** 连接超时配置 */
    private int conTimeOut;
    /** 可以从缓存池中分配对象的最大数量 */
    private int maxActive = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;
    /** 缓存池中最大空闲对象数量 */
    private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
    /** 缓存池中最小空闲对象数量 */
    private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;
    /** 阻塞的最大数量 */
    private long maxWait = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;
    /** 从缓存池中分配对象，是否执行PoolableObjectFactory.validateObject方法 */
    private boolean testOnBorrow = GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW;
    private boolean testOnReturn = GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN;
    private boolean testWhileIdle = GenericObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE;
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
		logger.info("ConnectionProviderImpl start~~~~");
		// 设置factory
		ThriftPooledObjectFactory thriftPooledObjectFactory = new ThriftPooledObjectFactory(serviceIP,servicePort,conTimeOut);

		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(this.maxIdle);
		config.setMinIdle(this.minIdle);
		config.setMaxTotal(this.maxActive);
		config.setMaxWaitMillis(this.maxWait);
		config.setTestOnBorrow(this.testOnBorrow);
		config.setTestOnReturn(this.testOnReturn);
		config.setTestWhileIdle(this.testWhileIdle);
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

	public int getConTimeOut() {
		return conTimeOut;
	}

	public void setConTimeOut(int conTimeOut) {
		this.conTimeOut = conTimeOut;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	
	public GenericObjectPool getObjectPool() {
		return objectPool;
	}

	public void setObjectPool(GenericObjectPool objectPool) {
		this.objectPool = objectPool;
	}
}
