package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.airamerica.address.Address;
import com.airamerica.airport.Airport;

public class AirportJDBC {
	
	public static ArrayList<Airport> getAirports() {
		ArrayList<Airport> airports = new ArrayList<Airport>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//airport
		String airportCode, name;
		int latDegs, latMins, lonDegs, lonMins;
		double passengerFacilityFee;
		
		//address
		String street, city, state, zipcode, country;
		
		String selectAirport = "SELECT a.AirportCode, a.AirportName, a.LatitudeDegrees, a.LatitudeMinutes, "
				+ "a.LongitudeDegrees, a.LongitudeMinutes, a.FacilityFee, "
				+ "b.Street, b.City, b.State, b.Zipcode, b.Country "
				+ "FROM Airports a "
				+ "LEFT JOIN Addresses b "
				+ "ON a.AddressID = b.AddressID;";
		
		try {
			ps = conn.prepareStatement(selectAirport);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				airportCode = rs.getString("AirportCode");
				name = rs.getString("AirportName");
				latDegs = rs.getInt("LatitudeDegrees");
				latMins = rs.getInt("LatitudeMinutes");
				lonDegs = rs.getInt("LongitudeDegrees");
				lonMins = rs.getInt("LongitudeMinutes");
				passengerFacilityFee = rs.getDouble("FacilityFee");
				
				street = rs.getString("Street");
				city = rs.getString("City");
				state = rs.getString("State");
				zipcode = rs.getString("Zipcode");
				country = rs.getString("Country");
				
				airports.add(new Airport(airportCode, name, new Address(street, city, state, zipcode, country), latDegs, latMins, lonDegs, lonMins, passengerFacilityFee));
				
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(ps != null)
				try { ps.close(); } catch(SQLException ignored) {}
			if(conn != null)
				try { conn.close(); } catch(SQLException ignored) {}
		}
		
		return airports;
		
	}
	
}
