package com.airamerica.invoice;

import java.util.ArrayList;
import java.util.Comparator;

import org.joda.time.DateTime;

import com.airamerica.customer.Customer;
import com.airamerica.person.Person;
import com.airamerica.product.service.Refreshment;
import com.airamerica.product.service.Service;
import com.airamerica.product.ticket.Ticket;
import com.airamerica.utils.StandardUtils;

public class Invoice {
	
	protected String code;
	protected Customer customer;
	protected Person person;
	protected DateTime invoiceDate;
	protected ArrayList<Ticket> tickets;
	protected ArrayList<Service> services;
	protected String PNR;
	
	private double subtotal;
	private double fees;
	private double taxes;
	private double discount;
	private double total;
	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	/*
	 * Sorts by customer name alphabetically
	 */
	public static class SortByCustomerName implements Comparator<Invoice> {

		@Override
		public int compare(Invoice arg0, Invoice arg1) {
			if(arg0.customer.getName().equals(arg1.customer.getName())) {
				return 0;
			} else {
				return arg0.customer.getName().compareTo(arg1.customer.getName()); 
			}
		}
		
	}
	
	/*
	 * Sorts from highest invoice to lowest invoice
	 */
	public static class SortByInvoiceTotal implements Comparator<Invoice> {

		@Override
		public int compare(Invoice arg0, Invoice arg1) {
			if(arg0.total == arg1.total) {
				return 0;
			} else if(arg0.total > arg1.total){
				return -1;
			} else {
				return 1;
			}
		}
		
	}
	
	/*
	 * Sorts by customer type and then by the sales person name
	 */
	public static class SortByCustomerThenPerson implements Comparator<Invoice> {

		@Override
		public int compare(Invoice arg0, Invoice arg1) {
			
			// customer type
			if(arg0.customer.getType().equalsIgnoreCase(arg1.customer.getType())) {
				
				// salesperson last name
				if(arg0.person.getLastName().equalsIgnoreCase(arg1.person.getLastName())) {
					
					// salesperson first name
					if(arg0.person.getFirstName().equalsIgnoreCase(arg1.person.getFirstName())) {
						return 0;
					} else {
						return arg0.person.getFirstName().compareToIgnoreCase(arg1.person.getFirstName());
					}
					
				} else {
					
					return arg0.person.getLastName().compareToIgnoreCase(arg1.person.getLastName());
					
				}
				
			} else {
				
				return arg0.customer.getType().compareToIgnoreCase(arg1.customer.getType());
				
			}
		}
		
	}
	
	public static void printSummaryHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("Executive Summary Report\n");
		sb.append("=========================\n");
		sb.append(String.format("%-10s %-50s %-30s %11s %11s %11s %11s %11s %n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Taxes",
				"Discount", "Total"));
		
		System.out.print(sb.toString());
	}
	
	public static void printSummaryFooter() {
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public void printSummary() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%-10s %-50s %-30s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f %n", 
				code, customer.getName() + " [" + customer.getTypeString() + "]", person.toString(), 
				subtotal, fees, taxes, discount, total));
		
		
		
		System.out.print(sb.toString());
	}
	
	/*
	 * TODO
	 * Refactored method from previous assignment, implementation not yet complete
	 */
	public void printDetailedReport() {
		
		StringBuilder sb = new StringBuilder();
		
		//invoice information
		sb.append(String.format("Invoice %-10s \n", code));
		sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		sb.append(String.format("%-82s %-10s \n", "Air America", "PNR"));
		sb.append(String.format("Issue Date: %-70s %-10s \n", invoiceDate, PNR));
		sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		
		sb.append("FLIGHT INFORMATION");
		sb.append(String.format("%-20s %-20s %-20s %-30s %-30s %-20s %n", 
				"Flight Date", "Flight No.", "Class", "Departing City and Time", "Arriving City and Time", "Aircraft"));
		
		// ticket and seat information
		for(Ticket t : tickets) {
			sb.append(t.toStringFlightInfo());
		}
		
		// customer information
		//TODO
	
		// product summary
		for(Ticket t : tickets) {
			sb.append(t.toString());
		}
		
		for(Service s : services) {
			sb.append(s.toString());
		}
		
		//totals
		sb.append(String.format("SUB-TOTALS %70s $%10.2f $%10.2f $%10.2f \n", " ", subtotal, taxes, subtotal + taxes));
		switch (customer.getType()) {
			case "V": 
				sb.append(String.format("DISCOUNT ( NO TAX ) %85s $%10.2f \n", " ", discount));
				break;
			case "G":
				sb.append(String.format("DISCOUNT ( NO DISCOUNT ) %80s $%10.2f \n", " ", discount));
				break;
			case "C":
				sb.append(String.format("DISCOUNT ( 12%% OFF SUB-TOTAL ) %74s $%10.2f \n", " ", discount));
				break;
		}
		sb.append(String.format("ADDITIONAL FEE %90s $%10.2f \n", " ", fees));
		sb.append(String.format("TOTAL %99s $%10.2f \n", " ", total));
		
		System.out.print(sb.toString());
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public DateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(DateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<Service> getServices() {
		return services;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	public String getPNR() {
		return PNR;
	}

	public void setPNR(String PNR) {
		this.PNR = PNR;
	}
	
	/*
	 * invoice totals are calculated upon construction
	 */
	public Invoice(String code, Customer customer, Person person, DateTime invoiceDate,
			ArrayList<Ticket> tickets, ArrayList<Service> services) {
		this.code = code;
		this.customer = customer;
		this.person = person;
		this.invoiceDate = invoiceDate;
		this.tickets = tickets;
		this.services = services;
		this.PNR = StandardUtils.generatePNR();
		for(Service s : services) {
			switch (s.getType()) {
				case "SR":
					if(!tickets.isEmpty()) {
						subtotal += 0.95 * s.getSubtotal();
						((Refreshment)s).setSubtotal(0.95 * s.getSubtotal());
						break;
					}
				default:
					subtotal += s.getSubtotal();
					break;
			}
			taxes += s.getTaxes();
			total += s.getTotal();
		}
		
		for(Ticket t : tickets) {
			subtotal += t.getSubtotal();
			taxes += t.getTaxes();
			total += t.getTotal();
		}
		
		switch (this.customer.getType()) {
			case "V":
				discount = -taxes;
				fees = 0;
				break;
			case "C":
				discount = -0.12 * subtotal;
				fees = 40;
				break;
			case "G":
				discount = 0;
				fees = 0;
				break;
		}
		
		total += discount;
		total += fees;
		
	}
	
	
}
