package com.epam.generics.unit1;

import java.util.ArrayList;
import java.util.List;

public class GenericLowerBoundWildCard {

	public static void main(String[] args) {
		
		List<Integer> intNumbers = new ArrayList<>();
		intNumbers.add(1);
		intNumbers.add(3);
		intNumbers.add(2);
		//inCorrectPrintList(intNumbers, 1);
		
		List<Double> doubleNumber = new ArrayList<>();
		doubleNumber.add(1.0);
		doubleNumber.add(3.0);
		doubleNumber.add(2.0);
		//inCorrectPrintList(doubleNumber, 2);
		
		List<Number> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(5);
		correctPrintList(numbers, 6);
	}
	
	// Adding restrictions to only access certain types, so whatever type passed it extends Number.
	public static void inCorrectPrintList(List<? extends Number> numbers, Integer i) {
		numbers.forEach(System.out::println);
	    //numbers.add(i);  // We just accept it and read from it(No Write operation) Because It might be a integer, double, long	etc...
	}
	
	// This ? wildcard type is either Number or Object (Parent Type)
	public static void correctPrintList(List<? super Number> numbers, Integer i) {
	    numbers.add(i);
	    numbers.forEach(System.out::println);
	    // Integer value = numbers.get(1); // Read operation will not work till we provide down casting
	}
}
