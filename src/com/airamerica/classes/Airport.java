package com.airamerica.classes;

public class Airport {
	private String airportCode;
	private String name;
	private Address address;
	private double latDegs;
	private double latMins;
	private double lonDegs;
	private double lonMins;
	private double passengerFacilityFee;
	
	//Airport constructor
	public Airport(String airportCode, String name, Address address, double latDegs,
			double latMins, double lonDegs, double lonMins, double passengerFacilityFee) {
		this.setAirportCode(airportCode);
		this.setName(name);
		this.setAddress(address);
		this.setLatDegs(latDegs);
		this.setLatMins(latMins);
		this.setLonDegs(lonDegs);
		this.setLonMins(lonMins);
		this.setPassengerFacilityFee(passengerFacilityFee);
	}
	
	
	//airportCode getter
	public String getAirportCode() {
		return airportCode;
	}

	//airportCode setter
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	
	//name getter
	public String getName() {
		return name;
	}

	//name setter
	public void setName(String name) {
		this.name = name;
	}
	
	//address getter
	public Address getAddress() {
		return address;
	}

	//address setter
	public void setAddress(Address address) {
		this.address = address;
	}
	
	//latDegs getter
	public double getLatDegs() {
		return latDegs;
	}
	
	//latDegs setter
	public void setLatDegs(double latDegs) {
		this.latDegs = latDegs;
	}
	
	//latMins getter
	public double getLatMins() {
		return latMins;
	}
	
	//latMins setter
	public void setLatMins(double latMins) {
		this.latMins = latMins;
	}
	
	//lonDegs getter
	public double getLonDegs() {
		return lonDegs;
	}

	//lonDegs setter
	public void setLonDegs(double lonDegs) {
		this.lonDegs = lonDegs;
	}
	
	//lonMins getter
	public double getLonMins() {
		return lonMins;
	}
	
	//lonMins setter
	public void setLonMins(double lonMins) {
		this.lonMins = lonMins;
	}
	
	//passengerFacilityFee getter
	public double getPassengerFacilityFee() {
		return passengerFacilityFee;
	}
	
	//passengerFacilityFee setter
	public void setPassengerFacilityFee(double passengerFacilityFee) {
		this.passengerFacilityFee = passengerFacilityFee;
	}
	
	
}
