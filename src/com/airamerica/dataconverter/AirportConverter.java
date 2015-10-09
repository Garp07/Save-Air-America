package src.com.airamerica.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.com.airamerica.airport.Airport;
import src.com.airamerica.other.Address;

public class AirportConverter extends DataReader {
	
	//AirportConverter constructor
	public AirportConverter(String inputFile) {											
		super(inputFile);
	}

	//Parse in airport file
	public ArrayList<Airport> parseAirports() {							
		ArrayList<Airport> airports = new ArrayList<Airport>();
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//First line of file is an integer that = # of lines
		int count = 0;
		try {
			count = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < count; i++) {											
			String line = scanner.nextLine();
			if(!line.trim().isEmpty()) {										
				Airport airport = null;
				airport = this.parseAirport(line);
				airports.add(airport);													
			}
		}
		scanner.close();																	
		return airports;
	}
	
	private Airport parseAirport(String airportString) {
		/*
		 * item order
		 * airportCode; name; address; latitude/longitude; passengerFacilityFee
		 */
		String tokens[] = airportString.split(";");
		String airportCode = tokens[0].trim();
		String name = tokens[1].trim();
		Address address = this.parseAddress(tokens[2]);
		
			/*
			 * item order
			 * latDegs, latMins, lonDegs, lonMins
			 */
			String latLongTokens[] = tokens[3].split(",");
			int latDegs = 0; 	
			int latMins = 0; 			
			int lonDegs = 0;				
			int lonMins = 0; 
			try {
				latDegs = Integer.parseInt(latLongTokens[0]);				
				latMins = Integer.parseInt(latLongTokens[1]);				
				lonDegs = Integer.parseInt(latLongTokens[2]);				
				lonMins = Integer.parseInt(latLongTokens[3]);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		double passengerFacilityFee = 0.0;
		try {
			 passengerFacilityFee = Double.parseDouble(tokens[4]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Airport airport = new Airport(airportCode, name, address, latDegs, latMins, 
						lonDegs, lonMins, passengerFacilityFee);
		
		return airport;
	}
	
}
