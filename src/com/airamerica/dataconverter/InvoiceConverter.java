package com.airamerica.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.airamerica.customer.Customer;
import com.airamerica.invoice.Invoice;
import com.airamerica.person.Person;
import com.airamerica.product.Product;
import com.airamerica.product.service.CheckedBaggage;
import com.airamerica.product.service.Insurance;
import com.airamerica.product.service.Refreshment;
import com.airamerica.product.service.Service;
import com.airamerica.product.service.SpecAssist;
import com.airamerica.product.ticket.Seat;
import com.airamerica.product.ticket.Ticket;

public class InvoiceConverter extends DataReader {
	private ArrayList<Product> products;
	private ArrayList<Customer> customers;
	private ArrayList<Person> persons;
	
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	//constructor
	public InvoiceConverter(String inputFile, ArrayList<Product> products, ArrayList<Customer> customers,
			ArrayList<Person> persons) {
		super(inputFile);
		this.products = products;
		this.customers = customers;
		this.persons = persons;
	}
	
	public ArrayList<Invoice> parseInvoices() {
		/*
		 * item order
		 * code; customerCode; salespersonCode (or ONLINE); invoiceDate (yyyy-mm-dd); [product list]
		 */
		Scanner scanner = null;
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		
		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//First line is number of invoices
		int count = 0;
		try {
			count = Integer.parseInt(scanner.nextLine());	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < count; i++) {
			String line = scanner.nextLine();
			if(!line.trim().isEmpty()) {
				Invoice invoice = null;
				invoice = this.parseInvoice(line);
				invoices.add(invoice);
			}
		}
		
		scanner.close();
		return invoices;
	}
	
	private Customer findCustomer(String customerCode) {
		for(Customer c : customers) {
			if(c.getCode() == customerCode) {
				return c;
			}
		}
		return null;
	}
	
	private Person findPerson(String personCode) {
		for(Person p : persons) {
			if(p.getCode() == personCode) {
				return p;
			}
		}
		return null;
	}
	
	private Product findProduct(String productCode) {
		for(Product p : products) {
			if(p.getCode() == productCode) {
				return p;
			}
		}
		return null;
	}
	
	private Invoice parseInvoice(String invoiceString) {
		String tokens[] = invoiceString.split(";");
		
		String code = tokens[0];
		Customer customer = this.findCustomer(tokens[1]);
		Person person = this.findPerson(tokens[2]);
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime invoiceDate = format.parseDateTime(tokens[3]);
		
		ArrayList<Ticket> tickets = this.parseTicketData(tokens[4]);
		ArrayList<Service> services = this.parseServiceData(tokens[4]);
		Invoice invoice = new Invoice(code, customer, person, invoiceDate, tickets, services);
		return invoice;
	}
	
	private ArrayList<Service> parseServiceData(String productString) {
		ArrayList<Service> services = new ArrayList<Service>();
		String tokens[] = productString.split(",");
		
		for(int i=1; i<tokens.length; i++) {
			Product product = this.findProduct(tokens[i].split(":")[0]);
			int quantity = 0;
			switch(product.getType()) {
				case "SC":		//checked baggage
					/*
					 * item order
					 * code: noOfBags
					 */
					String baggageString[] = tokens[i].split(":");
					CheckedBaggage checkedBaggage = (CheckedBaggage)product;
					int noOfBags = Integer.parseInt(baggageString[1]);
					checkedBaggage.setNoOfBags(noOfBags);
					services.add(checkedBaggage);
					break;
					
				case "SI":		//insurance
					/*
					 * item order
					 * code: quantity: ticketCode
					 */
					String insuranceString[] = tokens[i].split(":");
					Insurance insurance = (Insurance)product;
					quantity = Integer.parseInt(insuranceString[1]);
					String productCode = insuranceString[2];
					Ticket ticket = (Ticket)this.findProduct(productCode);
					insurance.setQuantity(quantity);
					insurance.setTicket(ticket);
					services.add(insurance);
					break;
					
				case "SS":		//special assistance
					/*
					 * item order
					 * code: personCode
					 */
					String specAssistString[] = tokens[i].split(":");
					SpecAssist specAssist = (SpecAssist)product;
					String personCode = specAssistString[1];
					Person person = this.findPerson(personCode);
					specAssist.setPerson(person);
					services.add(specAssist);
					break;
					
				case "SR":		//refreshments
					/*
					 * item order
					 * code: quantity
					 */
					String refreshmentString[] = tokens[i].split(":");
					Refreshment refreshment = (Refreshment)product;
					quantity = Integer.parseInt(refreshmentString[1]);
					refreshment.setQuantity(quantity);
					services.add(refreshment);
					break;
				default:
					break;
			}
		}
		return services;
	}
	
	private ArrayList<Ticket> parseTicketData(String productString) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		String tokens[] = productString.split(",");
		
		for(int i=1; i<tokens.length; i++) {
			Product product = this.findProduct(tokens[i]);
			switch(product.getType()) {
				case "TI":
				case "TS":		//all tickets
				case "TA":
					String ticketString[] = tokens[i].split(":");
					Ticket ticket = (Ticket)product;
					this.modifyTicket(ticket, ticketString);
					tickets.add(ticket);
					break;
				default:
					break;
			}
		}
		return tickets;
	}
	
	private void modifyTicket(Ticket ticket, String[] s) {
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime travelDate = format.parseDateTime(s[1]);
		
		int numOfPas = Integer.parseInt(s[2]);
		
		ArrayList<Seat> seats = new ArrayList<Seat>();

		for(int i=0; i<numOfPas; i++) {
			String seatNumber = s[(5*i)+3];
			Person person = this.findPerson(s[(5*i)+4]);
			String idNumber = s[(5*i)+5];
			int age = Integer.parseInt(s[(5*i)+6]);
			String nationality = s[(5*i)+7];
			
			seats.add(new Seat(seatNumber, person, idNumber, age, nationality));
		}
		
		ticket.setTravelDate(travelDate);
		ticket.setSeats(seats);
		ticket.setTicketNote(s[s.length-1]);
		
	}
}
