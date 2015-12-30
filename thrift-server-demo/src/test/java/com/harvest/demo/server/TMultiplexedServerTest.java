package com.harvest.demo.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.harvest.demo.server.impl.GroupMemberQueryServiceImpl;
import com.harvest.demo.server.impl.HelloServiceImpl;
import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IHelloService;

public class TMultiplexedServerTest {

	public static final int SERVER_PORT = 8090;
	private static final Logger log = Logger.getLogger(TMultiplexedServerTest.class);
	public void startServer() {
		try {
			log.info("IGroupMemberQueryService TMultiplexedServerTest start ....");
			TMultiplexedProcessor  multiProcessor =new TMultiplexedProcessor();
			multiProcessor.registerProcessor("IGroupMemberQueryService", new IGroupMemberQueryService.Processor<IGroupMemberQueryService.Iface>(new GroupMemberQueryServiceImpl()));
			multiProcessor.registerProcessor("IHelloService", new IHelloService.Processor<IHelloService.Iface>(new HelloServiceImpl()));
			TServerTransport serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(multiProcessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			log.info("Starting the TMultiplexedServer server...");
			server.serve();
		} catch (Exception e) {
			log.info("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TMultiplexedServerTest server = new TMultiplexedServerTest();
		server.startServer();
	}
}
