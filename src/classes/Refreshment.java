package classes;

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