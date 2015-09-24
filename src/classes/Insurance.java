package classes;

public class Insurance extends Product {
	private String name;
	private String ageClass;							//Is this the right data type??
	private double costPerMile;
	
	public String getName() {							//Name getter
		return name;
	}
	
	public void setName(String name) {					//Name setter
		if(name != null) {
			this.name = name;
		}
		else {
			System.out.println("Please use a valid name.");
		}
	}
	
	public String getAgeClass() {						//Age class getter
		return ageClass;
	}
	
	public void setAgeClass(String ageClass) {			//Age class setter
		this.ageClass = ageClass;
	}
	
	public double getCostPerMile() {					//Cost per mile getter
		return costPerMile;
	}
	
	public void setCostPerMile(double costPerMile) {	//Cost per mile setter
		this.costPerMile = costPerMile;
	}							
	
	public Insurance(String code, String type, String name, 	//Insurance constructor
			String ageClass, double costPerMile) {
		super(code, type);
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
	}
}