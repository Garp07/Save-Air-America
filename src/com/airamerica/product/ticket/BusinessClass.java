package com.airamerica.product.ticket;

public class BusinessClass extends FlightClass {
	//premium seats & service, 3 free bags, meals
	
	public BusinessClass() {
		super();
		this.costPerMile = 0.5;
		this.type = "BC";
	}
}
