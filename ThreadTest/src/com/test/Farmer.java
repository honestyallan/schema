package com.test;

import java.util.Random;

public class Farmer implements Runnable {

	String name;
	private FruitBasket fruitBasket;
	public Farmer(String name,FruitBasket fruitBasket) {
		this.name=name;
		this.fruitBasket=fruitBasket;
	}
	@Override
	public void run() {
		while(true)
		{
			fruitBasket.push(name, new Fruit());
			try {
				Thread.sleep(new Random().nextInt(2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
