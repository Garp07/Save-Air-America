package src.com.airamerica.customer;

import src.com.airamerica.person.Person;

public class Corporate extends Customer {
	//get %12 discount on all products (not taxes), pay flat $40 per invoice
	
	//Corporate constructor
	public Corporate(String code, Person primaryContact, String name, double airlineMiles) {
		super(code, primaryContact, name, airlineMiles);
		this.type = "C";
	}
}
