package com.dubbo.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerTest {

	public static void main(String[] args)  throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationProvider.xml" });
		context.start();
		System.out.println("按任意键退出");
		System.in.read();
	}
}
