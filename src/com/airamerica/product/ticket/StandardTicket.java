package com.airamerica.product.ticket;

import org.joda.time.DateTime;

import classes.Airport;

public class StandardTicket extends Ticket {
	
	public StandardTicket(String code, Airport depAirport, Airport arrAirport, DateTime depTime, 
			DateTime arrTime, String flightNo, String flightClass, String aircraftType) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.type = "TS";
	}
	
}
