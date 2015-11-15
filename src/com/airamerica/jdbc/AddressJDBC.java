package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.airamerica.address.Address;

public class AddressJDBC {
	
	public static Address getAddress(int addressID) {
		Address address = null;
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//address
		String street, city, state, zipcode, country;
		
		String selectAddress = "SELECT Street, City, State, Zipcode, Country "
				+ "FROM Addresses WHERE AddressID = ?;";
		
		try {
			ps = conn.prepareStatement(selectAddress);
			
			ps.setInt(1, addressID);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				street = rs.getString("Street");
				city = rs.getString("City");
				state = rs.getString("State");
				zipcode = rs.getString("Zipcode");
				country = rs.getString("Country");
				
				address = new Address(street, city, state, zipcode, country);
				
			} else {
				throw new SQLException("No Address");
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
		return address;
	}
	
}
