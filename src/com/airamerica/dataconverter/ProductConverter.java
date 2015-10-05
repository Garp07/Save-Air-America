package com.airamerica.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.airamerica.airport.Airport;
import com.airamerica.customer.Customer;
import com.airamerica.product.Product;
import com.airamerica.product.service.CheckedBaggage;
import com.airamerica.product.service.Insurance;
import com.airamerica.product.service.Refreshment;
import com.airamerica.product.service.SpecAssist;
import com.airamerica.product.ticket.AwardTicket;
import com.airamerica.product.ticket.BusinessClass;
import com.airamerica.product.ticket.Economy;
import com.airamerica.product.ticket.EconomyPremium;
import com.airamerica.product.ticket.FlightClass;
import com.airamerica.product.ticket.OffseasonTicket;
import com.airamerica.product.ticket.StandardTicket;
import com.airamerica.product.ticket.Ticket;

public class ProductConverter extends DataReader {
	private ArrayList<Airport> Airports;
	
	public void setAirports(ArrayList<Airport> Airports){
		this.Airports = Airports;
	}

	public ArrayList<Airport> getAirports() {
		return Airports;
	}
	
	//Constructor
	public ProductConverter(String inputFile, ArrayList<Airport> Airports) {										
		super(inputFile);
		this.Airports = Airports;
	}
	
	//parse product.dat file
	public ArrayList<Product> parseProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//First line is number of products
		int count = 0;
		try {
			count = Integer.parseInt(scanner.nextLine());	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < count; i++) {
			String line = scanner.nextLine();
			if(!line.trim().isEmpty()) {
				Product product = null;
				product = this.parseProduct(line);
				products.add(product);
			}
		}
		
		scanner.close();
		return products;
	}
	
	private Product parseProduct(String productString) {
		
		Product product = null;
		/*
		 * item order
		 * code; type; [type dependent]
		 */
		String tokens[] = productString.split(";");					
		String code = tokens[0].trim();
		String type = tokens[1].trim();
		
		switch(type) {
			case "TS":
				product = this.parseStandardTicket(tokens);
				break;
			case "TO":
				product = this.parseOffseasonTicket(tokens);
				break;
			case "TA":
				product = this.parseAwardTicket(tokens);
				break;
			case "SC":
				product = this.parseCheckedBaggage(tokens);
				break;
			case "SI":
				product = this.parseInsurance(tokens);
				break;
			case "SS":
				product = this.parseAssistanceService(tokens);
				break;
			case "SR":
				product = this.parseRefreshments(tokens);
				break;
			default:
				System.out.println("Invalid Product Type");
				break;
		}
		
		return product;
	}
	
	private FlightClass parseFlightClass(String flightClassString) {
		FlightClass flightClass = null;
		switch(flightClassString) {
			case "EC":
				flightClass = new Economy();
				break;
			case "BC":
				flightClass = new BusinessClass();
				break;
			case "EP":
				flightClass = new EconomyPremium();
				break;
			default:
				System.out.println("Invalid Flight Class");
				break;
		}
		
		return flightClass;
	}
	
	private Airport findAirport(String airportCode) {
		for(Airport a : Airports) {
			if(a.getAirportCode() == airportCode) {
				return a;
			}
		}
	}
	
	private Product parseStandardTicket(String[] tokens) {
		/*
		 * item order
		 * code; type; depAirportCode; arrAirportCode; depTime; arrTime; flightNo; 
		 * flightClass; aircraftType
		 */
		DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");
		
		String code = tokens[0];
		String depAirportCode = tokens[2];
		String arrAirportCode = tokens[3];									
		DateTime depTime = format.parseDateTime(tokens[4]);
		DateTime arrTime = format.parseDateTime(tokens[5]);
		String flightNo = tokens[6];	
		FlightClass flightClass = this.parseFlightClass(tokens[7]);
		String aircraftType = tokens[8];
		
		Airport depAirport = this.findAirport(depAirportCode);
		Airport arrAirport = this.findAirport(arrAirportCode);
		
		Product product = new StandardTicket(code, depAirport, arrAirport,
				depTime, arrTime, flightNo, flightClass, aircraftType);
		
		return product;
	}
	
	private Product parseOffseasonTicket(String[] tokens) {
		/*
		 * item order
		 * code; type; seasonsStartDate; seasonEndDate; depAirportCode; arrAirportCode; 
		 * depTime; arrTime; flightNo; flightClass; aircraftType; rebate
		 */
		DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");
		DateTimeFormatter formatSeason = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		String code = tokens[0];
		DateTime seasonStart = formatSeason.parseDateTime(tokens[2]);
		DateTime seasonEnd = formatSeason.parseDateTime(tokens[3]);
		String depAirportCode = tokens[4];
		String arrAirportCode = tokens[5];									
		DateTime depTime = format.parseDateTime(tokens[6]);
		DateTime arrTime = format.parseDateTime(tokens[7]);
		String flightNo = tokens[8];	
		FlightClass flightClass = this.parseFlightClass(tokens[9]);
		String aircraftType = tokens[10];
		
		double rebate = 0.0;
		try {
			rebate = Double.parseDouble(tokens[11]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Airport depAirport = this.findAirport(depAirportCode);
		Airport arrAirport = this.findAirport(arrAirportCode);
		
		Product product = new OffseasonTicket(code, seasonStart, seasonEnd, 
				depAirport, arrAirport,depTime, arrTime, flightNo, flightClass, 
				aircraftType, rebate);
		
		return product;
	}
	
	private Product parseAwardTicket(String[] tokens) {
		/*
		 * item order
		 * code; type; depAirportCode; arrAirportCode; depTime; arrTime; flightNo; 
		 * flightClass; aircraftType
		 */
		DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");
		
		String code = tokens[0];
		String depAirportCode = tokens[2];
		String arrAirportCode = tokens[3];									
		DateTime depTime = format.parseDateTime(tokens[4]);
		DateTime arrTime = format.parseDateTime(tokens[5]);
		String flightNo = tokens[6];	
		FlightClass flightClass = this.parseFlightClass(tokens[7]);
		String aircraftType = tokens[8];
		
		double pointsPerMile = 0.0;
		try {
			pointsPerMile = Double.parseDouble(tokens[9]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Airport depAirport = this.findAirport(depAirportCode);
		Airport arrAirport = this.findAirport(arrAirportCode);
		
		Product product = new AwardTicket(code, depAirport, arrAirport,
				depTime, arrTime, flightNo, flightClass, aircraftType,
				pointsPerMile);
		
		return product;
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
