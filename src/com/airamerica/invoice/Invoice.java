package src.com.airamerica.invoice;

import java.util.ArrayList;

import org.joda.time.DateTime;

import src.com.airamerica.customer.Customer;
import src.com.airamerica.person.Person;
import src.com.airamerica.product.service.Service;
import src.com.airamerica.product.ticket.Ticket;
import src.com.airamerica.utils.StandardUtils;

public class Invoice {
	protected String code;
	protected Customer customer;
	protected Person person;
	protected DateTime invoiceDate;
	protected ArrayList<Ticket> tickets;
	protected ArrayList<Service> services;
	protected String PNR;
	
	public Invoice(String code, Customer customer, Person person, DateTime invoiceDate,
			ArrayList<Ticket> tickets, ArrayList<Service> services) {
		this.code = code;
		this.customer = customer;
		this.person = person;
		this.invoiceDate = invoiceDate;
		this.tickets = tickets;
		this.services = services;
		this.PNR = StandardUtils.generatePNR();
	}
}
