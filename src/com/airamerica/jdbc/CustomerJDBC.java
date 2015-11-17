package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.airamerica.customer.Corporate;
import com.airamerica.customer.Customer;
import com.airamerica.customer.General;
import com.airamerica.customer.Government;
import com.airamerica.person.Person;
import com.airamerica.utils.NullString;

public class CustomerJDBC {
	
//	public static ArrayList<Customer> getCustomers() {
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//		
//		Connection conn = DatabaseInfo.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		//customer
//		String code, type, name;
//		Person primaryContact = null;
//		double airlineMiles;
//		int primaryContactID;
//		Customer c = null;
//		
//		String selectCustomer = "SELECT CustomerCode, CustomerType, PrimaryContactID, CustomerName, AirlineMiles "
//				+ "FROM Customers;";
//		
//		try {
//			ps = conn.prepareStatement(selectCustomer);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				
//				code = rs.getString("CustomerCode");
//				type = rs.getString("CustomerType");
//				name = rs.getString("CustomerName");
//				airlineMiles = rs.getDouble("AirlineMiles");
//				primaryContactID = rs.getInt("PrimaryContactID");
//				
//				primaryContact = PersonJDBC.getPerson(primaryContactID);
//				
//				if (type.equals("G")) {
//					c = new General(code, primaryContact, name, airlineMiles);
//				} else if (type.equals("V")) {
//					c = new Government(code, primaryContact, name, airlineMiles);
//				} else {
//					c = new Corporate(code, primaryContact, name, airlineMiles);
//				}
//				
//				customers.add(c);
//				
//			} 
//			
//		} catch (SQLException e) {
//			System.out.println("SQLException: ");
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} finally {
//			if(rs != null)
//				try { rs.close(); } catch(SQLException ignored) {}
//			if(ps != null)
//				try { ps.close(); } catch(SQLException ignored) {}
//			if(conn != null)
//				try { conn.close(); } catch(SQLException ignored) {}
//		}		
//		return customers;
//	}
	
	public static Customer getCustomer(int customerID) {
		Customer customer = null;
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//customer
		String code, type, name;
		Person primaryContact = null;
		double airlineMiles;
		int primaryContactID;
		
		String selectCustomer = "SELECT CustomerCode, CustomerType, PrimaryContactID, CustomerName, AirlineMiles "
				+ "FROM Customers WHERE CustomerID = ?;";
		
		try {
			ps = conn.prepareStatement(selectCustomer);
			ps.setInt(1, customerID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				code = NullString.CheckNullString(rs.getString("CustomerCode"));

				type = NullString.CheckNullString(rs.getString("CustomerType"));

				name = NullString.CheckNullString(rs.getString("CustomerName"));
					
				airlineMiles = rs.getDouble("AirlineMiles");
				primaryContactID = rs.getInt("PrimaryContactID");
				
				primaryContact = PersonJDBC.getPerson(primaryContactID);
				
				if (type.equals("G")) {
					customer = new General(code, primaryContact, name, airlineMiles);
				} else if (type.equals("V")) {
					customer = new Government(code, primaryContact, name, airlineMiles);
				} else {
					customer = new Corporate(code, primaryContact, name, airlineMiles);
				}
			
			} else {
				customer = new General("---", PersonJDBC.getPerson(0), "---", 0);
//				throw new SQLException("No associated customer.");
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
		return customer;
	}
	
}