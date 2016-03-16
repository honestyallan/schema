package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		FruitBasket fruitBasket=new FruitBasket();
		ExecutorService pool = Executors.newFixedThreadPool(6);
		for (int i = 1; i < 4; i++) {
			pool.execute(new Farmer("Å©·ò"+i,fruitBasket));
		}
		for (int i = 1; i < 4; i++) {
			pool.execute(new Child("Ð¡º¢"+i,fruitBasket));
		}
		
	}
}
