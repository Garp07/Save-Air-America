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
	
	
	/*
	 * Sorts by customer name alphabetically
	 */
	public class SortByCustomerName implements Comparator<Invoice> {

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
	public class SortByInvoiceTotal implements Comparator<Invoice> {

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
	public class SortByCustomerThenPerson implements Comparator<Invoice> {

		@Override
		public int compare(Invoice arg0, Invoice arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
	public String toStringDetailedReport() {
		StringBuilder sb = new StringBuilder();
		
		for(Ticket t : tickets) {
			sb.append(t.toString());
		}
		
		for(Service s : services) {
			sb.append(s.toString());
		}
		
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
		
		return sb.toString();
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
