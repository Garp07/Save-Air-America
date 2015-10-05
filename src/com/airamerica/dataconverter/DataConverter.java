package com.airamerica.dataconverter;

import java.util.ArrayList;

import com.airamerica.airport.Airport;
import com.airamerica.customer.Customer;
import com.airamerica.person.Person;
import com.airamerica.product.Product;

public class DataConverter {

	public static void main(String[] args) {
			
		//Create array list of airports
		AirportConverter airports = new AirportConverter("data/Airports.dat");					
		ArrayList<Airport> airportsArray = airports.parseAirports();
		
		System.out.println("HELLO");
		
		//Create array list of persons
		PersonConverter persons = new PersonConverter("data/Persons.dat");						
		ArrayList<Person> personsArray = persons.parsePersons();
		
		//Create array list of products
		ProductConverter products = new ProductConverter("data/Products.dat", airportsArray);					
		ArrayList<Product> productsArray = products.parseProducts();
		
		//Create array list of customers
		CustomerConverter customers = new CustomerConverter("data/Customers.dat", personsArray);				
		ArrayList<Customer> customersArray = customers.parseCustomers();
		
		//prints to XML
		PrintToXML.PrintAirportsXML(airportsArray);
		PrintToXML.PrintPersonsXML(personsArray);
		PrintToXML.PrintProductsXML(productsArray);
		PrintToXML.PrintCustomersXML(customersArray);
	}
	
	
}
