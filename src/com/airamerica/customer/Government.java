package src.com.airamerica.customer;

import src.com.airamerica.person.Person;

public class Government extends Customer {
	//pay full price for everything, no taxes
	
	//Government constructor
	public Government(String code, Person primaryContact, String name, double airlineMiles) {
		super(code, primaryContact, name, airlineMiles);
		this.type = "V";
	}
}
