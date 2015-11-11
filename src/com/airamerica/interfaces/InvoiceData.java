package com.airamerica.interfaces;

import com.airamerica.jdbc.DatabaseInfo;
import java.sql.*;

/* Assignment 5 - (Phase IV) */
/* NOTE: Do not change the package name or any of the method signatures.
 *  
 * There are 23 methods in total, all of which need to be completed as a 
 * bare minimum as part of the assignment.You can add additional methods 
 * for testing if you feel.
 * 
 * It is also recommended that you write a separate program to read
 * from the .dat files and test these methods to insert data into your 
 * database.
 * 
 * Do not forget to change your reports generation classes to read from 
 * your database instead of the .dat files.
 */

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		
		String deleteAllPersons = "DELETE FROM Persons;";
		String disableFK = "SET foreign_key_checks = 0;";
		String enableFK = "SET foreign_key_checks = 1;";
		
		try {
			//disable foreign key checks
			stmt = conn.createStatement();
			stmt.executeUpdate(disableFK);
			
			//delete all persons
			ps = conn.prepareStatement(deleteAllPersons);
			ps.executeUpdate();
			
			//re-enable foreign key checks
			stmt.executeUpdate(enableFK);
			
			//close things
			if(stmt != null) 
				stmt.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * Method to add a person record to the database with the provided data. 
	 */
	public static void addPerson(String personCode, String firstName, String lastName, 
			String phoneNo, String street, String city, String state, 
			String zip, String country) { 
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		
		addAddress(street, city, state, zip, country);
		int addressID = getAddressID(street, city, state, zip, country);
		
		String insertPerson = "INSERT INTO Persons(PersonCode, FirstName, LastName, AddressID, PhoneNumber);"
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(insertPerson);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, addressID);
			ps.setString(5, phoneNo);
			ps.executeUpdate();
			
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * Method to return an addressID given the provided data
	 */
	private static int getAddressID(String street, String city, String state, String zip, String country) {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectAddress = "SELECT AddressID FROM AddressID WHERE"
				+ "Street LIKE ? AND"
				+ "City LIKE ? AND"
				+ "State LIKE ? AND"
				+ "Zipcode LIKE ? AND"
				+ "Country LIKE ?;";
		int id = 0;
		
		try {
			ps = conn.prepareStatement(selectAddress);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getInt("AddressID");
			}
			
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return id;
	}	
	/**
	 * Adds an address to the database with the provided data
	 */
	private static void addAddress(String street, String city, String state, String zip, String country) {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		
		String insertAddress = "INSERT INTO Address(Street, City, State, Zipcode, Country)"
				+ "VALUES (?, ?, ?, ?, ?);";
		
		try {
			ps = conn.prepareStatement(insertAddress);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			
			ps.executeUpdate();
			
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * Method that removes every airport record from the database
	 */
	public static void removeAllAirports() { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		
		String deleteAllAirports = "DELETE FROM Airports;";
		String disableFK = "SET foreign_key_checks = 0;";
		String enableFK = "SET foreign_key_checks = 1;";
		
		try {
			//disable foreign key checks
			stmt = conn.createStatement();
			stmt.executeUpdate(disableFK);
			
			//delete all airports
			ps = conn.prepareStatement(deleteAllAirports);
			ps.executeUpdate();
			
			//re-enable foreign key checks
			stmt.executeUpdate(enableFK);
			
			//close things
			if(stmt != null) 
				stmt.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Method to add a airport record to the database with the provided data. 
	 */
	public static void addAirport(String airportCode, String name, String street, 
			String city, String state, String zip, String country, 
			int latdegs, int latmins, int londegs, int lonmins, 
			double passengerFacilityFee) { 
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		
		addAddress(street, city, state, zip, country);
		int addressID = getAddressID(street, city, state, zip, country);
		
		String insertAirport = "INSERT INTO Airports(AirportCode, AirportName, AddressID, LatitudeDegrees, "
				+ "LatitudeMinutes, LongitudeDegrees, LongitudeMinutes, FacilityFee)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(insertAirport);
			ps.setString(1, airportCode);
			ps.setString(2, name);
			ps.setInt(3, addressID);
			ps.setInt(4, latdegs);
			ps.setInt(5, latmins);
			ps.setInt(6, londegs);
			ps.setInt(7, lonmins);
			ps.setDouble(8, passengerFacilityFee);
			
			ps.executeUpdate();
			
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 */
	public static void addEmail(String personCode, String email) { }
	
	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		
		String deleteAllCustomers = "DELETE FROM Customers;";
		String disableFK = "SET foreign_key_checks = 0;";
		String enableFK = "SET foreign_key_checks = 1;";
		
		try {
			//disable foreign key checks
			stmt = conn.createStatement();
			stmt.executeUpdate(disableFK);
			
			//delete all customers
			ps = conn.prepareStatement(deleteAllCustomers);
			ps.executeUpdate();
			
			//re-enable foreign key checks
			stmt.executeUpdate(enableFK);
			
			//close things
			if(stmt != null) 
				stmt.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method to add a customer record to the database with the provided data. 
	 */
	public static void addCustomer(String customerCode, String customerType, 
			String primaryContactPersonCode, String name, 
			int airlineMiles) {	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		
		String deleteAllProducts = "DELETE FROM Products;";
		String disableFK = "SET foreign_key_checks = 0;";
		String enableFK = "SET foreign_key_checks = 1;";
		
		try {
			//disable foreign key checks
			stmt = conn.createStatement();
			stmt.executeUpdate(disableFK);
			
			//delete all products
			ps = conn.prepareStatement(deleteAllProducts);
			ps.executeUpdate();
			
			//re-enable foreign key checks
			stmt.executeUpdate(enableFK);
			
			//close things
			if(stmt != null) 
				stmt.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Adds an standardTicket record to the database with the
	 * provided data.  
	 */
	public static void addStandardTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, String aircraftType) { }
	
	 /** 
	 * Adds an offSeasonTicket record to the database with the
	 * provided data.  
	 */
	public static void addOffSeasonTicket(String productCode, String seasonStartDate, 
			String seasonEndDate, String depAirportCode, String arrAirportCode, 
			String depTime, String arrTime,	String flightNo, String flightClass, 
			String aircraftType, double rebate) { }
	 
	 /** Adds an awardsTicket record to the database with the
	 * provided data.  
	 */
	public static void addAwardsTicket(String productCode,String depAirportCode, 
			String arrAirportCode, String depTime, String arrTime, 
			String flightNo, String flightClass, 
			String aircraftType, double pointsPerMile) { } 
	
	/**
	 * Adds a CheckedBaggage record to the database with the
	 * provided data.  
	 */
	public static void addCheckedBaggage(String productCode, String ticketCode) { }

	/**
	 * Adds a Insurance record to the database with the
	 * provided data.  
	 */
	public static void addInsurance(String productCode, String productName, 
			String ageClass, double costPerMile) {	}
	
	/**
	 * Adds a SpecialAssistance record to the database with the
	 * provided data.  
	 */
	public static void addSpecialAssistance(String productCode, String assistanceType) { }

	/**
	 * Adds a refreshment record to the database with the
	 * provided data.  
	 */
	public static void addRefreshment(String productCode, String name, double cost) { }
	
	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() { 
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		
		String deleteAllInvoices = "DELETE FROM Invoices;";
		String disableFK = "SET foreign_key_checks = 0;";
		String enableFK = "SET foreign_key_checks = 1;";
		
		try {
			//disable foreign key checks
			stmt = conn.createStatement();
			stmt.executeUpdate(disableFK);
			
			//delete all Invoices
			ps = conn.prepareStatement(deleteAllInvoices);
			ps.executeUpdate();
			
			//re-enable foreign key checks
			stmt.executeUpdate(enableFK);
			
			//close things
			if(stmt != null) 
				stmt.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, 
			String salesPersonCode, String invoiceDate) { }
	/**
	 * Adds a particular Ticket (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * additional details
	 */
	public static void addTicketToInvoice(String invoiceCode, String productCode, 
			String travelDate, String ticketNote) { }
	/**
	 * Adds a Passenger information to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> 
	 */
	public static void addPassengerInformation(String invoiceCode, String productCode, 
			String personCode, 
			String identity, int age, String nationality, String seat){ }
	/**
	 * Adds an Insurance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity and associated ticket information
	 */
	public static void addInsuranceToInvoice(String invoiceCode, String productCode, 
			int quantity, String ticketCode) { }
	/**
	 * Adds a CheckedBaggage Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addCheckedBaggageToInvoice(String invoiceCode, String productCode, 
			int quantity) { }
	/**
	 * Adds a SpecialAssistance Service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addSpecialAssistanceToInvoice(String invoiceCode, String productCode, 
			String personCode) { }
	/**
	 * Adds a Refreshment service (corresponding to <code>productCode</code>) to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 */
	public static void addRefreshmentToInvoice(String invoiceCode, 
			String productCode, int quantity) { }
}