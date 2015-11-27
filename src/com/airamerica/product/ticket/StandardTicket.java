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
		
		this.subtotal = flightClass.getCostPerMile() * seats.size();
		this.taxes = 0.04 * subtotal;
		this.total = subtotal + taxes;
	}
	
//	public double getTotalFare() {
//		double totalFare = this.getBasefare()*(double)this.getSeats().size() + this.getTaxes();
//		return totalFare;
//	}
}
