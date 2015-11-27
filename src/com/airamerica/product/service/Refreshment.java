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
	public Refreshment(String code, String name, double cost) {
		super(code);
		this.name = name;
		this.costPerUnit = cost;
		this.type = "SR";
	}
	
	public double getCost() {
		double cost = 0;
		cost = (quantity)*(costPerUnit);
		return cost;
	}
	
//	public double getServiceCost() {
//		double cost = (double) this.quantity*this.cost;
//		return cost;
//	}
	
}