package com.airamerica.printer;

import com.airamerica.customer.Customer;
import com.airamerica.person.Person;

public class CustomerInfoPrinter {

		public void print(Customer c, Person salesperson) {
			System.out.println("CUSTOMER INFORMATION");
			String sales = salesperson.getLastName() + ", " + salesperson.getFirstName();
				String name = c.getName();
				String code = c.getCode();
				String type = c.getType();
				if(type.equals("G")) {			
					type = "[General]";
				}else if(type.equals("V")) {
					type = "[Government]";
				}else {
					type = "[Corporate]";
				}
				String contact = c.getPrimaryContact().getLastName() + ", " + c.getPrimaryContact().getFirstName();
				String street = c.getPrimaryContact().getAddress().getStreet();
				String city = c.getPrimaryContact().getAddress().getCity();
				String state = c.getPrimaryContact().getAddress().getState();
				String zip = c.getPrimaryContact().getAddress().getZipcode();
				String country = c.getPrimaryContact().getAddress().getCountry();
				System.out.printf("%-10s %-80s %n", " ", name + " (" + code + ")");
				System.out.printf("%-10s %-20s %n", " ", type);
				System.out.printf("%-10s %-80s %n", " ", contact);
				System.out.printf("%-10s %-80s %n", " ", street);
				System.out.printf("%-10s %-80s %n", " ", city + ", " + state + " " + zip);
				System.out.printf("%-10s %-80s %n", " ", country);
				System.out.printf("%-10s Salesperson: %-70s %n", " ", sales);
			
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
}
