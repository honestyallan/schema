package com.test;

import java.util.Random;

public class Child  implements Runnable{
	private String name;
	private FruitBasket fruitBasket;
	public Child(String name ,FruitBasket fruitBasket) {
		this.name=name;
		this.fruitBasket=fruitBasket;
	}
	@Override
	public void run() {
		while(true){
			fruitBasket.pop(name);
			try {
				Thread.sleep(new Random().nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
