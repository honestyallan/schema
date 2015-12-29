package com.harvest.demo.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import com.harvest.demo.thrift.IGroupMemberQueryService;
import com.harvest.demo.thrift.IGroupMemberQueryService.AsyncClient.getAllGroupMembers_call;

public class NonblockingAsynClientTest {
	private static final Logger log = Logger.getLogger(NonblockingAsynClientTest.class);
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;

	public void startClient() {
		log.info("startClient: ~~~~");
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP,
					SERVER_PORT, TIMEOUT);

			TProtocolFactory tprotocol = new TCompactProtocol.Factory();
			IGroupMemberQueryService.AsyncClient asyncClient = new IGroupMemberQueryService.AsyncClient(
					tprotocol, clientManager, transport);
			
			System.out.println("Client start .....");
			//异步客户端，使用非阻塞式IO的服务端。NonblockingServerTest
			CountDownLatch latch = new CountDownLatch(1);
			AsynCallback callBack = new AsynCallback(latch);
			System.out.println("call method getAllGroupMembers start ...");
			asyncClient.getAllGroupMembers(1l, 2l, callBack);
			System.out.println("call method getAllGroupMembers .... end");
			boolean wait = latch.await(30, TimeUnit.SECONDS);
			System.out.println("latch.await =:" + wait);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("startClient end.");
	}
	
	public class AsynCallback implements AsyncMethodCallback<getAllGroupMembers_call> {
		private CountDownLatch latch;

		public AsynCallback(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void onComplete(getAllGroupMembers_call response) {
			System.out.println("onComplete");
			try {
				// Thread.sleep(1000L * 1);
				System.out.println("AsynCall result =:"
						+ response.getResult().getErr_msg());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}

		@Override
		public void onError(Exception exception) {
			System.out.println("onError :" + exception.getMessage());
			latch.countDown();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NonblockingAsynClientTest client = new NonblockingAsynClientTest();
		client.startClient();
		System.in.read();
	}
}
