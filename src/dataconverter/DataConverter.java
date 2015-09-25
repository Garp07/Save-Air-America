package dataconverter;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import classes.Airport;
import classes.Customer;
import classes.Person;
import classes.Product;

public class DataConverter {

	public static void main(String[] args) {
		AirportConverter airports = new AirportConverter("data/Airports.dat");					//Create array list of airports
		ArrayList<Airport> airportsArray = airports.parseAirports();
		
		PersonConverter persons = new PersonConverter("data/Persons.dat");						//Create array list of persons
		ArrayList<Person> personsArray = persons.parsePersons();
		
		ProductConverter products = new ProductConverter("data/Products.dat");					//Create array list of products
		ArrayList<Product> productsArray = products.parseProducts();
		
		CustomerConverter customers = new CustomerConverter("data/Customers.dat");				//Create array list of customers
		//assign persons ArrayList to the customer converter
		customers.setPersons(personsArray);
		ArrayList<Customer> customersArray = customers.parseCustomers();
		
		
		PrintAirportsXML(airportsArray);
		PrintPersonsXML(personsArray);
		PrintProductsXML(productsArray);
		PrintCustomersXML(customersArray);
			
	}
	
	//prints airports to XML file
	public static void PrintAirportsXML(ArrayList<Airport> Airports) {
		
		XStream xstream = new XStream();

		xstream.alias("airport", Airport.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/AirportOutput.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<airports>\n");

		for(Airport a : Airports) {
			pw.print(xstream.toXML(a) + "\n");
		}
		
		pw.print("</airports>" + "\n");
		pw.close();

		System.out.println("XML generated at 'data/AirportOutput.xml'");
	}
	
	//prints persons to XML file
	public static void PrintPersonsXML(ArrayList<Person> Persons) {
		
		XStream xstream = new XStream();

		xstream.alias("person", Person.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/PersonOutput.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<persons>\n");

		for(Person p : Persons) {
			pw.print(xstream.toXML(p) + "\n");
		}
		
		pw.print("</persons>" + "\n");
		pw.close();

		System.out.println("XML generated at 'data/PersonsOutput.xml'");
	}
	
	//prints products to XML file
	public static void PrintProductsXML(ArrayList<Product> Products) {
		
		XStream xstream = new XStream();

		xstream.alias("product", Product.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/ProductOutput.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<products>\n");

		for(Product p : Products) {
			pw.print(xstream.toXML(p) + "\n");
		}
		
		pw.print("</products>" + "\n");
		pw.close();

		System.out.println("XML generated at 'data/ProductsOutput.xml'");
	}
	
	//prints products to XML file
	public static void PrintCustomersXML(ArrayList<Customer> Customers) {
		
		XStream xstream = new XStream();

		xstream.alias("customer", Customer.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/CustomerOutput.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<customers>\n");

		for(Customer p : Customers) {
			pw.print(xstream.toXML(p) + "\n");
		}
		
		pw.print("</customers>" + "\n");
		pw.close();

		System.out.println("XML generated at 'data/CustomersOutput.xml'");
	}

}
