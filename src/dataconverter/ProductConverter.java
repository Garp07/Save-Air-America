package dataconverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Airport;
import classes.Awards;
import classes.CheckedBaggage;
import classes.Insurance;
import classes.Offseason;
import classes.Product;
import classes.Refreshment;
import classes.SpecAssist;
import classes.Ticket;

public class ProductConverter {
	private String inputFile;
	private ArrayList<Airport> Airports;
	
	public void setAirports(ArrayList<Airport> Airports){
		this.Airports = Airports;
	}

	public String getInputFile() {													//Input file getter
		return inputFile;
	}

	public void setInputFile(String inputFile) {									//Input file setter
		this.inputFile = inputFile;
	}

	public ProductConverter(String inputFile, ArrayList<Airport> Airports) {										//Constructor
		this.inputFile = inputFile;
		this.Airports = Airports;
	}
	
	public ArrayList<Product> parseProducts() {										//Method for reading a products file
		ArrayList<Product> products = new ArrayList<Product>();						//Create an empty array list
		
		try{
			File f = new File(inputFile);
			Scanner s = new Scanner(f);
			int count = Integer.parseInt(s.nextLine());								//First line is # of products
			for(int i = 0; i < count; i++) {
				String line = s.nextLine();
				if(!line.trim().isEmpty()) {										//If line isn't blank
					Product thing;												//Create an empty product object
					String tokens[] = line.split(";");								//Split line at ;
					String code = tokens[0];										//First item is the product code
					String type = tokens[1];										//Product type
					if(type.equals("TS")) {												//Case for standard ticket product
						String depAirportCode = tokens[2];									//Departing airport code
						String arrAirportCode = tokens[3];									//Arriving airport code
						String depTime = tokens[4];										//Departing time
						String arrTime = tokens[5];										//Arriving time
						String flightNo = tokens[6];									//Flight number
						String flightClass = tokens[7];									//Flight class
						String aircraftType = tokens[8];								//Aircraft type
						
						Airport depAirport = null;
						Airport arrAirport = null;
						
						//must set airports ArrayList before this method will work
						for(Airport a : Airports) {
							if(a.getAirportCode().equals(depAirportCode)) {
								depAirport = a;
							}
						}
						
						for(Airport a : Airports) {
							if(a.getAirportCode().equals(arrAirportCode)) {
								arrAirport = a;
							}
						}
						
						thing = new Ticket(code, type, depAirport, arrAirport, depTime, 	//Standard ticket object
								arrTime, flightNo, flightClass, aircraftType);
					}
					else if(type.equals("TO")) {											//Case for offseason ticket product
						String seasonStart = tokens[2];									//Season start date
						String seasonEnd = tokens[3];									//Season end date
						String depAirportCode = tokens[4];									//Departing airport code
						String arrAirportCode = tokens[5];									//Arriving airport code
						String depTime = tokens[6];										//Departing time
						String arrTime = tokens[7];										//Arriving time
						String flightNo = tokens[8];									//Flight number
						String flightClass = tokens[9];									//Flight class
						String aircraftType = tokens[10];								//Aircraft type
						double rebate = Double.parseDouble(tokens[11]);					//Rebate
						
						Airport depAirport = null;
						Airport arrAirport = null;
						
						//must set airports ArrayList before this method will work
						for(Airport a : Airports) {
							if(a.getAirportCode().equals(depAirportCode)) {
								depAirport = a;
							}
						}
						
						for(Airport a : Airports) {
							if(a.getAirportCode().equals(arrAirportCode)) {
								arrAirport = a;
							}
						}
						
						thing = new Offseason(code, type, seasonStart, seasonEnd,			//Offseason ticket object
								depAirport, arrAirport, depTime, arrTime, flightNo,
								flightClass, aircraftType, rebate);
					}
					else if(type.equals("TA")) {											//Case for awards ticket product
						String depAirportCode = tokens[2];									//Departing airport code
						String arrAirportCode = tokens[3];									//Arriving airport code
						String depTime = tokens[4];										//Departing time
						String arrTime = tokens[5];										//Arriving time
						String flightNo = tokens[6];									//Flight number
						String flightClass = tokens[7];									//Flight class
						String aircraftType = tokens[8];								//Aircraft type
						double pointsPerMile = Double.parseDouble(tokens[9]);			//Points per mile
						
						Airport depAirport = null;
						Airport arrAirport = null;
						
						//must set airports ArrayList before this method will work
						for(Airport a : Airports) {
							if(a.getAirportCode().equals(depAirportCode)) {
								depAirport = a;
							}
						}
						
						for(Airport a : Airports) {
							if(a.getAirportCode().equals(arrAirportCode)) {
								arrAirport = a;
							}
						}
						
						thing = new Awards(code, type, depAirport, arrAirport, 				//Awards ticket object
								depTime, arrTime, flightNo, flightClass, aircraftType,	
								pointsPerMile);
					}
					else if(type.equals("SC")) {											//Case for checked baggage
						String ticketCode = tokens[2];									//Ticket code
						thing = new CheckedBaggage(code, type, ticketCode);					//Checked baggage object
					}
					else if(type.equals("SI")) {											//Case for insurance
						String name = tokens[2];										//Name
						String ageClass = tokens[3];									//Age class
						double costPerMile = Double.parseDouble(tokens[4]);				//Cost per mile
						thing = new Insurance(code, type, name, ageClass, costPerMile);		//Insurance object
					}
					else if(type.equals("SR")) {											//Case for refreshments
						String name = tokens[2];										//Name
						double cost = Double.parseDouble(tokens[3]);					//Cost
						thing = new Refreshment(code, type, name, cost);					//Refreshment object
					}
					else {															//Case for special assistance SS
						String typeOfService = tokens[2];								//Type of service
						thing = new SpecAssist(code, type, typeOfService);					//Special assistance object
					}
					
					products.add(thing);
				}
			}
			s.close();
		} catch(Exception darn) {
			darn.printStackTrace();
		}
		return products;
	}
}
