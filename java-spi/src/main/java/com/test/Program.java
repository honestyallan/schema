package com.test;

public class Program {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		Class.forName("com.test.SpiA");

		Api api = new Api("a");
		api.Send("ถฮนโฮฐ");
	}
}