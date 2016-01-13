package com.harvest.demo.test.client;


import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.harvest.demo.server.impl.manager.ConnectionManager;
import com.harvest.demo.thrift.GroupMemberListResult;
import com.harvest.demo.thrift.IGroupMemberQueryService;

public class PoolClientTest {

	Logger log = LoggerFactory.getLogger(PoolClientTest.class);
	private ConnectionManager connectionManager;
	
	public ConnectionManager getConnectionManager() {
		log.info("getConnectionManager = " );
		return connectionManager;
	}

	public void setConnectionManager(ConnectionManager connectionManager) {
		log.info("setConnectionManager = " + connectionManager);
		this.connectionManager = connectionManager;
	}
	public void startClient() {
		log.info("startClient: ~~~~");
		
		TTransport transport = null;
		try {
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(this.connectionManager.getSocket());
			IGroupMemberQueryService.Client client = new IGroupMemberQueryService.Client(
					protocol);
			transport.open();
			GroupMemberListResult result = client.getAllGroupMembers(1l, 2l);
			log.info("Thrify client result msg =: " + result.getErr_msg());
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/resources/spring-thrift.xml");
//		PoolClientTest client = new PoolClientTest();
		PoolClientTest client = (PoolClientTest)context.getBean("client");
		client.startClient();
		System.in.read();
	}
}
