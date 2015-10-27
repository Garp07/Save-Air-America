package src.com.airamerica.airport;

import src.com.airamerica.other.Address;

public class Airport {
	private String airportCode;
	private String name;
	private Address address;
	private double latitude;
	private double longitude;
	private double passengerFacilityFee;
	
	//Airport constructor
	public Airport(String airportCode, String name, Address address, int latDegs,
			int latMins, int lonDegs, int lonMins, double passengerFacilityFee) {
		this.setAirportCode(airportCode);
		this.setName(name);
		this.setAddress(address);
		this.setLatitude(latDegs + latMins / 60.0);				//typecast to double
		this.setLongitude(lonDegs + lonMins / 60.0);
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
	
	//latitude getter
	public double getLatitude() {
		return latitude;
	}
	
	//latitude setter
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	//longitude getter
	public double getLongitude() {
		return longitude;
	}

	//longitude setter
	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
