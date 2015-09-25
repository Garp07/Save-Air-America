package dataconverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import classes.Address;
import classes.Person;

public class PersonConverter {
	private String inputFile;

	public String getInputFile() {													//InputFile getter
		return inputFile;
	}

	public void setInputFile(String inputFile) {									//InputFile setter
		this.inputFile = inputFile;
	}

	public PersonConverter(String inputFile) {										//Constructor
		this.inputFile = inputFile;
	}
	
	public ArrayList<Person> parsePersons() {										//Method for reading from a person file
		ArrayList<Person> persons = new ArrayList<Person>();						//Create an empty array list
		
		try{
			File f = new File(inputFile);
			Scanner s = new Scanner(f);
			int count = Integer.parseInt(s.nextLine());								//First line is a number = # of people
			for(int i = 0; i < count; i++) {										//Loop through rest of lines
				String line = s.nextLine();
				if(!line.trim().isEmpty()) {										//If line isn't blank
					Person human = null;											//Create an empty person object
					Address add = null;												//Create an empty address object
					String tokens[] = line.split(";");								//Split up the line at ;
					String personCode = tokens[0];									//First thing in a line should be person code
					String nameTokens[] = tokens[1].split(",");						//Names are connected by , so need to be split further
						String lastName = nameTokens[0];							//Last name first
						String firstName = nameTokens[1];							//First name last
					String addressTokens[] = tokens[2].split(",");					//Addresses are connected by , so need to be split further
						String street = addressTokens[0];							//First item of address is street, then....
						String city = addressTokens[1];								//city...
						String state = addressTokens[2];							//state...
						String zipCode = addressTokens[3];							//zip code...
						String country = addressTokens[4];							//and finally country
						String phoneNumber = null;
						ArrayList<String> emails = new ArrayList<String>();
					if(tokens.length == 4) {										//Case if either telephone number or emails is omitted
						if(!tokens[3].contains("@")) {								//Case if telephone number is given, but not email
							phoneNumber = tokens[3];							
						} else {
							String emailTokens[] = tokens[3].split(",");			//Multiple emails are split by commas
							for(int j = 0; j < emailTokens.length; j++) {
								emails.add(emailTokens[j]);							//Populate array with emails
							}
						}
					} else if(tokens.length == 5) {									//Case if both telephone and emails are provided
						phoneNumber = tokens[3];
						String emailTokens[] = tokens[4].split(",");					
						for(int j = 0; j < emailTokens.length; j++) {
							emails.add(emailTokens[j]);	
						}
					}
					
					add = new Address(street, city, state, zipCode, country);		//Populate address with attributes
					
					if(phoneNumber != null) {										//If we have a phone number
						human = new Person(personCode, firstName, lastName, add, phoneNumber);
					} else {
						human = new Person(personCode, firstName, lastName, add);	//If we don't have a phone number
					}
					
					if(emails.size() != 0) {										//If we have emails
						human.addEmail(emails);
					}
					
					persons.add(human);
				}
			}
			s.close();	
		} catch(Exception crap){
			crap.printStackTrace();
		}
		return persons;
	}
}
