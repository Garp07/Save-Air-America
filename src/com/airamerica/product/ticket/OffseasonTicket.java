package com.airamerica.product.ticket;

import java.util.ArrayList;

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
			String flightNo, FlightClass flightClass, String aircraftType, double rebate,
			ArrayList<Seat> seats, DateTime travelDate) {
		super(code, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType, seats);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.type = "TO";
		this.travelDate = travelDate;
		
		if(travelDate.isBefore(seasonEndDate) && travelDate.isAfter(seasonStartDate)) {
			this.rebate = rebate;
			this.subtotal = (1-rebate) * flightClass.getCostPerMile() * seats.size() * getFlightDistance() + 20;
		} else {
			this.rebate = 0;
			this.subtotal = (1-rebate) * flightClass.getCostPerMile() * seats.size() * getFlightDistance() + 20;
		}
		this.taxes = 0.04 * subtotal;
		this.total = subtotal + taxes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String itemDescription = String.format("Offseason Ticket(%s) %s to %s (%.1f miles) %.2f%% off",
				flightClass.getType(), depAirport.getAirportCode(), arrAirport.getAirportCode(), getFlightDistance(), rebate * 100);
		
		sb.append(String.format("%-10s %-70s $%10.2f $%10.2f $%10.2f \n", code, itemDescription, subtotal, taxes, total));
		sb.append(String.format("%10s (%d units @ $%.2f/unit with $20 fee) \n"," ", seats.size(), (1-rebate) * flightClass.getCostPerMile() * getFlightDistance()));
		
		return sb.toString();
	}
//	public double getTotalFare() {
//		double discount = 0;
//		double serviceFee = 20;
//		if(this.travelDate.isAfter(this.seasonStartDate) && this.travelDate.isBefore(this.seasonEndDate)) {
//			discount = this.getBasefare()*this.rebate;
//		} 
//		double totalFare = (this.getBasefare()*(double)this.getSeats().size() - discount) + this.getTaxes() + serviceFee;
//		return totalFare;
//	}
//	
	
}
