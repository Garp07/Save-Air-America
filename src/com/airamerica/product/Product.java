package com.airamerica.product;

abstract public class Product {
	protected String code;
	protected String type;
	
	public String getCode() {
		return code;
	}
	
	public String getType() {
		return type;
	}
	
	//Product constructor
	public Product(String code) {
		this.code = code;
	}
}