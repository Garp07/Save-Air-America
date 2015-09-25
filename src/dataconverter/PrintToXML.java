package dataconverter;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;

import classes.Airport;
import classes.Customer;
import classes.Person;
import classes.Product;

public class PrintToXML {
	//prints airports to XML file
	public static void PrintAirportsXML(ArrayList<Airport> Airports) {
		
		XStream xstream = new XStream();

		xstream.alias("airport", Airport.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/Airports.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<airports>\n");

		for(Airport a : Airports) {
			pw.print(xstream.toXML(a).replaceAll("&apos;", "'").replaceAll("&amp;", "&") + "\n");
		}
		
		pw.print("</airports>" + "\n");
		pw.close();

	}
	
	//prints persons to XML file
	public static void PrintPersonsXML(ArrayList<Person> Persons) {
		
		XStream xstream = new XStream();

		xstream.alias("person", Person.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/Persons.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<persons>\n");

		for(Person p : Persons) {
			pw.print(xstream.toXML(p) + "\n");
		}
		
		pw.print("</persons>" + "\n");
		pw.close();

	}
	
	//prints products to XML file
	public static void PrintProductsXML(ArrayList<Product> Products) {
		
		XStream xstream = new XStream();

		xstream.alias("product", Product.class);
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/Products.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<products>\n");

		for(Product p : Products) {
			pw.print(xstream.toXML(p) + "\n");
		}
		
		pw.print("</products>" + "\n");
		pw.close();

	}
	
	//prints products to XML file
	public static void PrintCustomersXML(ArrayList<Customer> Customers) {
		
		XStream xstream = new XStream();
		
		
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File("data/Customers.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		pw.print("<customers>\n");

		for(Customer c : Customers) {
			xstream.alias(c.getType(), Customer.class);
			//xstream.aliasField(c.getType(), Customer.class, "customer");
			pw.print(xstream.toXML(c) + "\n");
		}
		
		pw.print("</customers>" + "\n");
		pw.close();

	}

}
