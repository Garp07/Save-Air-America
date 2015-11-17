package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.airamerica.address.Address;
import com.airamerica.airport.Airport;
import com.airamerica.utils.NullString;

public class AirportJDBC {
	
//	public static ArrayList<Airport> getAirports() {
//		ArrayList<Airport> airports = new ArrayList<Airport>();
//		
//		Connection conn = DatabaseInfo.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		//airport
//		String airportCode, name;
//		int latDegs, latMins, lonDegs, lonMins;
//		double passengerFacilityFee;
//		
//		int addressID;
//		Address address = null;
//	
//		
//		String selectAirport = "SELECT AddressID, AirportCode, AirportName, LatitudeDegrees, LatitudeMinutes, "
//				+ "LongitudeDegrees, LongitudeMinutes, FacilityFee, "
//				+ "FROM Airports;";
//		
//		try {
//			ps = conn.prepareStatement(selectAirport);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				airportCode = rs.getString("AirportCode");
//				name = rs.getString("AirportName");
//				latDegs = rs.getInt("LatitudeDegrees");
//				latMins = rs.getInt("LatitudeMinutes");
//				lonDegs = rs.getInt("LongitudeDegrees");
//				lonMins = rs.getInt("LongitudeMinutes");
//				passengerFacilityFee = rs.getDouble("FacilityFee");
//				
//				addressID = rs.getInt("AddressID");
//				
//				address = AddressJDBC.getAddress(addressID);
//				
//				airports.add(new Airport(airportCode, name, address, latDegs, latMins, lonDegs, lonMins, passengerFacilityFee));
//				
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("SQLException: ");
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} finally {
//			if(ps != null)
//				try { ps.close(); } catch(SQLException ignored) {}
//			if(conn != null)
//				try { conn.close(); } catch(SQLException ignored) {}
//		}
//		
//		return airports;
//		
//	}
	
	public static Airport getAirport(int airportID) {
		Airport airport = null;
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//airport
		String airportCode, name;
		int latDegs, latMins, lonDegs, lonMins;
		double passengerFacilityFee;
		
		int addressID;
		Address address = null;
	
		
		String selectAirport = "SELECT AddressID, AirportCode, AirportName, LatitudeDegrees, LatitudeMinutes, "
				+ "LongitudeDegrees, LongitudeMinutes, FacilityFee "
				+ "FROM Airports WHERE AirportID = ?;";
		
		try {
			ps = conn.prepareStatement(selectAirport);
			ps.setInt(1, airportID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				airportCode = NullString.CheckNullString(rs.getString("AirportCode"));
					
				name = NullString.CheckNullString(rs.getString("AirportName"));
				
				latDegs = rs.getInt("LatitudeDegrees");
				latMins = rs.getInt("LatitudeMinutes");
				lonDegs = rs.getInt("LongitudeDegrees");
				lonMins = rs.getInt("LongitudeMinutes");
				passengerFacilityFee = rs.getDouble("FacilityFee");
				addressID = rs.getInt("AddressID");
				
				address = AddressJDBC.getAddress(addressID);
				
				airport = new Airport(airportCode, name, address, latDegs, latMins, lonDegs, lonMins, passengerFacilityFee);
				
			} else {
				airport = new Airport("---", "---", AddressJDBC.getAddress(0), 0, 0, 0, 0, 0);
//				throw new SQLException("No associated airport.");
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
		
		return airport;
		
	}
	
}
