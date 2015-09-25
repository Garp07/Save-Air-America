package dataconverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Customer;

public class CustomerConverter {
	private String inputFile;

	public String getInputFile() {																//Input file getter
		return inputFile;
	}

	public void setInputFile(String inputFile) {												//Input file setter
		this.inputFile = inputFile;
	}

	public CustomerConverter(String inputFile) {												//Constructor
		this.inputFile = inputFile;
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
					Customer client = null;														//Create empty instance of customer
					String tokens[] = line.split(";");											//Split lines at ;
					String code = tokens[0];													//First item is a customer code
					String type = tokens[1];													//Customer type
					String primaryContact = tokens[2];											//Primary contact person code
					String name = tokens[3];													//Customer name
					int airlineMiles = Integer.parseInt(tokens[4]);								//Airline miles
				
					client = new Customer(code, type, primaryContact, name, airlineMiles);		//Customer object
				
					customers.add(client);
				}
			}
			s.close();
		} catch(Exception drat) {
			drat.printStackTrace();
		}
		return customers;
	}
}
