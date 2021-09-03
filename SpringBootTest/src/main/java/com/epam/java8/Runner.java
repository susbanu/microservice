package com.epam.java8;

class BasePage {

	public String pageURL = "123";
	public String getpageURL() {
		return this.pageURL;
	}
	
	public BasePage(String pageURL) {
		this.pageURL = pageURL;
		System.out.println("Base page created");
	}
}

class HomePage extends BasePage {
	public String getpageURL() {
		this.pageURL = "456";
		return this.pageURL;
	}
	
	public HomePage() {
		super("");
		System.out.println("HomePage Created");
	}
}

public class Runner {
	
	public static void main(String[] args) {
		BasePage base = new HomePage();
		System.out.println(base.getpageURL());
	}
}