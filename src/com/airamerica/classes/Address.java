package com.airamerica.classes;

public class Address {
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	
	//Address constructor
	public Address(String street, String city, String state, String zipcode, String country) {
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZipcode(zipcode);
		this.setCountry(country);
	}
	
	//street getter
	public String getStreet() {
		return street;
	}
	
	//street setter
	public void setStreet(String street) {
		this.street = street;
	}
	
	//city getter
	public String getCity() {
		return city;
	}
	
	//city setter
	public void setCity(String city) {
		this.city = city;
	}
	
	//state getter
	public String getState() {
		return state;
	}

	//state setter
	public void setState(String state) {
		this.state = state;
	}
	
	//zipcode getter
	public String getZipcode() {
		return zipcode;
	}

	//zipcode setter
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	//country getter
	public String getCountry() {
		return country;
	}

	//country setter
	public void setCountry(String country) {
		this.country = country;
	}
	
}
