package com.airamerica.product.service;

public class Refreshment extends Service {
	private String name;
	private double costPerUnit;

	public String getName() {						
		return name;
	}
	
	public void setName(String name) {				
		this.name = name;
	}
	
	public double getCostPerUnit() {						
		return costPerUnit;
	}
	
	public void setCost(double cost) {				
		this.costPerUnit = cost;
	}
	
	//Refreshment constructor
	public Refreshment(String code, String name, double costPerUnit, int quantity) {
		super(code);
		this.name = name;
		this.costPerUnit = costPerUnit;
		this.type = "SR";
		
		this.quantity = quantity;
		
		this.subtotal = quantity * costPerUnit;
		this.taxes = 0.04 * subtotal;
		this.total = subtotal + taxes;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
		this.taxes = 0.04 * this.subtotal;
		this.total = this.subtotal + taxes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String itemDescription = String.format("%s (%d units @ $%.2f/unit : 5%% off w/ ticket purchase)", name, quantity, costPerUnit);
		
		sb.append(String.format("%-10s %-70s $%10.2f $%10.2f $%10.2f \n", code, itemDescription, subtotal, taxes, total));		
		
		return sb.toString();
	}
	
//	public double getServiceCost() {
//		double cost = (double) this.quantity*this.cost;
//		return cost;
//	}
	
}