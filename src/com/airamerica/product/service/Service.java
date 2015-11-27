package com.airamerica.product.service;

import com.airamerica.product.Product;

abstract public class Service extends Product {
	protected int quantity;
	protected double subtotal;
	protected double taxes;
	protected double total;
	
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
	
	public double getSubtotal() {
		return subtotal;
	}
	
	public double getTaxes() {
		return taxes;
	}
	
	public double getTotal() {
		return total;
	}
	
	@Override
	public abstract String toString();
	
}
