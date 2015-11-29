package com.airamerica.product.ticket;

import java.util.ArrayList;

import org.joda.time.DateTime;

import com.airamerica.airport.Airport;
import com.airamerica.product.Product;

abstract public class Ticket extends Product {
	protected Airport depAirport;
	protected Airport arrAirport;
	protected DateTime depTime;
	protected DateTime arrTime;
	protected String flightNo;
	protected FlightClass flightClass;
	protected String aircraftType;
	
	protected DateTime travelDate;
	protected ArrayList<Seat> seats;
	protected String ticketNote;
	
	protected double subtotal;
	protected double taxes;
	protected double total;
	
	public String toStringFlightInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%-20s %-20s %-20s %-30s %-30s %-20s %n",
				travelDate, flightNo, flightClass.getType(), depAirport.getAddress().getCityState(), arrAirport.getAddress().getCityState(), aircraftType));
		
		sb.append(String.format("%-20s %-20s %-20s (%-3s)%-25s (%-3s)%-8s %-20s %n",
				" ", " ", " ", depAirport.getAirportCode(), depTime, arrAirport.getAirportCode(), arrTime, " "));
		
		// seat info
		sb.append(String.format("%-15s %-20s %-10s %-10s %n", " ", "Passenger", "Age", "Seat No."));
		for(Seat s : seats) {
			sb.append(s.toStringSeatInfo());
		}
	
		//Notes are optional, implement conditional later
		sb.append(String.format("%-10s *%-50s %n", " ", ticketNote));
		
		sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		
		return sb.toString();
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public double getTaxes() {
		return taxes;
	}
	
	public double getTotal() {
		return total;
	}
	
	public Airport getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(Airport depAirport) {
		this.depAirport = depAirport;
	}

	public Airport getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(Airport arrAirport) {
		this.arrAirport = arrAirport;
	}

	public DateTime getDepTime() {
		return depTime;
	}

	public void setDepTime(DateTime depTime) {
		this.depTime = depTime;
	}

	public DateTime getArrTime() {
		return arrTime;
	}

	public void setArrTime(DateTime arrTime) {
		this.arrTime = arrTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public DateTime getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(DateTime travelDate) {
		this.travelDate = travelDate;
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public String getTicketNote() {
		return ticketNote;
	}

	public void setTicketNote(String ticketNote) {
		this.ticketNote = ticketNote;
	}

	//Ticket Constructor
	public Ticket(String code, Airport depAirport, Airport arrAirport, DateTime depTime, 
			DateTime arrTime, String flightNo, FlightClass flightClass, String aircraftType, 
			ArrayList<Seat> seats) {
		super(code);
		this.depAirport = depAirport;
		this.arrAirport = arrAirport;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
		
		this.seats = seats;
	}
	
	public double getFlightDistance() {
		double latDiff = Math.toRadians(arrAirport.getLatitude() - depAirport.getLatitude());
		double longDiff = Math.toRadians(arrAirport.getLongitude() - depAirport.getLongitude());
		double latDep = Math.toRadians(depAirport.getLatitude());
		double latArr = Math.toRadians(arrAirport.getLatitude());
		double radiusEarth = 3959.87;
		
		double a = Math.pow(Math.sin(latDiff/2), 2) + Math.cos(latDep)*Math.cos(latArr)*Math.pow(Math.sin(longDiff/2), 2);		//Haversine formula
		double c = 2*Math.asin(Math.sqrt(a));
		return c*radiusEarth;
	}
	
	@Override
	public abstract String toString();

//	public double getBasefare() {
//		double fareRate = this.flightClass.getCostPerMile();
//		double flightDistance = this.getFlightDistance();
//		double basefare = fareRate*flightDistance;
//		return basefare;
//	}

//	public double getTaxes() {
//		double federalExciseTax = this.getBasefare()*(double)this.getSeats().size()*0.075;
//		double flightSegmentTax = (double)this.getSeats().size()*4;
//		double securityFee = (double)this.getSeats().size()*5.6;
//		double facilityCharge = this.depAirport.getPassengerFacilityFee()*(double)this.getSeats().size();
//		double taxes = federalExciseTax + flightSegmentTax + securityFee + facilityCharge;
//		return taxes;
//	}

//	public abstract double getTotalFare();
	
	
}
