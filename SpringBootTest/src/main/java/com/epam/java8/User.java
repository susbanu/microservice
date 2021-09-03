package com.epam.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class User {

	private String name;

	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		
		//Optional.ofNullable(null).get();
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "ONE");
		map.put("2", "Two");
		map.put("12", "Twelve");
		map.put("11", "Eleven");
		map.put("1", "One");
		
		map.forEach((k,v) -> System.out.println("Key :"+k+" Value :"+v));
	}
}
