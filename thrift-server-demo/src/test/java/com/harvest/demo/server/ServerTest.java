package com.harvest.demo.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.harvest.demo.server.impl.GroupMemberQueryServiceImpl;
import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IGroupMemberQueryService.Iface;

public class ServerTest {

	public static final int SERVER_PORT = 8090;
	private static final Logger log = Logger.getLogger(ServerTest.class);
	public void startServer() {
		try {
			log.info("IGroupMemberQueryService TSimpleServer start ....");

			TProcessor tprocessor = new IGroupMemberQueryService.Processor<Iface>(new GroupMemberQueryServiceImpl());

			// 简单的单线程服务模型，一般用于测试
			TServerTransport serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// tArgs.protocolFactory(new TCompactProtocol.Factory());
			// tArgs.protocolFactory(new TJSONProtocol.Factory());
			
			//简单的单线程服务模型，一般用于测试。
			TServer server = new TSimpleServer(tArgs);
			log.info("Starting the simple server...");
			
			//使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式。
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
		ServerTest server = new ServerTest();
		server.startServer();
	}

}