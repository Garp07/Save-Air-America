package src.com.airamerica.printer;

import java.util.ArrayList;

import src.com.airamerica.customer.Customer;
import src.com.airamerica.product.service.Insurance;
import src.com.airamerica.product.service.Refreshment;
import src.com.airamerica.product.service.Service;
import src.com.airamerica.product.service.SpecAssist;
import src.com.airamerica.product.ticket.Ticket;

public class ProductInfoPrinter {
	
	public void print(ArrayList<Ticket> tickets, ArrayList<Service> services, Customer client) {
		System.out.println("FARES AND SERVICES");
		System.out.printf("%-10s %-80s %20s %20s %20s %n", "Code", "Item", "Subtotal", "Taxes/Fees", "Total");
		double grandSubtotal = 0;
		double grandTaxesFees = 0;
		double redemptionFee = 0;
		
		for(Ticket t : tickets) {
			String code = t.getCode();
			String type = t.getType();
			String flightClass = t.getFlightClass().getType();
			String item = null;
			double subtotal = t.getBasefare()*(double)t.getSeats().size();
			double taxesFees = t.getTaxes();
			double total = t.getTotalFare();
				switch(type) {
					case "TS":
						item = "Standard Ticket";
						break;
					case "TO":
						item = "Offseason Ticket";
						break;
					case "TA":
						item = "Awards Ticket";
						subtotal = 0;                             				//Change this to conditional to check the airline points of the customer later, 
						taxesFees = t.getTaxes();                //for now assume customer always has enough points
						total = t.getTaxes()*(double)t.getSeats().size();
						redemptionFee = 40;
						break;
					default:
						break;
				}
			int quantity = t.getSeats().size();
			double basefare = t.getBasefare();
			double distance = t.getFlightDistance();
			String depAirport = t.getDepAirport().getAirportCode();
			String arrAirport = t.getArrAirport().getAirportCode();
			
			System.out.printf("%-10s %-20s (%-2s) %-3s to %-3s, %10.2f miles %34s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, flightClass, 
					depAirport, arrAirport, distance, " ", subtotal, " ", taxesFees, " ", total);
			System.out.printf("%-15s (%-2d units @ $%-7.2f per unit) %n", " ", quantity, basefare);
			
			grandSubtotal = grandSubtotal + subtotal;
			grandTaxesFees = grandTaxesFees + taxesFees;
			
		}
		
		for(Service s : services) {
			String code = s.getCode();
			String type = s.getType();
			double subtotal = s.getServiceCost()*(double)s.getQuantity();
			double taxesFees = s.getServiceTax()*(double)s.getQuantity();
			double total = s.getServiceTotal()*(double)s.getQuantity();
			String item = null;
			int quantity = s.getQuantity();
			
			switch(type) {
				case "SC":
					item = "Checked Baggage";
					subtotal = s.getServiceCost();
					taxesFees = s.getServiceTax();
					total = s.getServiceTotal();
					System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, " ", subtotal, " ", taxesFees, " ", total);
					System.out.printf("%-15s (%-2d unit(s) @ $25.00 for 1st and $35.00 onwards) %n", " ", quantity);
					break;
				case "SI":
					Insurance i = (Insurance) s;
					item = "Insurance";
					String company = i.getName();
					String ageClass = i.getAgeClass();
					double costPerMile = i.getCostPerMile();
					System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item + ": " + company + " (" + ageClass + ")", " ", subtotal, " ", taxesFees, " ", total);
					System.out.printf("%-15s (%-2d units @ $%-5.2f per mile) %n", " ", quantity, costPerMile);
					break;
				case "SR":
					Refreshment r = (Refreshment) s;
					item = r.getName();
					double cost = r.getCost();
					if(tickets.size() != 0) {
						subtotal = subtotal - subtotal*0.05;
						taxesFees = subtotal*0.04;
						total = subtotal + taxesFees;
					}
					System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, " ", subtotal, " ", taxesFees, " ", total);
					System.out.printf("%-15s (%-2d units @ $%6.2f per unit; 5%% discount if also purchasing a ticket) %n", " ", quantity, cost);
					break;
				case "SS":
					SpecAssist a = (SpecAssist) s;
					item = a.getTypeOfService();
					String person = a.getPerson().getLastName() + ", " + a.getPerson().getFirstName();
					System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, " ", subtotal, " ", taxesFees, " ", total);
					System.out.printf("%-15s (special assistance for %-20s %n", " ", person + ")");
					break;
				default:
					break;
			}
			
			grandSubtotal = grandSubtotal + subtotal;
			grandTaxesFees = grandTaxesFees + taxesFees;
		}
		System.out.printf("%-93s ------------------------------------------------------------- %n", " ");
		
		double discounts = 0;
		String customerType = client.getType();
		
		if(customerType.equals("C")) {
			discounts = grandSubtotal*0.12;
			redemptionFee = redemptionFee + 40;
		} else if(customerType.equals("V")) {
			discounts = grandTaxesFees;
		} 
		
		double grandTotal = grandSubtotal - discounts + grandTaxesFees + redemptionFee;
		
		System.out.printf("%93s %-46s $%12.2f %n", " ", "Subtotal", grandSubtotal);
		System.out.printf("%93s %-45s -$%12.2f %n", " ", "Discounts", discounts);
		System.out.printf("%93s %-46s $%12.2f %n", " ", "Additional Fees", redemptionFee);
		System.out.printf("%93s %-46s $%12.2f %n", " ", "Grand Total", grandTotal);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
}
