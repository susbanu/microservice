package com.epam.generics.unit1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class GenericTest1 {

	public static void main(String[] args) {
		
		String[] namesArray = {"Name1", "Name2", "Name3"};
		addToArray(namesArray, "Name4");
		Stream.of(namesArray).forEach(System.out::println);
		

		List<String> listNames = new ArrayList<>();
		addToNames(listNames, "Name1");
		addToNames(listNames, "Name2");
		
		incorrectAddToNames(listNames, 10);
		
		// String s = listNames.get(2);
		// System.out.println("Number : "+s); // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
		
	}
	
	public static void addToNames(List<String> names, String name) {
		names.add(name);
	}
	
	public static void incorrectAddToNames(List names, Integer number) {
		names.add(number);
	}
	
	public static void addToArray(String[] array, String name) {
		array[0] = name;
	}
	
	public static void incorrectAddToArray(String[] array, Integer number) {
		// array[0] = number; // Runtime Error
	}
}
