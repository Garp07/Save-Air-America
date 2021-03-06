package com.airamerica.person;

import java.util.ArrayList;

import com.airamerica.address.Address;

public class Person {
	private String code;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private ArrayList<String> emailAddress;
	
	//Person constructor - no phone number
	public Person(String code, String firstName, String lastName, Address address) {
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailAddress = new ArrayList<String>();
	}
	
	//Person constructor - w/ phone number
	public Person(String code, String firstName, String lastName, Address address, String phoneNumber) {
		this.code = code;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = new ArrayList<String>();
	}
	
	//set emailAddress list
	public void setEmails(ArrayList<String> emailAddress)
	{
		this.emailAddress = emailAddress;
	}
	
	//add individual email to emailAddress list
	//..this should be a single string otherwise its the same behavior as the setEmails method
	public void addEmail(String email) {
		this.emailAddress.add(email);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		if(firstName.equalsIgnoreCase("online")) {
			return "ONLINE";
		} else {
			return lastName + ", " + firstName;
		}
	}
	
}
