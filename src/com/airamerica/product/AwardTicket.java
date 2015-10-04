package com.airamerica.product;

import org.joda.time.DateTime;

import classes.Airport;

public class AwardTicket extends Ticket { 
	private double pointsPerMile;	
	
	public AwardTicket(String code, Airport depAirport, 	//Awards ticket constructor
			Airport arrAirport, DateTime depTime, DateTime arrTime, 
			String flightNo, String flightClass, String aircraftType,
			double pointsPerMile) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.pointsPerMile = pointsPerMile;
	}
	
}