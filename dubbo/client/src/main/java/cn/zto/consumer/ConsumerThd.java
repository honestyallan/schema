package cn.zto.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.zto.service.IProcessData;

public class ConsumerThd {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationConsumer.xml" });
		context.start();
		IProcessData demoService = (IProcessData) context
				.getBean("demoService");
		System.out.println(demoService.hello("world"));
	}
}