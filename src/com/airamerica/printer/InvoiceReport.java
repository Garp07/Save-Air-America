package com.airamerica.printer;

import java.util.ArrayList;

import com.airamerica.airport.Airport;
import com.airamerica.customer.Customer;
import com.airamerica.dataconverter.AirportConverter;
import com.airamerica.dataconverter.CustomerConverter;
import com.airamerica.dataconverter.InvoiceConverter;
import com.airamerica.dataconverter.PersonConverter;
import com.airamerica.dataconverter.ProductConverter;
import com.airamerica.invoice.Invoice;
import com.airamerica.person.Person;
import com.airamerica.product.Product;
import com.airamerica.product.ticket.Ticket;

public class InvoiceReport {

	public static void main(String[] args) {
		AirportConverter airports = new AirportConverter("data/Airports.dat");					
		ArrayList<Airport> airportsArray = airports.parseAirports();
		
		//Create array list of persons
		PersonConverter persons = new PersonConverter("data/Persons.dat");						
		ArrayList<Person> personsArray = persons.parsePersons();
		
		/* 
		 * Create array list of products - first go through and find all tickets, 
		 * then go through file again to make complete objects
		 */
		ProductConverter products = new ProductConverter("data/Products.dat", airportsArray);	
		ArrayList<Ticket> ticketsArray = products.parseTickets();
		products = new ProductConverter("data/Products.dat", airportsArray, ticketsArray);
		ArrayList<Product> productsArray = products.parseProducts();
		
		//Create array list of customers
		CustomerConverter customers = new CustomerConverter("data/Customers.dat", personsArray);				
		ArrayList<Customer> customersArray = customers.parseCustomers();
		
		InvoiceConverter invoices = new InvoiceConverter("data/Invoices.dat", productsArray, 
				customersArray, personsArray);
		ArrayList<Invoice> invoicesArray = invoices.parseInvoices();
		
		SummaryPrinter summary = new SummaryPrinter();
		InvoicePrinter singleInvoice = new InvoicePrinter();
		
		summary.print(invoicesArray);
		System.out.printf("%n");
		System.out.printf("%n");
		for(Invoice i : invoicesArray) {
			singleInvoice.print(i);
			System.out.printf("%n");
			System.out.printf("%n");
		}
		

	}

}
