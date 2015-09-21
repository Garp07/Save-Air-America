package classes;

public class Product {
	private String code;
	private String type;
	
	public class Ticket extends Product {					//This sub for standard tickets
		private String depAirportCode;						//These refer to airports, maybe need to be of class Airport?
		private String arrAirportCode;
		private String depTime;								//Times are strings of hh:mm
		private String arrTime;
		private String flightNo;							//Could an int type be used here?
		private String flightClass;							//Two letter code
		private String aircraftType;
		
		public class Offseason extends Ticket {				//This sub of standard for offseason
			private String seasonStartDate;
			private String seasonEndDate;
			private double rebate							//Rebate represented as a decimal number
			
		}
		
		public class Awards extends Ticket { 				//This sub of standard for awards
			private int pointsPerMile;						//Is this the right data type?
		}
		
	}
	
	public class CheckedBaggage extends Product {
		private String ticketCode;							//Could an int type be used here?
		
	}
	
	public class Insurance extends Product {
		private String name;
		private String ageClass;							//Is this the right data type??
		private double costPerMile;							
		
	}
	
	public class SpecAssist extends Product {
		private String typeOfService;						//Is this the right data type?
		
	}
	
	public class Refreshment extends Product {
		private String name;
		private double cost;
		
	}
	
}
