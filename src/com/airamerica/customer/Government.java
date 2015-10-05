package com.airamerica.customer;

import classes.Person;


public class Government extends Customer {
	
	//Government constructor
	public Government(String code, Person primaryContact, String name, double airlineMiles) {
		super(code, primaryContact, name, airlineMiles);
		this.type = "V";
	}
}
