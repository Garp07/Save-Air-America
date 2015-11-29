package com.airamerica.product.ticket;

import com.airamerica.person.Person;

public class Seat {
	private String seatNumber;
	private Person person;
	private String idNumber;
	private int age;
	private String nationality;
	
	public String toStringSeatInfo() {
		return String.format("%-15s %-20s %-10s %-10s %n", " ", person, age, seatNumber);
	}
	
	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public Seat(String seatNumber, Person person, String idNumber, int age, String nationality) {
		this.seatNumber = seatNumber;
		this.person = person;
		this.idNumber = idNumber;
		this.age = age;
		this.nationality = nationality;
	}
	
	
}
