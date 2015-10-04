package com.airamerica.product;

import org.joda.time.DateTime;

import classes.Airport;

public class OffseasonTicket extends Ticket {
	private DateTime seasonStartDate;
	private DateTime seasonEndDate;
	private double rebate;							
	
	public OffseasonTicket(String code, DateTime seasonStartDate,
			DateTime seasonEndDate, Airport depAirport, 
			Airport arrAirport, DateTime depTime, DateTime arrTime, 
			String flightNo, String flightClass, String aircraftType, 
			double rebate) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.rebate = rebate;
	}
}
