package com.epam.java8;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Test {

	private static Employee[] arrayOfEmps = {
			new Employee(1, "Jeff Bezos", 100000.0), 
			new Employee(2, "Bill Gates", 200000.0), 
			new Employee(3, "Mark Zuckerberg", 300000.0),
			new Employee(4, "Bill Gates", 200000.0)
	};
	private static List<Employee> empList = Arrays.asList(arrayOfEmps);
	
	private static List<List<String>> namesNested = Arrays.asList( 
		      Arrays.asList("Jeff", "Bezos"), 
		      Arrays.asList("Bill", "Gates"), 
		      Arrays.asList("Mark", "Zuckerberg"));
	
	public static void main(String[] args) {
		
		Stream.of(arrayOfEmps);
		empList.stream();
		Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
		
		empList.stream().forEach((emp) -> System.out.println("Name :"+emp.getName()));
		
		Map<String, Long> empNames = empList.stream()
									.map(Employee::getName)
									.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		empNames.forEach((k,v) -> System.out.println("Key : "+ k +" Value : "+v));
		
		
		Double avgSalary = empList.stream()
				.map(emp -> emp.getSalary())
				.collect(Collectors.averagingDouble((salary) -> salary));
		System.out.println("Average Salary :"+avgSalary);
		
		Map<String, Double> empSalarySum = empList.stream()
			.collect(Collectors.groupingBy(Employee::getName, Collectors.summingDouble(Employee::getSalary)));
		empSalarySum.forEach((k,v) -> System.out.println("Employee Name : "+ k +" Salary sum : "+v));
		
		Optional<String> firstName = empList.stream()
			.filter(emp -> emp.getSalary() >= 200000.0)
			.map(emp -> emp.getName())
			.findFirst();
			//.collect(Collectors.toSet());
		System.out.println("First name orElse: "+ firstName.orElse("Sushant"));
		System.out.println("First name orElseGet : "+ firstName.orElseGet(() -> "Arjun"));
		
		List<String> s = namesNested.stream().map((names -> names.get(0))).collect(Collectors.toList());
		s.forEach(System.out::println);
		
		String flatMapList = namesNested.stream().flatMap((names -> names.stream())).collect(Collectors.joining(","));
		System.err.println(flatMapList);
		
		// This method exists mainly to support debugging in stream pipeline
		Stream.of("one", "two", "three", "four")
		  .filter(e -> e.length() > 3)
		  .peek(e -> System.out.println("Filtered value: " + e))
		  .map(String::toUpperCase)
		  .peek(e -> System.out.println("Mapped value: " + e))
		  .collect(Collectors.toList());
		
		// when we want to alter the inner state of an element. 
		// For example, let's say we want to convert all user's name to lowercase before printing them.
		Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
		// Alternative way of peek
		// userStream.map(u -> { u.setName(u.getName().toLowerCase()); return u.getName(); })
		userStream.peek(u -> u.setName(u.getName().toLowerCase()))
				  .map(u -> u.getName())
		  		  .forEach(System.out::println);
		
		empList.stream()
			.sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
			.collect(Collectors.toList());
		
		Optional<Employee> pratik = Optional.empty();
		Employee e  = pratik.orElse(new Employee(0, "Default", 0));
		e.getName();
	}
}
