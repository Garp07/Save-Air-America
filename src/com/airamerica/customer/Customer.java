package com.airamerica.customer;

import classes.Person;

abstract public class Customer {
	private String code;
	protected String type;
	private Person primaryContact;							
	private String name;
	private double airlineMiles;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Person getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAirlineMiles() {
		return airlineMiles;
	}

	public void setAirlineMiles(double airlineMiles) {
		this.airlineMiles = airlineMiles;
	}
	
	//Customer Constructor
	public Customer(String code, Person primaryContact, String name, double airlineMiles) {
		this.code = code;
		this.primaryContact = primaryContact;
		this.name = name;
		this.airlineMiles = airlineMiles;
	}
	
}
