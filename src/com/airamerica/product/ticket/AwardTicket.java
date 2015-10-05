package com.airamerica.product.ticket;

import org.joda.time.DateTime;

import classes.Airport;

public class AwardTicket extends Ticket { 
	private double pointsPerMile;	
	
	public double getPointsPerMile() {
		return pointsPerMile;
	}

	public void setPointsPerMile(double pointsPerMile) {
		this.pointsPerMile = pointsPerMile;
	}
	
	//Awards ticket constructor
	public AwardTicket(String code, Airport depAirport, 	
			Airport arrAirport, DateTime depTime, DateTime arrTime, 
			String flightNo, String flightClass, String aircraftType,
			double pointsPerMile) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.pointsPerMile = pointsPerMile;
		this.type = "TA";
	}
	
}