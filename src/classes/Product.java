package classes;

public class Product {
	private String code;									//Should this have private access restrictions?
	private String type;									//How do private attributes interact with sub-classes?
	
	public String getCode() {								//Product code getter
		return code;
	}
	
	public void setCode(String code) {						//Product code setter
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
	}
	
	public Product (String code, String type) {				//Product constructor
		this.code = code;
		this.type = type;
	}
	
	public class Ticket extends Product {					//This sub for standard tickets
		private String depAirportCode;						//These refer to airports, maybe need to be of class Airport?
		private String arrAirportCode;
		private String depTime;								//Times are strings of hh:mm
		private String arrTime;
		private String flightNo;							//Could an int type be used here?
		private String flightClass;							//Two letter code
		private String aircraftType;
		
		public String getDepAirportCode() {					//Departing airport code getter
			return depAirportCode;
		}

		public void setDepAirportCode(String depAirportCode) {  	//Departing airport code setter
			this.depAirportCode = depAirportCode;
		}

		public String getArrAirportCode() {					//Arriving airport code getter
			return arrAirportCode;
		}

		public void setArrAirportCode(String arrAirportCode) {		//Arriving airport code setter
			this.arrAirportCode = arrAirportCode;
		}

		public String getDepTime() {						//Departure time getter
			return depTime;
		}

		public void setDepTime(String depTime) {			//Departure time setter
			this.depTime = depTime;
		}

		public String getArrTime() {						//Arrival time getter
			return arrTime;
		}

		public void setArrTime(String arrTime) {			//Arrival time setter
			this.arrTime = arrTime;
		}

		public String getFlightNo() {						//Flight number getter
			return flightNo;
		}

		public void setFlightNo(String flightNo) {			//Flight number setter
			this.flightNo = flightNo;
		}

		public String getFlightClass() {					//Flight class getter
			return flightClass;
		}

		public void setFlightClass(String flightClass) {	//Flight class setter
			if(flightClass == "BC" || flightClass == "EC" || flightClass == "EP") {
				this.flightClass = flightClass;
			}
			else {
				System.out.println("That is not a valid flight class code.");
				System.out.println("Valid flight class codes: BC, EC, EP.");
			}
		}

		public String getAircraftType() {					//Aircraft type getter
			return aircraftType;
		}

		public void setAircraftType(String aircraftType) {	//Aircraft type setter
			this.aircraftType = aircraftType;
		}

		public Ticket(String code, String type, String depAirportCode, String arrAirportCode, String depTime,
				String arrTime, String flightNo, String flightClass, String aircraftType) {
			super(code, type);
			this.depAirportCode = depAirportCode;
			this.arrAirportCode = arrAirportCode;
			this.depTime = depTime;
			this.arrTime = arrTime;
			this.flightNo = flightNo;
			this.flightClass = flightClass;
			this.aircraftType = aircraftType;
		}
		
		public class Offseason extends Ticket {				//This sub of standard for off-season
			private String seasonStartDate;
			private String seasonEndDate;
			private double rebate;							//Rebate represented as a decimal number
			
			public String getSeasonStartDate() {						//Season start date getter
				return seasonStartDate;
			}
			
			public void setSeasonStartDate(String seasonStartDate) {	//Season start date setter
				this.seasonStartDate = seasonStartDate;
			}
			
			public String getSeasonEndDate() {							//Season end date getter
				return seasonEndDate;
			}
			
			public void setSeasonEndDate(String seasonEndDate) {		//Season end date setter
				this.seasonEndDate = seasonEndDate;
			}
			
			public double getRebate() {									//Rebate getter
				return rebate;
			}
			
			public void setRebate(double rebate) {						//Rebate setter
				this.rebate = rebate;
			}
			
			public Offseason(String code, String type, String depAirportCode, 		//Off-season ticket constructor
					String arrAirportCode, String depTime, String arrTime, 
					String flightNo, String flightClass, String aircraftType, 
					String seasonStartDate, String seasonEndDate, double rebate) {
				super(code, type, depAirportCode, arrAirportCode, depTime, arrTime, flightNo, flightClass, aircraftType);
				this.seasonStartDate = seasonStartDate;
				this.seasonEndDate = seasonEndDate;
				this.rebate = rebate;
			}
		}
		
		public class Awards extends Ticket { 				//This sub of standard for awards
			private int pointsPerMile;						//Is this the right data type?

			public int getPointsPerMile() {						//Points per mile getter
				return pointsPerMile;
			}

			public void setPointsPerMile(int pointsPerMile) {	//Points per mile setter
				this.pointsPerMile = pointsPerMile;
			}
			
			public Awards(String code, String type, String depAirportCode, 	//Awards ticket constructor
					String arrAirportCode, String depTime, String arrTime, 
					String flightNo, String flightClass, String aircraftType,
					int pointsPerMile) {
				super(code, type, depAirportCode, arrAirportCode, depTime, arrTime, flightNo, flightClass, aircraftType);
				this.pointsPerMile = pointsPerMile;
			}
			
		}
		
	}
	
	public class CheckedBaggage extends Product {
		private String ticketCode;							//Could an int type be used here?

		public String getTicketCode() {						//Ticket code getter
			return ticketCode;
		}

		public void setTicketCode(String ticketCode) {		//Ticket code setter
			this.ticketCode = ticketCode;
		}
		
		public CheckedBaggage(String code, String type, String ticketCode) {	//Checked baggage constructor
			super(code, type);
			this.ticketCode = ticketCode;
		}
	}
	
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
				String ageClasss, double costPerMile) {
			super(code, type);
			this.name = name;
			this.ageClass = ageClass;
			this.costPerMile = costPerMile;
		}
	}
	
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
	
	public class Refreshment extends Product {
		private String name;
		private double cost;
		
		public String getName() {						//Name getter
			return name;
		}
		
		public void setName(String name) {				//Name setter
			this.name = name;
		}
		
		public double getCost() {						//Cost getter
			return cost;
		}
		
		public void setCost(double cost) {				//Cost setter
			this.cost = cost;
		}
		
		public Refreshment(String code, String type,	//Refreshment constructor
				String name, double cost) {
			super(code, type);
			this.name = name;
			this.cost = cost;
		}
	}

}
