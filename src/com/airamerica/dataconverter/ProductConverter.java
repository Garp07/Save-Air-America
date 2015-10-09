package src.com.airamerica.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import src.com.airamerica.airport.Airport;
import src.com.airamerica.product.Product;
import src.com.airamerica.product.service.CheckedBaggage;
import src.com.airamerica.product.service.Insurance;
import src.com.airamerica.product.service.Refreshment;
import src.com.airamerica.product.service.SpecAssist;
import src.com.airamerica.product.ticket.AwardTicket;
import src.com.airamerica.product.ticket.BusinessClass;
import src.com.airamerica.product.ticket.Economy;
import src.com.airamerica.product.ticket.EconomyPremium;
import src.com.airamerica.product.ticket.FlightClass;
import src.com.airamerica.product.ticket.OffseasonTicket;
import src.com.airamerica.product.ticket.StandardTicket;
import src.com.airamerica.product.ticket.Ticket;

public class ProductConverter extends DataReader {
	private ArrayList<Airport> airports;
	private ArrayList<Ticket> tickets;
	
	public void setAirports(ArrayList<Airport> airports){
		this.airports = airports;
	}

	public ArrayList<Airport> getAirports() {
		return airports;
	}
	
	//Constructor
	public ProductConverter(String inputFile, ArrayList<Airport> airports, ArrayList<Ticket> tickets) {										
		super(inputFile);
		this.airports = airports;
		this.tickets = tickets;
	}
	
	public ProductConverter(String inputFile, ArrayList<Airport> airports) {
		super(inputFile);
		this.airports = airports;
		this.tickets = new ArrayList<Ticket>();
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
	
	public ArrayList<Ticket> parseTickets() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
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
				Ticket ticket = null;
				ticket = this.parseTicket(line);
				if(ticket != null) {
					tickets.add(ticket);
				}
			}
		}
		
		scanner.close();
		return tickets;
	}
	
	private Ticket parseTicket(String productString) {
		Product product = null;
		/*
		 * item order
		 * code; type; [type dependent]
		 */
		String tokens[] = productString.split(";");
		
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
			default:
				break;
		}
		
		return ((Ticket) product);
	}
	
	private Product parseProduct(String productString) {
		
		Product product = null;
		/*
		 * item order
		 * code; type; [type dependent]
		 */
		String tokens[] = productString.split(";");
		
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
		for(Airport a : airports) {
			if(a.getAirportCode().equals(airportCode)) {
				return a;
			}
		}
		return null;
	}
	
	private Product parseStandardTicket(String[] tokens) {
		/*
		 * item order
		 * code; type; depAirportCode; arrAirportCode; depTime; arrTime; flightNo; 
		 * flightClass; aircraftType
		 */
		DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");
		
		String code = tokens[0].trim();
		String depAirportCode = tokens[2].trim();
		String arrAirportCode = tokens[3].trim();									
		DateTime depTime = format.parseDateTime(tokens[4].trim());
		DateTime arrTime = format.parseDateTime(tokens[5].trim());
		String flightNo = tokens[6].trim();	
		FlightClass flightClass = this.parseFlightClass(tokens[7].trim());
		String aircraftType = tokens[8].trim();
		
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
		
		String code = tokens[0].trim();
		DateTime seasonStart = formatSeason.parseDateTime(tokens[2].trim());
		DateTime seasonEnd = formatSeason.parseDateTime(tokens[3].trim());
		String depAirportCode = tokens[4].trim();
		String arrAirportCode = tokens[5].trim();									
		DateTime depTime = format.parseDateTime(tokens[6].trim());
		DateTime arrTime = format.parseDateTime(tokens[7].trim());
		String flightNo = tokens[8].trim();	
		FlightClass flightClass = this.parseFlightClass(tokens[9].trim());
		String aircraftType = tokens[10].trim();
		
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
		
		String code = tokens[0].trim();
		String depAirportCode = tokens[2].trim();
		String arrAirportCode = tokens[3].trim();									
		DateTime depTime = format.parseDateTime(tokens[4].trim());
		DateTime arrTime = format.parseDateTime(tokens[5].trim());
		String flightNo = tokens[6].trim();	
		FlightClass flightClass = this.parseFlightClass(tokens[7].trim());
		String aircraftType = tokens[8].trim();
		
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
	
	private Ticket findTicket(String ticketCode) {
		for(Ticket t : tickets) {
			if(t.getCode() == ticketCode) {
				return t;
			}
		}
		return null;
	}
	
	private Product parseCheckedBaggage(String[] tokens) {
		String code = tokens[0].trim();
		Ticket ticket = this.findTicket(tokens[2].trim());
		
		Product product = new CheckedBaggage(code, ticket);
		return product;
	}
	
	private Product parseInsurance(String[] tokens) {
		String code = tokens[0].trim();
		String name = tokens[2].trim();
		String ageClass = tokens[3].trim();
		
		double costPerMile = 0.0;
		try {
			costPerMile = Double.parseDouble(tokens[4]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Product product = new Insurance(code, name, ageClass, costPerMile);
		return product;
	}
		
	private Product parseRefreshments(String[] tokens) {
		String code = tokens[0].trim();
		String name = tokens[2].trim();
		
		double cost = 0.0;
		try {
			cost = Double.parseDouble(tokens[3]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Product product = new Refreshment(code, name, cost);
		return product;
	}
	
	private Product parseAssistanceService(String[] tokens) {
		String code = tokens[0].trim();
		String typeOfService = tokens[2].trim();
		
		Product product = new SpecAssist(code, typeOfService);
		return product;
	}
}
