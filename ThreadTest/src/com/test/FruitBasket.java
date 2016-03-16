package com.test;

import java.text.DecimalFormat;

public class FruitBasket {
	
	//篮子里最多放水果的数量
	private Fruit[]fruits=new Fruit[10];
	private int index=0;
	
	//是否为空
	public boolean isEmpty()
	{
		return index==0?true:false;
	}
	//是否已满
	public boolean isFull()
	{
		return index==fruits.length?true:false;
	}
	
	public synchronized void push(String name,Fruit fruit)
	{
		//如果已满则等待
		while(isFull())
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fruits[index++]=fruit;
		System.out.println(name+"向水果框中放入编号为"+fruit.getId()+"的"+fruit.getVariety());
		display();
		this.notify();	
	}
	
	public void display()
	{
		for (int i = 0; i < index; i++) {
			System.out.printf("%-10s", " NO:"+new DecimalFormat("00").format(fruits[i].getId())+fruits[i].getVariety()+" |");
		}
		for (int i = index; i < fruits.length; i++) {
			System.out.printf("%-10s"," ["+(i+1)+"] |");			
		}
		System.out.println();
	}
	
	public synchronized Fruit pop(String name)
	{
		while(isEmpty())
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Fruit fruit = fruits[--index];
		System.out.println(name+"从水果框中拿出编号为"+fruit.getId()+"的"+fruit.getVariety());
		display();
		this.notify();
		return fruit;
	}
}
