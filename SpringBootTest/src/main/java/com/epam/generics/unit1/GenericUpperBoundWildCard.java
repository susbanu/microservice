package com.epam.generics.unit1;

import java.util.ArrayList;
import java.util.List;

public class GenericUpperBoundWildCard {

	public static void main(String[] args) {
		
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(3);
		numbers.add(2);
		correctPrintList(numbers);
		
		List<Double> doubleNumber = new ArrayList<>();
		doubleNumber.add(1.0);
		doubleNumber.add(3.0);
		doubleNumber.add(2.0);
		correctPrintList(doubleNumber);
		
		
		List<String> names = new ArrayList<>();
		names.add("Raj");
		names.add("Ravi");
		// correctPrintList(names);
	}
	
	// Adding restrictions to only access certain types, so whatever type passed it extends Number.
	public static void correctPrintList(List<? extends Number> numbers) {
		numbers.forEach(System.out::println);
		// names.add(3);  // We just accept it and read from it(No Write operation) Because It might be a integer, double, long	etc...
	}
}
