package com.airamerica.invoice;

import java.util.ArrayList;

import org.joda.time.DateTime;

import com.airamerica.customer.Customer;
import com.airamerica.person.Person;
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

	public void setPNR(String pNR) {
		PNR = pNR;
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
	}
	
	
}
