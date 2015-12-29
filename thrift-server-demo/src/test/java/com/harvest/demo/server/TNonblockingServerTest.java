package com.harvest.demo.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.harvest.demo.server.impl.GroupMemberQueryServiceImpl;
import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IGroupMemberQueryService.Iface;

public class TNonblockingServerTest {

	public static final int SERVER_PORT = 8090;
	private static final Logger log = Logger.getLogger(TNonblockingServerTest.class);
	public void startServer() {
		try {
			log.info("IGroupMemberQueryService TNonblockingServerTest start ....");
			// 半同步半异步的服务端模型，需要指定为： TFramedTransport 数据传输的方式。
			TProcessor tprocessor = new IGroupMemberQueryService.Processor<Iface>(new GroupMemberQueryServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(
					SERVER_PORT);
			THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
			thhsArgs.processor(tprocessor);
			thhsArgs.transportFactory(new TFramedTransport.Factory());
			thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

			//半同步半异步的服务模型
			TServer server = new THsHaServer(thhsArgs);
			log.info("Starting the TNonblockingServerTest server...");
			
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
		TNonblockingServerTest server = new TNonblockingServerTest();
		server.startServer();
	}

}
