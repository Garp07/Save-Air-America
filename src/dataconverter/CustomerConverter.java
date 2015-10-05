package dataconverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.airamerica.customer.Customer;

import classes.Person;

public class CustomerConverter {
	private ArrayList<Person> Persons;
	private String inputFile;
	
	public void setPersons(ArrayList<Person> Persons) {
		this.Persons = Persons;
	}
	
	public String getInputFile() {																//Input file getter
		return inputFile;
	}

	public void setInputFile(String inputFile) {												//Input file setter
		this.inputFile = inputFile;
	}

	public CustomerConverter(String inputFile, ArrayList<Person> Persons) {						//Constructor
		this.inputFile = inputFile;
		this.Persons = Persons;
	}
	
	public ArrayList<Customer> parseCustomers() {												//Method for reading customers file
		ArrayList<Customer> customers = new ArrayList<Customer>();								//Create an empty array list
		
		try{
			File f = new File(inputFile);
			Scanner s = new Scanner(f);
			int count = Integer.parseInt(s.nextLine());											//First line is number of customers
			for(int i = 0; i < count; i++) {
				String line = s.nextLine();
				if(!line.trim().isEmpty()) {													//If line isn't blank
					String tokens[] = line.split(";");											//Split lines at ;
					String code = tokens[0];													//First item is a customer code
					String type = tokens[1];													//Customer type
					String primaryContactCode = tokens[2];											//Primary contact person code
					String name = tokens[3];													//Customer name
					double airlineMiles = 0.0;
					
					//optional field
					if(tokens.length == 5) {
						airlineMiles = Double.parseDouble(tokens[4]);								//Airline miles
					}
					
					Person primaryContact = null;
					
					//must set persons ArrayList before this method will work
					for(Person p : Persons) {
						if(p.getPersonCode().equals(primaryContactCode)) {
							primaryContact = p;
						}
					}
				
					Customer customer = new Customer(code, type, primaryContact, name, airlineMiles);		//Customer object
				
					customers.add(customer);
				}
			}
			s.close();
		} catch(Exception drat) {
			drat.printStackTrace();
		}
		return customers;
	}
}
