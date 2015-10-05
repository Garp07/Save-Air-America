package com.airamerica.product.ticket;

public class Economy extends FlightClass {
	//no special behavior
	
	public Economy() {
		super();
		this.costPerMile = 0.15;
		this.type = "EC";
	}
}
