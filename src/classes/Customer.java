package classes;

public class Customer {
	private String code;
	private String type;
	private Person primaryContact;							
	private String name;
	private double airlineMiles;
	
	//name getter
	public String getname() {
		return name;
	}
	
	//name setter
	public void setame(String name) {
		this.name = name;
	}

	public String getCode() {								//Customer code getter
		return code;
	}
	
	public void setCode(String code) {						//Customer code setter
		this.code = code;
	}
	
	public String getType() {									//Customer type getter		
		return type;
	}
	
	public void setType(String type) {						//Customer type setter
		switch(type) {
			case "G": this.type = "general"; break;
			case "C": this.type = "corporate"; break;
			case "V": this.type = "government"; break;
		}
	}
	
	public Person getPrimaryContact() {						//Customer primary contact getter
		return primaryContact;
	}
	
	public void setPrimaryContact(Person primaryContact) {	//Customer primary contact setter
		this.primaryContact = primaryContact;
	}
	
	
	public double getAirlineMiles() {							//Customer airline miles getter
		return airlineMiles;
	}
	
	public void setAirlineMiles(double airlineMiles) {			//Customer airline miles setter
		this.airlineMiles = airlineMiles;
	}
	
	public Customer(String code, String type, Person primaryContact, String name, double airlineMiles) {			//Customer constructor
		this.code = code;
		this.setType(type);
		this.primaryContact = primaryContact;
		this.name = name;
		this.airlineMiles = airlineMiles;
	}
	
	//Need a method to get person object from the primary contact person code (Customer.primaryContact??)
	
	//Do we need any try-catch blocks for users entering invalid data upon instantiation?
	
}
