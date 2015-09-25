package dataconverter;

import java.util.ArrayList;

import classes.Airport;
import classes.Customer;
import classes.Person;
import classes.Product;

public class DataConverter {

	public static void main(String[] args) {
		AirportConverter airports = new AirportConverter("data/Airports.dat");					//Create array list of airports
		ArrayList<Airport> airportsArray = airports.parseAirports();
		
		PersonConverter people = new PersonConverter("data/Persons.dat");						//Create array list of persons
		ArrayList<Person> peopleArray = people.parsePersons();
		
		ProductConverter products = new ProductConverter("data/Products.dat");					//Create array list of products
		ArrayList<Product> productsArray = products.parseProducts();
		
		CustomerConverter customers = new CustomerConverter("data/Customers.dat");				//Create array list of customers
		ArrayList<Customer> customrsArray = customers.parseCustomers();

	}

}
