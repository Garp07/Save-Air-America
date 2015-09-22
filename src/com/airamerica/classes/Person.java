package com.airamerica.classes;

import java.util.ArrayList;

public class Person {
	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private ArrayList<String> emailAddress;
	
	public Person(String personCode, String firstName, String lastName, Address address) {
		
	}
	
	public Person(String personCode, String firstName, String lastName, Address address, String phoneNumber) {
		
	}
	
	/**
	 * @return the personCode
	 */
	public String getPersonCode() {
		return personCode;
	}
	/**
	 * @param personCode the personCode to set
	 */
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	
}
