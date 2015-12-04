package com.harvest.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.Student;

public class PeopleTest {

	@Test
	public void test() {  
	        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");  
//	        Student student1 = (Student) ctx.getBean("student1");  
//	        Student student2 = (Student) ctx.getBean("student2");  
//	        System.out.println("name: " +student1.getName()+" age :" + student1.getAge());  
//	        System.out.println("name: " +student2.getName()+" age :" + student2.getAge());  
	        
	        Student result = (Student)ctx.getBean("people");
	        System.out.println(result.getId());  
	        System.out.println(result.getName());  
	        System.out.println(result.getAge());
	    } 
}
