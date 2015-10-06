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
}
