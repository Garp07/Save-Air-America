package src.com.airamerica.product.ticket;

import org.joda.time.DateTime;

import src.com.airamerica.airport.Airport;

public class StandardTicket extends Ticket {
	
	public StandardTicket(String code, Airport depAirport, Airport arrAirport, DateTime depTime, 
			DateTime arrTime, String flightNo, FlightClass flightClass, String aircraftType) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.type = "TS";
	}
	
	public double getTotalFare() {
		double totalFare = this.getBasefare() + this.getTaxes();
		return totalFare;
	}
}
