package com.harvest.demo.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.harvest.demo.server.impl.GroupMemberQueryServiceImpl;
import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IGroupMemberQueryService.Iface;

public class NonblockingServerTest {
	public static final int SERVER_PORT = 8090;
	private static final Logger log = Logger.getLogger(NonblockingServerTest.class);
	public void startServer() {
		try {
			log.info("IGroupMemberQueryService TNonblockingServer start ....");
			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TProcessor tprocessor = new IGroupMemberQueryService.Processor<Iface>(new GroupMemberQueryServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(
					tnbSocketTransport);
			tnbArgs.processor(tprocessor);
			tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new TCompactProtocol.Factory());

			// 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
			TServer server = new TNonblockingServer(tnbArgs);
			log.info("Starting the TNonblockingServer server...");
			
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
		NonblockingServerTest server = new NonblockingServerTest();
		server.startServer();
	}

}
