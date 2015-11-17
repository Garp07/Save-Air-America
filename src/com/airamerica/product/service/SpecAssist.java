package com.airamerica.product.service;

import com.airamerica.person.Person;

public class SpecAssist extends Service {
	private String typeOfService;
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTypeOfService() {					
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {	
		this.typeOfService = typeOfService;
	}
	
	public SpecAssist(String code, String typeOfService, Person person) {
		super(code);
		this.typeOfService = typeOfService;
		this.type = "SS";
		this.quantity = 1;
		this.person = person;
	}
	
	public SpecAssist(String code, String typeOfService) {
		super(code);
		this.typeOfService = typeOfService;
		this.type = "SS";
		this.quantity = 1;
	}
	
	public double getServiceCost() {
		double cost = 0;
		return cost;
	}
	
}