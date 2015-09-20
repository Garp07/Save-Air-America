package classes;

public class Customer {
	private String code;
	private char type;
	private String primaryContact;							//Change class to Person once person class is integrated?
	private String name;
	private int airlineMiles;								//Should this be an int?
	
	public String getCode() {								//Customer code getter
		return code;
	}
	
	public void setCode(String code) {						//Customer code setter
		this.code = code;
	}
	
	public char getType() {									//Customer type getter		
		return type;
	}
	
	public void setType(char type) {						//Customer type setter
		if(type == 'G' || type == 'C' || type == 'V') {
			this.type = type;
		} 
		else {
			System.out.println("That is not a valid customer type code.");		//Is there a better way to handle this?
			System.out.println("Valid customer codes: G, C, V.");
		}
	}
	
	public String getPrimaryContact() {						//Customer primary contact getter
		return primaryContact;
	}
	
	public void setPrimaryContact(String primaryContact) {	//Customer primary contact setter
		this.primaryContact = primaryContact;
	}
	
	public String getName() {								//Customer name getter
		return name;
	}
	
	public void setName(String name) {						//Customer name setter
		if(name != null) {
			this.name = name;
		}
		else {
			System.out.println("Please use a valid name.");	//Is there a better way to handle this?
		}
	}
	
	public int getAirlineMiles() {							//Customer airline miles getter
		return airlineMiles;
	}
	
	public void setAirlineMiles(int airlineMiles) {			//Customer airline miles setter
		this.airlineMiles = airlineMiles;
	}
	
	public Customer(String code, char type, String primaryContact, String name, int airlineMiles) {			//Customer constructor
		this.code = code;
		this.type = type;
		this.primaryContact = primaryContact;
		this.name = name;
		this.airlineMiles = airlineMiles;
	}
	
	//Need a method to get person object from the primary contact person code (Customer.primaryContact??)
	
	//Do we need any try-catch blocks for users entering invalid data upon instantiation?
	
}
