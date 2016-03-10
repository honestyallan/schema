package com.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {
	public static void main(String[] args) {
		Person person = new Person();
		person.setId(BigInteger.valueOf(123));
		person.setName("beanjoy");
		person.setAge(BigInteger.valueOf(26));
		
		try 
		{ 
			 File file = new File("C:\\person.xml");
			 
			 JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
			 // �������µķ�ʽ�õ�JAXBContext
			 // JAXBContext jaxbContext = JAXBContext.newInstance("com.test");

			 Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			 jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			 
			 // �����XML�ļ�
			 jaxbMarshaller.marshal(person, file);  
		     
			 // ��ӡ������̨
			 jaxbMarshaller.marshal(person, System.out);
		     
			 // �����XML�ַ���
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 jaxbMarshaller.marshal(person, baos);
			 String strXML = baos.toString("UTF-8");
			 System.out.print("XML=\r\n" + strXML);
			 
			 
			 Unmarshaller unjaxbMarshaller = jaxbContext.createUnmarshaller();
			 
			 // ��XML�ַ�����javabean
			 String strXML2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<person id=\"456\"><name>joybean</name><age>62</age></person>";
			 Person person2 = (Person)unjaxbMarshaller.unmarshal(new ByteArrayInputStream(
					 strXML2.getBytes("UTF-8")));
			 System.out.print("person2=" + person2.getId() + "," + person2.getName()
					 + "," + person2.getAge());
			 
			 // ��XML�ļ���javabean
			 File file1 = new File("C:\\person.xml");
			 Person person3 = (Person)unjaxbMarshaller.unmarshal(file1);
			 System.out.print("\r\nperson3=" + person3.getId() + "," + person3.getName()
					 + "," + person3.getAge());
		} 
		catch (JAXBException e) 
		{  
			e.printStackTrace();  
		}  
		catch (Exception e) 
		{  
			e.printStackTrace();  
		}
	}
}
