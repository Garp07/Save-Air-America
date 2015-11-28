package com.airamerica.product.service;

import com.airamerica.product.ticket.Ticket;

public class Insurance extends Service {
	private String name;
	private String ageClass;
	private double costPerMile;
	private Ticket ticket;
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeClass() {
		return ageClass;
	}

	public void setAgeClass(String ageClass) {
		this.ageClass = ageClass;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}
	
	//Insurance Constructor
	public Insurance(String code, String name, String ageClass, double costPerMile, Ticket ticket, int quantity) {
		super(code);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
		this.type = "SI";
		this.ticket = ticket;
		this.quantity = quantity;
		
		this.subtotal = quantity * costPerMile * ticket.getFlightDistance();
		this.taxes = 0.04 * subtotal;
		this.total = subtotal + taxes;
	}
	
//	public Insurance(String code, String name, String ageClass, double costPerMile) {
//		super(code);
//		this.name = name;
//		this.ageClass = ageClass;
//		this.costPerMile = costPerMile;
//		this.type = "SI";
//	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		String itemDescription = String.format("Insurance %s(%s)", name, ageClass);
		String itemCostDescription = String.format("(%d units @ $%.2f/mile for $%.2f miles)", 
				quantity, costPerMile, ticket.getFlightDistance());
	
		sb.append(String.format("%-10s %-70s $%10.2f $%10.2f $%10.2f \n", code, itemDescription, subtotal, taxes, total));
		sb.append(String.format("%10s %s \n", "", itemCostDescription));
		
		return sb.toString();
	}
	
	
	
//	public double getServiceCost() {
//		double distance = this.ticket.getFlightDistance();
//		double cost = distance*this.costPerMile;
//		return cost;
//	}
	
	
}