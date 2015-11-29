package com.airamerica.printer;

import java.util.ArrayList;

import com.airamerica.invoice.Invoice;
import com.airamerica.jdbc.InvoiceJDBC;
import com.airamerica.list.SortedList;

public class InvoiceReport {

	private static double subtotal = 0;
	private static double fees = 0;
	private static double taxes = 0;
	private static double discount = 0;
	private static double total = 0;
	
	public static void main(String[] args) {
		
		ArrayList<Invoice> originalInvoices = InvoiceJDBC.getInvoices();
		
		System.out.println("DONE with db");
		
		//sort by customer name
		SortedList<Invoice> invoicesByCustomerName = new SortedList<Invoice>(new Invoice.SortByCustomerName());
		
		for(Invoice i : originalInvoices) {
			invoicesByCustomerName.add(i);
		}
		
		System.out.println("DONE ordering");
		
		System.out.println("Ordered by Customer Name");
		
		Invoice.printSummaryHeader();
		for(Invoice i : invoicesByCustomerName) {
			i.printSummary();
			subtotal += i.getSubtotal();
			fees += i.getFees();
			taxes += i.getTaxes();
			discount += i.getDiscount();
			total += i.getTotal();
		}
		
		Invoice.printSummaryFooter();
		printSummaryTotals();
		
		clearTotals();
	
		// Sort by Totals
		SortedList<Invoice> invoicesByTotals = new SortedList<Invoice>(new Invoice.SortByInvoiceTotal());
		
		for(Invoice i : originalInvoices) {
			invoicesByTotals.add(i);
		}
		
		System.out.println("Ordered by Totals");
		
		Invoice.printSummaryHeader();
		for(Invoice i : invoicesByTotals) {
			i.printSummary();
			subtotal += i.getSubtotal();
			fees += i.getFees();
			taxes += i.getTaxes();
			discount += i.getDiscount();
			total += i.getTotal();
		}
		
		Invoice.printSummaryFooter();
		printSummaryTotals();
		
		clearTotals();
		
		// Sort by Customer Type then SalesPerson
		SortedList<Invoice> invoicesByCustomerTypeSalesperson = new SortedList<Invoice>(new Invoice.SortByCustomerThenPerson());
		
		for(Invoice i : originalInvoices) {
			invoicesByCustomerTypeSalesperson.add(i);
		}
		
		System.out.println("Ordered by Customer then Salesperson");
		
		Invoice.printSummaryHeader();
		for(Invoice i : invoicesByCustomerTypeSalesperson) {
			i.printSummary();
			subtotal += i.getSubtotal();
			fees += i.getFees();
			taxes += i.getTaxes();
			discount += i.getDiscount();
			total += i.getTotal();
		}
		
		Invoice.printSummaryFooter();
		printSummaryTotals();
		
		clearTotals();
		
	}
	
	private static void printSummaryTotals() {
		System.out.printf("%-92s $%10.2f $%10.2f $%10.2f $%10.2f $%10.2f %n", "TOTALS", subtotal, fees, taxes,
				discount, total);
	}
	
	private static void clearTotals() {
		subtotal = 0;
		fees = 0;
		taxes = 0;
		discount = 0;
		total = 0;
	}
		
//	private static String generateSummaryReport() {
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("Executive Summary Report\n");
//		sb.append("=========================\n");
//		sb.append(String.format("%-10s %-50s %-30s %11s %12s %11s %11s %11s %n", 
//				"Invoice", "Customer", "Salesperson", "Subtotal", "Discounts", "Taxes", "Other Fees", "Total"));
//		
//		ArrayList<Invoice> invoices = InvoiceJDBC.getInvoices();
//		
//		for(Invoice i : invoices) {
//			sb.append(i.getSummary());
//		}
//		
//		return sb.toString();
//	}
//	
//
//	private String getTravelSummary() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("FLIGHT INFORMATION");
//		sb.append("==================================================\n");
//
//		//TODO: Add code for generating Travel Information of an Invoice
//		
//		return sb.toString();
//		
//	}
//	
//	private String getCostSummary() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("FARES AND SERVICES");
//		sb.append("==================================================\n");
//
//		//TODO: Add code for generating Cost Summary of all 
//		//products and services in an Invoice
//		
//		return sb.toString();
//		
//	}
//
//	public String generateDetailReport() {
//	StringBuilder sb = new StringBuilder();		
//	sb.append("Individual Invoice Detail Reports\n");
//	sb.append("==================================================\n");
//	
//	/* TODO: Loop through all invoices and call the getTravelSummary() and 
//	getCostSummary() for each invoice*/
//	
//	
//	
//		return sb.toString();
//	}

}
