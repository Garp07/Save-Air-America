package com.airamerica.utils;

import com.airamerica.product.ticket.Ticket;
import org.joda.time.DateTime;
import com.airamerica.product.ticket.OffseasonTicket;
import com.airamerica.product.ticket.AwardTicket;
import com.airamerica.product.service.CheckedBaggage;
import com.airamerica.product.service.Insurance;
import com.airamerica.product.service.Refreshment;
import com.airamerica.product.service.SpecAssist;

public class Calculator {

	public static double standardTicketCost(Ticket t) {
		double distance = t.getFlightDistance();
		double fareRate = t.getFlightClass().getCostPerMile();
		double quantity = (double)t.getSeats().size();
		double subtotal = distance*fareRate*quantity;
		return subtotal;
				
	}
	
	public static double offseasonTicketCost(OffseasonTicket t) {
		DateTime seasonStart = t.getSeasonStartDate();
		DateTime seasonEnd = t.getSeasonEndDate();
		DateTime travelDate = t.getTravelDate();
		double subtotal = standardTicketCost(t);
		
		if (travelDate.isAfter(seasonStart) && travelDate.isBefore(seasonEnd)) {
			double rebate = t.getRebate();
			double discount = subtotal*rebate;
			subtotal = subtotal - discount;
		}
		
		subtotal = subtotal + 20;
		
		return subtotal;
		
	}
	
	public static double awardTicketCost(AwardTicket t) {
		double subtotal = 30;
		return subtotal;
		//Figure out a method for calculation later, for now it looks like we can always assume they have enough awards points to get the 30 tickets
		//Not dependent on quantity, its flat 30 for any number of tickets
	}
	
	public static double baggageCost(CheckedBaggage s) {
		double subtotal;
		double quantity = (double)s.getQuantity();
		
		if (quantity == 1) {
			subtotal = 25;
			
		} else if (quantity > 1) {
			double extraBags = quantity - 1;
			subtotal = 25 + ((extraBags)*35);
			
		} else {
			subtotal = 0;
			
		}
		
		return subtotal;
		
	}
	
	public static double insuranceCost(Insurance s) {
		double travelDistance = s.getTicket().getFlightDistance();
		double passengers = (double)s.getTicket().getSeats().size();
		double costPerMile = s.getCostPerMile();
		double subtotal = travelDistance*costPerMile*passengers;
		return subtotal;
		
	}
	
	public static double refreshmentCost(Refreshment s) {
		double cost = s.getCost();
		double quantity = (double)s.getQuantity();
		double subtotal = cost*quantity;
		return subtotal;
		
	}
	
	public static double specialAssistanceCost(SpecAssist s) {
		double subtotal = 0;
		return subtotal;
		//For consistency
		
	}
	
	public static double serviceTaxes(double subtotal) {
		double salesTax = 0.04;
		double taxTotal = subtotal*salesTax;
		return taxTotal;
		
	}
	
	public static double ticketTaxes(double subtotal, Ticket t) {
		double passengers = (double)t.getSeats().size();
		double flightSegmentTax = 4*passengers;
		double federalExciseTax = 0.075;
		double taxTotal = (subtotal*federalExciseTax) + flightSegmentTax;
		return taxTotal;
		
	}
	
	public static double fees(Ticket t) {
		double passengers = (double)t.getSeats().size();
		double facilityFee = t.getArrAirport().getPassengerFacilityFee();
		double securityFee = passengers*5.60;
		double facilityCharge = passengers*facilityFee;
		double totalFees = securityFee + facilityCharge;
		return totalFees;
		
	}
	
	public static double corporateDiscount(double subtotal) {
		double discount = subtotal*0.12;
		return discount;
		
	}
	
}
