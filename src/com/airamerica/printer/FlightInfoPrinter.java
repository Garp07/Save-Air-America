package com.airamerica.printer;

import java.util.ArrayList;

import com.airamerica.product.ticket.Seat;
import com.airamerica.product.ticket.Ticket;

public class FlightInfoPrinter {
	
	public void print(ArrayList<Ticket> tickets) {
		System.out.println("FLIGHT INFORMATION");
		System.out.printf("%-20s %-20s %-20s %-30s %-30s %-20s %n", 
				"Flight Date", "Flight No.", "Class", "Departing City and Time", "Arriving City and Time", "Aircraft");
		
		for(Ticket t : tickets) {
			String flightDate = t.getTravelDate().toString("MM-dd-yyyy");
			String flightNo = t.getFlightNo();
			String flightClass = t.getFlightClass().getType();
			String depCity = t.getDepAirport().getAddress().getCity() + ", " + t.getDepAirport().getAddress().getState();
			String depTime = t.getDepTime().toString("hh:mm");
			String depCode = t.getDepAirport().getAirportCode();
			String arrCity = t.getArrAirport().getAddress().getCity() + ", " + t.getArrAirport().getAddress().getState();
			String arrTime = t.getArrTime().toString("hh:mm");
			String arrCode = t.getArrAirport().getAirportCode();
			String aircraft = t.getAircraftType();
			System.out.printf("%-20s %-20s %-20s %-30s %-30s %-20s %n",
					flightDate, flightNo, flightClass, depCity, arrCity, aircraft);
			System.out.printf("%-20s %-20s %-20s (%-3s)%-25s (%-3s)%-8s %-20s %n",
					" ", " ", " ", depCode, depTime, arrCode, arrTime, " ");
			
			System.out.printf("%-15s %-20s %-10s %-10s %n", " ", "Passenger", "Age", "Seat No.");
			for(Seat s : t.getSeats()) {
				String passenger = s.getPerson().getLastName() + ", " + s.getPerson().getFirstName();
				String age = Integer.toString(s.getAge());
				String seatNo = s.getSeatNumber();
				System.out.printf("%-15s %-20s %-10s %-10s %n", " ", passenger, age, seatNo);
			}
			
			String note = t.getTicketNote();								//Notes are optional, implement conditional later
			System.out.printf("%-10s *%-50s %n", " ", note);
			//System.out.printf("%n");
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	
}
