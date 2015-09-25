package classes;

public class Awards extends Ticket { 				//This sub of standard for awards
	private double pointsPerMile;						//Is this the right data type?

	public double getPointsPerMile() {						//Points per mile getter
		return pointsPerMile;
	}

	public void setPointsPerMile(double pointsPerMile) {	//Points per mile setter
		this.pointsPerMile = pointsPerMile;
	}
	
	public Awards(String code, String type, Airport depAirport, 	//Awards ticket constructor
			Airport arrAirport, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType,
			double pointsPerMile) {
		super(code, type, depAirport, arrAirport, depTime, arrTime, flightNo, flightClass, aircraftType);
		this.pointsPerMile = pointsPerMile;
	}
	
}