package com.airamerica.product.ticket;

abstract public class FlightClass {
	protected String type;
	protected double costPerMile;
	
	public FlightClass() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}
	
}
