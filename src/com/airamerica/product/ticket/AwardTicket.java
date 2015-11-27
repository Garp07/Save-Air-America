package com.airamerica.product.ticket;

import java.util.ArrayList;

import org.joda.time.DateTime;

import com.airamerica.airport.Airport;

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
			String flightNo, FlightClass flightClass, String aircraftType,
			double pointsPerMile, ArrayList<Seat> seats) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType, seats);
		this.pointsPerMile = pointsPerMile;
		this.type = "TA";
	}
	
	public double getAwardsPoints() {
		double distance = this.getFlightDistance();
		double awardsPoints = distance*this.pointsPerMile;
		return awardsPoints;
	}
	
//	public double getTotalFare() {
//		double totalFare = this.getBasefare()*(double)this.getSeats().size() + this.getTaxes();
//		return totalFare;
//	}
	
	/*Pay flat $30 redemption fee once per invoice regardless of number of tickets*/
}