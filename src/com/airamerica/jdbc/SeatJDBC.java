package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.airamerica.person.Person;
import com.airamerica.product.ticket.Seat;
import com.airamerica.utils.NullString;

public class SeatJDBC {
	
	public static ArrayList<Seat> getSeats(int productID) {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//seat
		String seatNumber, idNumber, nationality;
		int age, personID;
		Person person = null;

		String selectAirport = "SELECT SeatNumber, PersonID, IDNumber, Age, Nationality "
				+ "FROM Seats a JOIN InvoiceProducts b ON a.InvoiceProductsID = b.InvoiceProductsID "
				+ "WHERE b.InvoiceID = ?;";

//		String selectAirport = "SELECT SeatNumber, PersonID, IDNumber, Age, Nationality, "
//				+ "FROM Seats a RIGHT JOIN InvoiceProducts b ON a.InvoiceProductsID = b.InvoiceProductsID "
//				+ "WHERE b.ProductID = ?;";
		
		try {
			ps = conn.prepareStatement(selectAirport);
			ps.setInt(1, productID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				seatNumber = NullString.CheckNullString(rs.getString("SeatNumber"));
				
				idNumber = NullString.CheckNullString(rs.getString("IDNumber"));
				
				nationality = NullString.CheckNullString(rs.getString("Nationality"));
				
				age = rs.getInt("Age");
				
				personID = rs.getInt("PersonID");
			
				person = PersonJDBC.getPerson(personID);
				
				seats.add(new Seat(seatNumber, person, idNumber, age, nationality));
				
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
		
		return seats;
		
	}
	
}
