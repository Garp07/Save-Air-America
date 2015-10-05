package com.airamerica.product.ticket;

import org.joda.time.DateTime;

import com.airamerica.airport.Airport;

public class StandardTicket extends Ticket {
	
	public StandardTicket(String code, Airport depAirport, Airport arrAirport, DateTime depTime, 
			DateTime arrTime, String flightNo, FlightClass flightClass, String aircraftType) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.type = "TS";
	}
	
}
