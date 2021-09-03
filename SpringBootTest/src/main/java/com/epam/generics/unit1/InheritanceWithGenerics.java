package com.epam.generics.unit1;

import java.util.ArrayList;
import java.util.List;

public class InheritanceWithGenerics {

	public static void main(String[] args) {
		
		List<String> names = new ArrayList<>();
		names.add("Raj");
		names.add("Ravi");
		// The concept of polymorphism, A Child class assign to parent class in not applicable in case of Geneics.
		// incorrectPrintList(names);
		correctPrintList(names);
		
		
		List<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(3);
		numbers.add(2);
		// The concept of polymorphism, A Child class assign to parent class in not applicable in case of Geneics.
		// incorrectPrintList(numbers);
		correctPrintList(numbers);
		
	}
	
	public static void incorrectPrintList(List<Object> names) {
		names.forEach(System.out::println);
	}
	
	// ? is wildcard, which is imply that an unknown specific type 
	public static void correctPrintList(List<?> names) {
		names.forEach(System.out::println);
		// Compiler accepts whatever type you pass using wildcard. 
		// We just accept it and read from it(No Write operation) Because It might be a number, date etc...
		// names.add("Sushant");
	}
}
