package com.fancy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fancy.util.Email;
/**
 * -----------------------------------------
 * @文件: EmailTest.java
 * @作者: fancy
 * @邮箱: fancydeepin@yeah.net
 * @时间: 2012-6-11
 * @描述: Junit测试,运行将发送一封email
 * -----------------------------------------
 */
public class EmailTest{

	@Test
    public void testSendMail() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-smtp-mail.xml");
        Email mail = (Email)context.getBean("simpleMail");
        mail.sendMail("Spring SMTP Mail Subject", "Spring SMTP Mail Text12112", "chenli@smarti.cn");
        //mail.sendMail("标题", "内容", "收件人邮箱");
    }

}