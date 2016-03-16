package com.test;

import java.util.Random;

public class Fruit {

	private int id;
	private static int number=0;
	private String variety;
	
	private String [] varietys="apple,pear,banana,grape".split(",");
	public Fruit() {
		this.variety=varietys[new Random().nextInt(4)];
		this.id=++number;
	}
	public int getId() {
		return id;
	}
	public String getVariety() {
		return variety;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
}
