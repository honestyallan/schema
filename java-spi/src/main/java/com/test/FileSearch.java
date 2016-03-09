package com.test;

public class FileSearch implements Search {

	@Override
	public String search(String keyword) {
		System.out.println("now use file system search. keyword:" + keyword);
		return null;
	}

}
