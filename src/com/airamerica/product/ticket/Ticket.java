package com.airamerica.product.ticket;

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

/*public class Tickets extends Product {					//This sub for standard tickets
	private Airport depAirport;							//These refer to airports, maybe need to be of class Airport? Yep *Sass*
	private Airport arrAirport;
	private String depTime;								//Times are strings of hh:mm
	private String arrTime;
	private String flightNo;							
	private String flightClass;							//Two letter code
	private String aircraftType;
	
	public Airport getDepAirport() {					//Departing airport code getter
		return depAirport;
	}

	public void setDepAirport(Airport depAirport) {  	//Departing airport code setter
		this.depAirport = depAirport;
	}

	public Airport getArrAirport() {					//Arriving airport code getter
		return arrAirport;
	}

	public void setArrAirport(Airport arrAirport) {		//Arriving airport code setter
		this.arrAirport = arrAirport;
	}

	public String getDepTime() {						//Departure time getter
		return depTime;
	}

	public void setDepTime(String depTime) {			//Departure time setter
		this.depTime = depTime;
	}

	public String getArrTime() {						//Arrival time getter
		return arrTime;
	}

	public void setArrTime(String arrTime) {			//Arrival time setter
		this.arrTime = arrTime;
	}

	public String getFlightNo() {						//Flight number getter
		return flightNo;
	}

	public void setFlightNo(String flightNo) {			//Flight number setter
		this.flightNo = flightNo;
	}

	public String getFlightClass() {					//Flight class getter
		return flightClass;
	}

	public void setFlightClass(String flightClass) {	//Flight class setter
		if(flightClass == "BC" || flightClass == "EC" || flightClass == "EP") {
			this.flightClass = flightClass;
		}
		else {
			System.out.println("That is not a valid flight class code.");
			System.out.println("Valid flight class codes: BC, EC, EP.");
		}
	}

	public String getAircraftType() {					//Aircraft type getter
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {	//Aircraft type setter
		this.aircraftType = aircraftType;
	}

	public Ticket(String code, String type, Airport depAirport, Airport arrAirport, String depTime,			//Ticket constructor
			String arrTime, String flightNo, String flightClass, String aircraftType) {
		super(code, type);
		this.depAirport = depAirport;
		this.arrAirport = arrAirport;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
	}

}*/
