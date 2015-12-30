package com.harvest.demo.client;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.harvest.demo.thrift.GroupMemberListResult;
import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IHelloService;

public class TMultiplexedClientTest {
	private static final Logger log = Logger.getLogger(TMultiplexedClientTest.class);
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;

	public void startClient() {
		log.info("startClient: ~~~~");
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			TProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol mp1 = new TMultiplexedProtocol(protocol,"IGroupMemberQueryService");
            IGroupMemberQueryService.Client client1 = new IGroupMemberQueryService.Client(mp1);
            TMultiplexedProtocol mp2 = new TMultiplexedProtocol(protocol,"IHelloService");
            IHelloService.Client client2 = new IHelloService.Client(mp2);
			transport.open();
			GroupMemberListResult result = client1.getAllGroupMembers(1l, 2l);
			log.info("Thrify client result msg =: " + result.getErr_msg());
			client2.sayHello("allan");
			log.info("say Hello : end " );
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
		TMultiplexedClientTest client = new TMultiplexedClientTest();
		client.startClient();
		System.in.read();
	}
}