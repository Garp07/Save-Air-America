package dataconverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Address;
import classes.Airport;

public class AirportConverter {
	private String inputFile;
	
	public String getInputFile() {														//InputFile getter
		return inputFile;
	}

	public void setInputFile(String inputFile) {										//InputFile setter
		this.inputFile = inputFile;
	}

	public AirportConverter(String inputFile) {											//Constructor
		this.inputFile = inputFile;
	}

	public ArrayList<Airport> parseAirports() {											//Method for reading airport file
		ArrayList<Airport> airports = new ArrayList<Airport>();							//Create an empty array list
		
		try{
			File f = new File(inputFile);
			Scanner s = new Scanner(f);
			int count = Integer.parseInt(s.nextLine());									//First line of file is an integer that = # of lines
			for(int i = 0; i < count; i++) {											//Loop through the rest of the lines
				String line = s.nextLine();
				if(!line.trim().isEmpty()) {											//If the line isn't blank
					Airport air = null;													//Create an instance of airport
					Address add = null; 												//Create an instance of address
					String tokens[] = line.split(";");									//Split the line up at ;'s
					String airportCode = tokens[0];										//First item should be the code
					String name = tokens[1];											//Second item should be the airport name
					String addressTokens[] = tokens[2].split(",");						//Third item is address, needs to be split further by commas
						String street = addressTokens[0];								//First item of address is street, then....
						String city = addressTokens[1];									//city...
						String state = addressTokens[2];								//state...
						String zipCode = addressTokens[3];								//zip code...
						String country = addressTokens[4];								//and finally country
					String latLongTokens[] = tokens[3].split(",");						//Next comes lats and longs which need to be split up as well
						int latDegs = Integer.parseInt(latLongTokens[0]);				//First is latitude in degrees...
						int latMins = Integer.parseInt(latLongTokens[1]);				//latitude in minutes....
						int lonDegs = Integer.parseInt(latLongTokens[2]);				//longitude in degrees...
						int lonMins = Integer.parseInt(latLongTokens[3]);				//and longitude in minutes
					double passengerFacilityFee = Double.parseDouble(tokens[4]);		//Final item on a line is the facility fee
					
					add = new Address(street, city, state, zipCode, country);			//Populate objects with attributes
					air = new Airport(airportCode, name, add, latDegs, latMins, 
							lonDegs, lonMins, passengerFacilityFee);
					
					airports.add(air);													//Add airport object to the array list
				}
			}
			s.close();																	//Close scanner
		} catch(Exception why) {														//Handle exceptions
			why.printStackTrace();
		}
		return airports;
	}
}
