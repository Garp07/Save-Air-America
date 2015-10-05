package com.airamerica.dataconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.airamerica.other.Address;
import com.airamerica.person.Person;

public class PersonConverter extends DataReader {
	
	//PersonConverter constructor
	public PersonConverter(String inputFile) {
		super(inputFile);
	}
	
	//Parse person.dat file
	public ArrayList<Person> parsePersons() {	
		
		ArrayList<Person> persons = new ArrayList<Person>();						
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//First line is number of persons
		int count = 0;
		try {
			count = Integer.parseInt(scanner.nextLine());	
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < count; i++) {
			String line = scanner.nextLine();
			if(!line.trim().isEmpty()) {
				Person person = null;
				person = this.parsePerson(line);
				persons.add(person);
			}
		}
		
		scanner.close();
		return persons;
	}
	
	private Person parsePerson(String personString) {
		/*
		 * item order
		 * personCode; name; address; (optional)phoneNumber; (optional)emailAddresses
		 */
		String tokens[] = personString.split(";");
		String personCode = tokens[0].trim();
		
			/*
			 * item order
			 * lastName, firstName
			 */
			String nameTokens[] = tokens[1].split(",");					
			String lastName = nameTokens[0].trim();							
			String firstName = nameTokens[1].trim();	
			
		Address address = this.parseAddress(tokens[2]);
		String phoneNumber = tokens[3].trim();
		
		ArrayList<String> emails = new ArrayList<String>();
		
		if(tokens.length == 5) {
			String emailTokens[] = tokens[4].split(",");					
			for(int i = 0; i < emailTokens.length; i++) {
				emails.add(emailTokens[i]);	
			}
		}
		
		Person person = new Person(personCode, firstName, lastName, address);
		
		if(phoneNumber.length() != 0) {
			person.setPhoneNumber(phoneNumber);
		}
		
		if(emails.size() != 0) {
			person.setEmails(emails);
		}
		
		return person;
	}
}
