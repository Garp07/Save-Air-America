package com.airamerica.printer;

import com.airamerica.invoice.Invoice;

public class InvoicePrinter {
	
	public void print(Invoice i) {
		String code = i.getCode();
		String date = i.getInvoiceDate().toString("MM-dd-yyyy");
		String pnr = i.getPNR();
		
		System.out.printf("Invoice %-10s %n", code);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-82s %-10s %n", "Air America", "PNR");
		System.out.printf("Issue Date: %-70s %-10s %n", date, pnr);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		FlightInfoPrinter flight = new FlightInfoPrinter();
		CustomerInfoPrinter customer = new CustomerInfoPrinter();
		ProductInfoPrinter products = new ProductInfoPrinter();
		
		flight.print(i.getTickets());
		customer.print(i.getCustomer(), i.getPerson());
		products.print(i.getTickets(), i.getServices(), i.getCustomer());
	}
}
