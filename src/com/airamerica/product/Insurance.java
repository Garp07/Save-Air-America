package com.airamerica.product;

public class Insurance extends Service {
	private String name;
	private String ageClass;							//Is this the right data type??
	private double costPerMile;
	
	public Insurance(String code, String name, 	//Insurance constructor
			String ageClass, double costPerMile) {
		super(code);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
	}
}