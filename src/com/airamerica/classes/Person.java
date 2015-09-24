package com.airamerica.classes;

import java.util.ArrayList;

public class Person {
	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private ArrayList<String> emailAddress;
	
	//Person constructor - no phone number
	public Person(String personCode, String firstName, String lastName, Address address) {
		this.setPersonCode(personCode);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.emailAddress = new ArrayList<String>();
	}
	
	//Person constructor - w/ phone number
	public Person(String personCode, String firstName, String lastName, Address address, String phoneNumber) {
		this.setPersonCode(personCode);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
		this.emailAddress = new ArrayList<String>();
	}
	
	//set emailAddress list
	public void setEmails(ArrayList<String> emailAddress)
	{
		this.emailAddress = emailAddress;
	}
	
	//add individual email to emailAddress list
	public void addEmail(String emailAddress) {
		this.emailAddress.add(emailAddress);
	}
	
	//personCode getter
	public String getPersonCode() {
		return personCode;
	}
	
	//personCode setter
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	//firstName getter
	public String getFirstName() {
		return firstName;
	}
	
	//firstName setter
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	//lastName getter
	public String getLastName() {
		return lastName;
	}

	//lastName setter
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	//address getter
	public Address getAddress() {
		return address;
	}
	
	//address setter
	public void setAddress(Address address) {
		this.address = address;
	}

	//phoneNumber getter
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	//phoneNumber setter
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
