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
		
		this.subtotal = 0;
		this.taxes = 0;
		this.total = this.subtotal + this.taxes;
	}
	
//	public SpecAssist(String code, String typeOfService) {
//		super(code);
//		this.typeOfService = typeOfService;
//		this.type = "SS";
//		this.quantity = 1;
//		
//		this.subtotal = 0;
//		this.taxes = 0;
//		this.total = this.subtotal + this.taxes;
//	}
	
	public String toString() {
		String personString = person.toString();
		String itemDescription = String.format("Special Assistance for [%s] (%s)", personString, typeOfService);
		
		//code, item description, subtotal, tax, total
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-10s %-70s $%10.2f $%10.2f $%10.2f \n", code, itemDescription, subtotal, taxes, total));

		return sb.toString();
	}
	
}