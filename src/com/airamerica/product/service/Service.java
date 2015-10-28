package com.airamerica.product.service;

import com.airamerica.product.Product;

abstract public class Service extends Product {
	protected int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//Service constructor
	public Service(String code) {
		super(code);
	}
	
	public abstract double getServiceCost();
	
	public double getServiceTax() {
		double tax = this.getServiceCost()*0.04;
		return tax;
	}
	
	public double getServiceTotal() {
		double total = this.getServiceCost() + this.getServiceTax();
		return total;
	}
	
}
