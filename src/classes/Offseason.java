package classes;

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
	
	public Offseason(String code, String type, String seasonStartDate, 				//Offseason constructor
			String seasonEndDate, Airport depAirport, 
			Airport arrAirport, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType, 
			double rebate) {
		super(code, type, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.seasonStartDate = seasonStartDate;
		this.seasonEndDate = seasonEndDate;
		this.rebate = rebate;
	}
}
