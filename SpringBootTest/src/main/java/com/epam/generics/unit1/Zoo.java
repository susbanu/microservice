package com.epam.generics.unit1;

public class Zoo {

	public static void main(String[] args) {
		Cage<Monkey> cage1 = new Cage<>();
		cage1.setAnimal1(new Monkey());
		cage1.setAnimal2(new Monkey());
		Monkey monkey = cage1.getAnimal1();
		
		Cage<Lion> cage2 = new Cage<>();
		cage2.setAnimal1(new Lion());
		
		Cage<Monkey> cage3 = new Cage<>(new Monkey(), new Monkey()); //Provide type safety, Only Monkey can live in cage3
		Cage cage4 = new Cage(new Monkey(), new Lion()); //
		
		
		// Generic with Upper bound wild card
		// Cage<Animal> cage5 = new Cage<>(new Monkey(), new Lion());
		Lion lion1 = new Lion();
		lion1.setType("wild");
		Monkey monkey1 = new Monkey();
		monkey1.setType("Pet");
		
		Cage<Animal> cage5 = new Cage<>();
		cage5.setAnimal1(lion1);
		cage5.setAnimal2(monkey1);
		boolean isCompatible = cage5.isBothAnimalCompatibleInSameCage(lion1, monkey1);
		System.out.println("IsCompatible :"+isCompatible);
		
		// Generic with multiple type parameters
		cage5.feedAnimal();
		
		
		// Generic with static method
		boolean isAnimalCompatible = Cage.isCompatible(lion1, monkey);
		System.out.println("isAnimalCompatible :"+isAnimalCompatible);
	}
}
