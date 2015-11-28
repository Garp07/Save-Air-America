package com.airamerica.printer;

public class ProductInfoPrinter {
	
//	public void print(ArrayList<Ticket> tickets, ArrayList<Service> services, Customer client) {
//		System.out.println("FARES AND SERVICES");
//		System.out.printf("%-10s %-80s %20s %20s %20s %n", "Code", "Item", "Subtotal", "Taxes/Fees", "Total");
//		double grandSubtotal = 0;
//		double grandTaxes = 0;
//		double runningTotal = 0;
//		
//		for(Ticket t : tickets) {
//			String code = t.getCode();
//			String type = t.getType();
//			String flightClass = t.getFlightClass().getType();
//			String item = null;
//			int quantity = t.getSeats().size();
//			double distance = t.getFlightDistance();
//			String depAirport = t.getDepAirport().getAirportCode();
//			String arrAirport = t.getArrAirport().getAirportCode();
//			double subtotal = 0, taxes = 0, fees = 0, taxesFees = 0, total = 0;
//			
//				switch(type) {
//					case "TS":
//						item = "Standard Ticket";
//						subtotal = Calculator.standardTicketCost(t);
//						break;
//					case "TO":
//						item = "Offseason Ticket";
//						subtotal = Calculator.offseasonTicketCost((OffseasonTicket)t);
//						break;
//					case "TA":
//						item = "Awards Ticket";
//						subtotal = Calculator.awardTicketCost((AwardTicket)t);
//						break;
//					default:
//						break;
//				}
//			
//			taxes = Calculator.ticketTaxes(subtotal, t);
//			fees = Calculator.fees(t);
//			taxesFees = taxes + fees;
//			total = subtotal + taxesFees;
//			
//			System.out.printf("%-10s %-20s (%-2s) %-3s to %-3s, %10.2f miles %34s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, flightClass, 
//					depAirport, arrAirport, distance, " ", subtotal, " ", taxesFees, " ", total);
//			System.out.printf("%-15s (%-2d units @ $%-7.2f per unit) %n", " ", quantity, subtotal);
//			
//			grandSubtotal = grandSubtotal + subtotal;
//			grandTaxes = grandTaxes + taxesFees;
//			runningTotal = runningTotal + total;
//			
//		}
//		
//		for(Service s : services) {
//			String code = s.getCode();
//			String type = s.getType();
//			String item = null;
//			int quantity = s.getQuantity();
//			double subtotal = 0, taxes = 0, total = 0;
//			
//				switch(type) {
//					case "SC":
//						item = "Checked Baggage";
//						subtotal = Calculator.baggageCost((CheckedBaggage)s);
//						taxes = Calculator.serviceTaxes(subtotal);
//						total = subtotal + taxes;
//						System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, " ", subtotal, " ", taxes, " ", total);
//						System.out.printf("%-15s (%-2d unit(s) @ $25.00 for 1st and $35.00 onwards) %n", " ", quantity);
//						break;
//					case "SI":
//						Insurance i = (Insurance) s;
//						item = "Insurance";
//						double distance = i.getTicket().getFlightDistance();
//						String company = i.getName();
//						String ageClass = i.getAgeClass();
//						double costPerMile = i.getCostPerMile();
//						subtotal = Calculator.insuranceCost(i);
//						taxes = Calculator.serviceTaxes(subtotal);
//						total = subtotal + taxes;
//						System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item + ": " + company + " (" + ageClass + ")", " ", subtotal, " ", taxes, " ", total);
//						System.out.printf("%-15s (%-2d units @ $%-5.2f per mile for %10.2f miles) %n", " ", quantity, costPerMile, distance);
//						break;
//					case "SR":
//						Refreshment r = (Refreshment) s;
//						item = r.getName();
//						double cost = r.getCost();
//						subtotal = Calculator.refreshmentCost(r);
//						if(tickets.size() != 0) {
//							subtotal = subtotal - subtotal*0.05;
//						}
//						taxes = Calculator.serviceTaxes(subtotal);
//						total = subtotal + taxes;
//						System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, " ", subtotal, " ", taxes, " ", total);
//						System.out.printf("%-15s (%-2d units @ $%6.2f per unit; 5%% discount if also purchasing a ticket) %n", " ", quantity, cost);
//						break;
//					case "SS":
//						SpecAssist a = (SpecAssist) s;
//						item = a.getTypeOfService();
//						subtotal = Calculator.specialAssistanceCost(a);
//						taxes = Calculator.serviceTaxes(subtotal);
//						total = subtotal + taxes;
//						String person = a.getPerson().getLastName() + ", " + a.getPerson().getFirstName();
//						System.out.printf("%-10s %-60s %28s $%10.2f %8s $%10.2f %8s $%10.2f %n", code, item, " ", subtotal, " ", taxes, " ", total);
//						System.out.printf("%-15s (special assistance for %-20s %n", " ", person + ")");
//						break;
//					default:
//						break;
//				}
//			
//				grandSubtotal = grandSubtotal + subtotal;
//				grandTaxes = grandTaxes + taxes;
//				runningTotal = runningTotal + total;
//				
//		}
//		
//		System.out.printf("%-93s ------------------------------------------------------------- %n", " ");
//		
//		double discount, redemptionFee, grandTotal;
//		String customerType = client.getType();
//		
//		if(customerType.equals("C")) {
//			discount = Calculator.corporateDiscount(grandSubtotal);
//			redemptionFee = 40;
//			
//		} else if(customerType.equals("V")) {
//			discount = grandTaxes;
//			redemptionFee = 0;
//			
//		} else {
//			discount = 0;
//			redemptionFee = 0;
//			
//		}
//		
//		grandTotal = runningTotal - discount + redemptionFee;
//		
//		System.out.printf("%93s %-46s $%12.2f %n", " ", "Total", runningTotal);
//		System.out.printf("%93s %-45s -$%12.2f %n", " ", "Discounts", discount);
//		System.out.printf("%93s %-46s $%12.2f %n", " ", "Additional Fees", redemptionFee);
//		System.out.printf("%93s %-46s $%12.2f %n", " ", "Grand Total", grandTotal);
//		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
//	}
}
