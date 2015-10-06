package com.airamerica.product.ticket;

import org.joda.time.DateTime;

import com.airamerica.airport.Airport;

public class OffseasonTicket extends Ticket {
	private DateTime seasonStartDate;
	private DateTime seasonEndDate;
	private double rebate;							
	
	public DateTime getSeasonStartDate() {
		return seasonStartDate;
	}

	public void setSeasonStartDate(DateTime seasonStartDate) {
		this.seasonStartDate = seasonStartDate;
	}

	public DateTime getSeasonEndDate() {
		return seasonEndDate;
	}

	public void setSeasonEndDate(DateTime seasonEndDate) {
		this.seasonEndDate = seasonEndDate;
	}

	public double getRebate() {
		return rebate;
	}

	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	
	//OffseasonTicket constructor
	public OffseasonTicket(String code, DateTime seasonStartDate, DateTime seasonEndDate, 
			Airport depAirport, Airport arrAirport, DateTime depTime, DateTime arrTime, 
			String flightNo, FlightClass flightClass, String aircraftType, double rebate) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.rebate = rebate;
		this.type = "TO";
	}
}