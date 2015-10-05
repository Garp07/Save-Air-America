package com.airamerica.product.service;

public class SpecAssist extends Service {
	private String typeOfService;					

	public String getTypeOfService() {					
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {	
		this.typeOfService = typeOfService;
	}
	
	public SpecAssist(String code, String type,			
			String typeOfService) {
		super(code);
		this.typeOfService = typeOfService;
		this.type = "SS";
	}
}