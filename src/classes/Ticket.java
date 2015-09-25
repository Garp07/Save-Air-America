package classes;

public class Ticket extends Product {					//This sub for standard tickets
	private String depAirportCode;						//These refer to airports, maybe need to be of class Airport? Yep
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

	public Ticket(String code, String type, String depAirportCode, String arrAirportCode, String depTime,			//Ticket constructor
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

}
