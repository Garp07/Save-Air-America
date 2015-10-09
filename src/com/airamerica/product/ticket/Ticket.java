package src.com.airamerica.product.ticket;

import java.util.ArrayList;

import org.joda.time.DateTime;

import src.com.airamerica.airport.Airport;
import src.com.airamerica.product.Product;

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
			DateTime arrTime, String flightNo, FlightClass flightClass, String aircraftType) {
		super(code);
		this.depAirport = depAirport;
		this.arrAirport = arrAirport;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
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

	public double getBasefare() {
		double fareRate = this.flightClass.getCostPerMile();
		double flightDistance = this.getFlightDistance();
		double basefare = fareRate*flightDistance;
		return basefare;
	}

	public double getTaxes() {
		double federalExciseTax = this.getBasefare()*0.075;
		double flightSegmentTax = (double)this.getSeats().size()*4;
		double securityFee = (double)this.getSeats().size()*5.6;
		double facilityCharge = this.depAirport.getPassengerFacilityFee()*(double)this.getSeats().size();
		double taxes = federalExciseTax + flightSegmentTax + securityFee + facilityCharge;
		return taxes;
	}

	abstract double getTotalFare();
	
	
}
