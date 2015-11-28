package com.airamerica.product.ticket;

import java.util.ArrayList;

import org.joda.time.DateTime;

import com.airamerica.airport.Airport;

public class StandardTicket extends Ticket {
	
	public StandardTicket(String code, Airport depAirport, Airport arrAirport, DateTime depTime, 
			DateTime arrTime, String flightNo, FlightClass flightClass, String aircraftType,
			ArrayList<Seat> seats) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType, seats);
		this.type = "TS";
		
		this.subtotal = flightClass.getCostPerMile() * seats.size() * getFlightDistance();
		this.taxes = 0.04 * subtotal;
		this.total = subtotal + taxes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String itemDescription = String.format("Standard Ticket(%s) %s to %s (%.1f miles)",
				flightClass.getType(), depAirport.getAirportCode(), arrAirport.getAirportCode(), getFlightDistance());
		
		sb.append(String.format("%-10s %-70s $%10.2f $%10.2f $%10.2f \n", code, itemDescription, subtotal, taxes, total));
		sb.append(String.format("%10s (%d units @ $%.2f/unit) \n"," ", seats.size(), flightClass.getCostPerMile() * getFlightDistance()));
		
		return sb.toString();
	}
//	public double getTotalFare() {
//		double totalFare = this.getBasefare()*(double)this.getSeats().size() + this.getTaxes();
//		return totalFare;
//	}
}
