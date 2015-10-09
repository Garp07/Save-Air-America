package src.com.airamerica.printer;

import java.util.ArrayList;

import src.com.airamerica.invoice.Invoice;
import src.com.airamerica.product.service.Service;
import src.com.airamerica.product.ticket.Ticket;

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
				String code = i.getCode();
				String client = i.getCustomer().getName();
				String type = i.getCustomer().getType();
					if(type.equals("G")) {
						type = "General";
					}else if(type.equals("V")) {
						type = "Government";
					}else {
						type = "Corporate";
					}
				String salesperson = i.getPerson().getLastName() + ", " + i.getPerson().getFirstName();
				double grandSubtotal = 0;
				double grandTaxes = 0;
				double redemptionFee = 0;
				
				for(Ticket t : i.getTickets()) {
					double subtotal = t.getBasefare()*(double)t.getSeats().size();
					double taxesFees = t.getTaxes();
						if(t.getCode().equals("TA")) {
							redemptionFee = 40;
							subtotal = 0;
						}
					grandSubtotal = grandSubtotal + subtotal;
					grandTaxes = grandTaxes + taxesFees;
				}
				
				for(Service s : i.getServices()) {
					double subtotal = s.getServiceCost()*(double)s.getQuantity();
					double taxesFees = s.getServiceTax()*(double)s.getQuantity();
					if(s.getType().equals("SC")) {
						subtotal = s.getServiceCost();
						taxesFees = s.getServiceTax();
					}
					grandSubtotal = grandSubtotal + subtotal;
					grandTaxes = grandTaxes + taxesFees;
				}
				
				double discounts = 0;
				String customerType = i.getCustomer().getType();
				
				if(customerType.equals("C")) {
					discounts = grandSubtotal*0.12;
					redemptionFee= redemptionFee + 40;
				} else if(customerType.equals("V")) {
					discounts = grandTaxes;
				} 
				
				double grandTotal = grandSubtotal - discounts + grandTaxes + redemptionFee;
				
				System.out.printf("%-10s %-50s %-30s $%10.2f -$%10.2f $%10.2f $%10.2f $%10.2f %n", code, client + " [" + type + "]", salesperson, 
						grandSubtotal, discounts, grandTaxes, redemptionFee, grandTotal);
				
				superSubtotal = superSubtotal + grandSubtotal;
				superDiscounts = superDiscounts + discounts;
				superTaxes = superTaxes + grandTaxes;
				superFees = superFees + redemptionFee;
				superTotal = superTotal + grandTotal;
			}
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-92s $%10.2f -$%10.2f $%10.2f $%10.2f $%10.2f %n", "Overall Totals:", superSubtotal, superDiscounts, superTaxes,
					superFees, superTotal);
			
		}
}
