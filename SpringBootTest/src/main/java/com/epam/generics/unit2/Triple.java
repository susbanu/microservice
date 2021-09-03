package com.epam.generics.unit2;

public class Triple<T, K, E> {

	private T value1;
	private K value2;
	private E value3;
	
	public Triple(T value1, K value2, E value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}
	
	public T getValue1() {
		return value1;
	}
	public void setValue1(T value1) {
		this.value1 = value1;
	}
	public K getValue2() {
		return value2;
	}
	public void setValue2(K value2) {
		this.value2 = value2;
	}
	public E getValue3() {
		return value3;
	}
	public void setValue3(E value3) {
		this.value3 = value3;
	}

}
