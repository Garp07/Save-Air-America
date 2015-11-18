package com.airamerica.printer;

import java.util.ArrayList;

import com.airamerica.invoice.Invoice;
import com.airamerica.product.service.CheckedBaggage;
import com.airamerica.product.service.Insurance;
import com.airamerica.product.service.Refreshment;
import com.airamerica.product.service.Service;
import com.airamerica.product.service.SpecAssist;
import com.airamerica.product.ticket.AwardTicket;
import com.airamerica.product.ticket.OffseasonTicket;
import com.airamerica.product.ticket.Ticket;
import com.airamerica.utils.Calculator;

public class SummaryPrinter {

		public void print(ArrayList<Invoice> invoices) {
			System.out.println("Executive Summary Report");
			System.out.println("=========================");
			System.out.printf("%-10s %-50s %-30s %11s %12s %11s %11s %11s %n", "Invoice", "Customer", "Salesperson", "Subtotal", "Discounts", "Taxes",
					"Other Fees", "Total");
			
			double superSubtotal = 0;
			double superDiscounts = 0;
			double superTaxes = 0;
			double superFees = 0;
			double superTotal = 0;
			
			for(Invoice i : invoices) {
				double grandSubtotal = 0;
				double grandTaxes = 0;
				double runningTotal = 0;
				
				for(Ticket t : i.getTickets()) {
					String ticketType = t.getType();
					double subtotal = 0, taxes = 0, fees = 0, taxesFees = 0, total = 0;
					
					switch(ticketType) {
					case "TS":
						subtotal = Calculator.standardTicketCost(t);
						break;
					case "TO":
						subtotal = Calculator.offseasonTicketCost((OffseasonTicket)t);
						break;
					case "TA":
						subtotal = Calculator.awardTicketCost((AwardTicket)t);
						break;
					default:
						break;
					}
					
					taxes = Calculator.ticketTaxes(subtotal, t);
					fees = Calculator.fees(t);
					taxesFees = taxes + fees;
					total = subtotal + taxesFees;
					
					grandSubtotal = grandSubtotal + subtotal;
					grandTaxes = grandTaxes + taxesFees;
					runningTotal = runningTotal + total;
					
				}
				
				for(Service s : i.getServices()) {
					String serviceType = s.getType();
					double subtotal = 0, taxes = 0, total = 0;
					
					switch(serviceType) {
					case "SC":
						subtotal = Calculator.baggageCost((CheckedBaggage)s);
						break;
					case "SI":
						subtotal = Calculator.insuranceCost((Insurance)s);
						break;
					case "SR":
						subtotal = Calculator.refreshmentCost((Refreshment)s);
						if(i.getTickets().size() != 0) {
							subtotal = subtotal - subtotal*0.05;
						}
						break;
					case "SS":
						subtotal = Calculator.specialAssistanceCost((SpecAssist)s);
						break;
					default:
						break;
					}
					
					taxes = Calculator.serviceTaxes(subtotal);
					total = subtotal + taxes;
					
					grandSubtotal = grandSubtotal + subtotal;
					grandTaxes = grandTaxes + taxes;
					runningTotal = runningTotal + total;

				}
				
				String code = i.getCode();
				String client = i.getCustomer().getName();
				String salesperson = i.getPerson().getLastName() + ", " + i.getPerson().getFirstName();
				String customerType = i.getCustomer().getType();
				String type;

					if(customerType.equals("C")) {
						type = "Corporate";
						
					} else if(customerType.equals("V")) {
						type = "Government";
						
					} else {
						type = "General";
					}
					
				double discount = 0, redemptionFee = 0;
				
					if(customerType.equals("C")) {
						discount = Calculator.corporateDiscount(grandSubtotal);
						redemptionFee = 40;
					
					} else if(customerType.equals("V")) {
						discount = grandTaxes;
						redemptionFee = 0;
						
					} else {
						discount = 0;
						redemptionFee = 0;
					}
				
				double grandTotal = runningTotal - discount + redemptionFee;
				
				System.out.printf("%-10s %-50s %-30s $%10.2f -$%10.2f $%10.2f $%10.2f $%10.2f %n", code, client + " [" + type + "]", salesperson, 
						grandSubtotal, discount, grandTaxes, redemptionFee, grandTotal);
				
				superSubtotal = superSubtotal + grandSubtotal;
				superDiscounts = superDiscounts + discount;
				superTaxes = superTaxes + grandTaxes;
				superFees = superFees + redemptionFee;
				superTotal = superTotal + grandTotal;
			}
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-92s $%10.2f -$%10.2f $%10.2f $%10.2f $%10.2f %n", "Overall Totals:", superSubtotal, superDiscounts, superTaxes,
					superFees, superTotal);
			
		}
}
