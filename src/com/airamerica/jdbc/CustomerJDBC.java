package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.airamerica.address.Address;
import com.airamerica.customer.Customer;
import com.airamerica.person.Person;

public class CustomerJDBC {
	
	public static ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//customer
		String code, type, name;
		Person primaryContact = null;
		double airlineMiles;
		int primaryContactID;
		Customer c = null;
		
		String selectCustomer = "SELECT CustomerCode, CustomerType, PrimaryContactID, CustomerName, AirlineMiles "
				+ "FROM Customers;";
		
		try {
			ps = conn.prepareStatement(selectCustomer);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				code = rs.getString("CustomerCode");
				type = rs.getString("CustomerType");
				name = rs.getString("CustomerName");
				airlineMiles = rs.getDouble("AirlineMiles");
				primaryContactID = rs.getInt("PrimaryContactID");
				
				primaryContact = PersonJDBC.getPerson(primaryContactID);
				
				c = new Customer(code, primaryContact, name, airlineMiles);
				c.setType(type);
				customers.add(c);
				
			} 
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(rs != null)
				try { rs.close(); } catch(SQLException ignored) {}
			if(ps != null)
				try { ps.close(); } catch(SQLException ignored) {}
			if(conn != null)
				try { conn.close(); } catch(SQLException ignored) {}
		}		
		return customers;
	}
	
}