package com.airamerica.product.service;

import com.airamerica.product.ticket.Ticket;

public class Insurance extends Service {
	private String name;
	private String ageClass;
	private double costPerMile;
	private int quantity;
	private Ticket ticket;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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
	public Insurance(String code, String name, String ageClass, double costPerMile) {
		super(code);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
		this.type = "SI";
	}
}