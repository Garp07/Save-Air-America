package src.com.airamerica.product.service;

import src.com.airamerica.product.Product;

abstract public class Service extends Product {
	
	//Service constructor
	public Service(String code) {
		super(code);
	}
	
	abstract double getServiceCost();
}
