package com.epam.generics.unit1;

public class Animal implements Eats, Runs {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public void run() {
		System.out.println("Animal run");
	}

	@Override
	public void eat() {
		System.out.println("Animal Eat");
	}
}
