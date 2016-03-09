package com.test;

public class DatabaseSearch implements Search {

	@Override
	public String search(String keyword) {
		System.out.println("now use database search. keyword:" + keyword);
		return null;
	}

}
