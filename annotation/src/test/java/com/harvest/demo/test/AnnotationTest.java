package com.harvest.demo.test;

import java.lang.reflect.*;

import com.ross.annotation.MyClassAnnotation;
import com.ross.annotation.MyConstructorAnnotation;
import com.ross.annotation.MyFieldAnnotation;
import com.ross.annotation.MyMethodAnnotation;

@MyClassAnnotation(uri = "com.ross.MySample", desc = "The class name")  
public class AnnotationTest {

	@MyFieldAnnotation(uri = "com.ross.MySample#id", desc = "The class field")
	public String id;

	@MyConstructorAnnotation(uri = "com.ross.MySample#MySample", desc = "The default constuctor")
	public AnnotationTest() {
	}

	@MyMethodAnnotation(uri = "com.ross.MySample#setId", desc = "The class method")
	public void setId(String id) {
		this.id = id;
	}

	public static void main(String[] args) throws SecurityException,
			NoSuchMethodException, NoSuchFieldException {
		AnnotationTest oMySample = new AnnotationTest();
		// get class annotation
		MyClassAnnotation oMyAnnotation = AnnotationTest.class
				.getAnnotation(MyClassAnnotation.class);
		System.out.println("Class's uri: " + oMyAnnotation.uri() + "; desc: "
				+ oMyAnnotation.desc());

		// get constructor annotation
		Constructor oConstructor = oMySample.getClass().getConstructor();
		MyConstructorAnnotation oMyConstructorAnnotation = (MyConstructorAnnotation) oConstructor
				.getAnnotation(MyConstructorAnnotation.class);
		System.out.println("Constructor's uri: "
				+ oMyConstructorAnnotation.uri() + "; desc: "
				+ oMyConstructorAnnotation.desc());

		// get method annotation
		Method oMethod = oMySample.getClass().getDeclaredMethod("setId",
				String.class);
		MyMethodAnnotation oMyMethodAnnotation = oMethod
				.getAnnotation(MyMethodAnnotation.class);
		System.out.println("Method's uri: " + oMyMethodAnnotation.uri()
				+ "; desc: " + oMyMethodAnnotation.desc());

		// get field annotation
		Field oField = oMySample.getClass().getDeclaredField("id");
		MyFieldAnnotation oMyFieldAnnotation = oField
				.getAnnotation(MyFieldAnnotation.class);
		System.out.println("Field's uri: " + oMyFieldAnnotation.uri()
				+ "; desc: " + oMyFieldAnnotation.desc());
	}
}