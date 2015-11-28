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
		
		this.subtotal = 30;
		this.taxes = 0.04 * seats.size() * flightClass.getCostPerMile() * getFlightDistance();
		this.total = subtotal + taxes;
	}
	
	public double getAwardsPoints() {
		double distance = this.getFlightDistance();
		double awardsPoints = distance  *this.pointsPerMile;
		return awardsPoints;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String itemDescription = String.format("Award Ticket(%s) %s to %s (%.1f miles)",
				flightClass.getType(), depAirport.getAirportCode(), arrAirport.getAirportCode(), getFlightDistance());
		
		sb.append(String.format("%-10s %-70s $%10.2f $%10.2f $%10.2f \n", code, itemDescription, subtotal, taxes, total));
		sb.append(String.format("%10s (%d units @ $%.1f reward miles/unit with $30 fee) \n", " ", seats.size(), flightClass.getCostPerMile() * getFlightDistance()));
		
		return sb.toString();
	}
	
//	public double getTotalFare() {
//		double totalFare = this.getBasefare()*(double)this.getSeats().size() + this.getTaxes();
//		return totalFare;
//	}
	
	/*Pay flat $30 redemption fee once per invoice regardless of number of tickets*/
}