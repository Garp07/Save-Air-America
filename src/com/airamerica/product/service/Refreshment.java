package com.airamerica.product.service;

public class Refreshment extends Service {
	private String name;
	private double cost;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {						
		return name;
	}
	
	public void setName(String name) {				
		this.name = name;
	}
	
	public double getCost() {						
		return cost;
	}
	
	public void setCost(double cost) {				
		this.cost = cost;
	}
	
	//Refreshment constructor
	public Refreshment(String code, String name, double cost) {
		super(code);
		this.name = name;
		this.cost = cost;
		this.type = "SR";
	}
}