package com.airamerica.product;

abstract public class Product {
	protected String code;
	
	public String getCode() {
		return code;
	}
	
	/*public void setCode(String code) {						//Product code setter
		this.code = code;
	}
	
	public String getType() {								//Product type getter
		return type;
	}
	
	public void setType(String type) {						//Product type setter
		if(type == "TS" || type == "TO" || type == "TA"
				|| type == "SC" || type == "SI"
				|| type == "SR" || type == "SS") {
			this.type = type;
		}
		else {
			System.out.println("That is not a valid product type code.");
			System.out.println("Valid product type codes: TS, TO, TA, SC, SI, SR, SS");
		}
	}*/
	
	public Product(String code) {
		this.code = code;
	}
}