package com.address.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(value = { "id" })
public class AddressDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private long id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public AddressDto() {
	}
	
	public AddressDto(String firstName, String lastName, String street, String city, String state, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "AddressDto [firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + "]";
	}

}
