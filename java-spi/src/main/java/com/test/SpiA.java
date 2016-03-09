package com.test;

public class SpiA implements Spi {
	static {
		Api.Register("a", SpiA.class);
	}

	@Override
	public void send(String msg) {
		System.out.println("SpiA£º" + msg);
	}

}