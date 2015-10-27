package src.com.airamerica.customer;

import src.com.airamerica.person.Person;

public class General extends Customer {
	//pay full price for everything (including taxes)
	
	//General constructor
	public General(String code, Person primaryContact, String name, double airlineMiles) {
		super(code, primaryContact, name, airlineMiles);
		this.type = "G";
	}
}
