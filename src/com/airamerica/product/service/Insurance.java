package com.airamerica.product.service;

public class Insurance extends Service {
	private String name;
	private String ageClass;
	private double costPerMile;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeClass() {
		return ageClass;
	}

	public void setAgeClass(String ageClass) {
		this.ageClass = ageClass;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}
	
	//Insurance Constructor
	public Insurance(String code, String name, String ageClass, double costPerMile) {
		super(code);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
		this.type = "SI";
	}
}