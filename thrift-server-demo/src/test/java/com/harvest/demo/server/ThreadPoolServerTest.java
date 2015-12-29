package com.harvest.demo.server;

import org.apache.log4j.Logger;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.harvest.demo.server.impl.GroupMemberQueryServiceImpl;
import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IGroupMemberQueryService.Iface;

public class ThreadPoolServerTest {
	public static final int SERVER_PORT = 8090;
	private static final Logger log = Logger.getLogger(ThreadPoolServerTest.class);
	public void startServer() {
		try {
			log.info("IGroupMemberQueryService TThreadPoolServer start ....");

			TProcessor tprocessor = new IGroupMemberQueryService.Processor<Iface>(new GroupMemberQueryServiceImpl());
			TServerTransport serverTransport = new TServerSocket(SERVER_PORT);
			// 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
			// 客户端Client代码与ClientTest的一样，只要数据传输的协议一致即可
			TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
					serverTransport);
			ttpsArgs.processor(tprocessor);
			ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TThreadPoolServer(ttpsArgs);
			log.info("Starting the threadPool server...");
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
		ThreadPoolServerTest server = new ThreadPoolServerTest();
		server.startServer();
	}

}