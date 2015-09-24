package classes;

public class SpecAssist extends Product {
	private String typeOfService;						//Is this the right data type?

	public String getTypeOfService() {					//Type of service getter
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {	//Type of service setter
		this.typeOfService = typeOfService;
	}
	
	public SpecAssist(String code, String type,			//Special assistance constructor
			String typeOfService) {
		super(code, type);
		this.typeOfService = typeOfService;
	}
}