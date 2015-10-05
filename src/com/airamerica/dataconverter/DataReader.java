package com.airamerica.dataconverter;

import com.airamerica.other.Address;

public class DataReader {
	protected String inputFile;

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	public DataReader(String inputFile) {
		this.inputFile = inputFile;
	}
	
	//parses an address string
	public Address parseAddress(String addressString) {
		/*
		 * item order
		 * street, city, state, zipcode, country
		 */
		
		String addressToken[] = addressString.split(",");
		String street = addressToken[0].trim();								
		String city = addressToken[1].trim();									
		String state = addressToken[2].trim();								
		String zipcode = addressToken[3].trim();								
		String country = addressToken[4].trim();	
		Address address = new Address(street, city, state, zipcode, country);
		return address;
	}
}
