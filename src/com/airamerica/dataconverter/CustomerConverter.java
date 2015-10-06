package com.airamerica.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.airamerica.customer.Corporate;
import com.airamerica.customer.Customer;
import com.airamerica.customer.General;
import com.airamerica.customer.Government;
import com.airamerica.person.Person;

public class CustomerConverter extends DataReader {
	private ArrayList<Person> persons;
	
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}
	
	public ArrayList<Person> getPersons() {
		return persons;
	}

	//CustomerConverter constructor
	public CustomerConverter(String inputFile, ArrayList<Person> persons) {						
		super(inputFile);
		this.persons = persons;
	}
	
	//Parse customers.dat file
	public ArrayList<Customer> parseCustomers() {												
		ArrayList<Customer> customers = new ArrayList<Customer>();								
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//First line is number of customers
		int count = 0;
		try {
			count = Integer.parseInt(scanner.nextLine());	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < count; i++) {
			String line = scanner.nextLine();
			if(!line.trim().isEmpty()) {
				Customer customer = null;
				customer = this.parseCustomer(line);
				customers.add(customer);
			}
		}
		
		scanner.close();
		return customers;
	}
	
	private Customer parseCustomer(String customerString) {
		/*
		 * Item order
		 * code; type; primaryContact; name; (optional)airlineMiles
		 */	
		String tokens[] = customerString.split(";");							
		String code = tokens[0];													
		String type = tokens[1];												
		String primaryContactCode = tokens[2];									
		String name = tokens[3];												
		double airlineMiles = 0.0;
		
		//optional field
		if(tokens.length == 5) {
			try {
				airlineMiles = Double.parseDouble(tokens[4]);					
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		Person primaryContact = null;
		
		for(Person p : persons) {
			if(p.getCode() == primaryContactCode) {
				primaryContact = p;
			}
		}
		
		Customer customer = null;
		switch(type) {
			case "V":
				customer = new Government(code, primaryContact, name, airlineMiles);
				break;
			case "G":
				customer = new General(code, primaryContact, name, airlineMiles);
				break;
			case "C":
				customer = new Corporate(code, primaryContact, name, airlineMiles);
				break;
			default:
				System.out.println("Invalid customer type\n");
				break;
		}
		
		return customer;
	}
}
