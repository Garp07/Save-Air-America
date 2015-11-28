package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.DateTime;

import com.airamerica.airport.Airport;
import com.airamerica.product.ticket.AwardTicket;
import com.airamerica.product.ticket.BusinessClass;
import com.airamerica.product.ticket.Economy;
import com.airamerica.product.ticket.EconomyPremium;
import com.airamerica.product.ticket.FlightClass;
import com.airamerica.product.ticket.OffseasonTicket;
import com.airamerica.product.ticket.Seat;
import com.airamerica.product.ticket.StandardTicket;
import com.airamerica.product.ticket.Ticket;
import com.airamerica.utils.NullString;

public class TicketJDBC {
	
	public static ArrayList<Ticket> getTickets(int invoiceID) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//ticket
//		String flightNo, flightClass, aircraftType, ticketNote, type, code;
//		DateTime depTime, arrTime, seasonStart, seasonEnd, travelDate;
//		int depAirportID, arrAirportID, productID;
//		double rebate, pointsPerMile;
//		
//		Airport arrAirport = null;
//		Airport depAirport = null;
//		FlightClass fc = null;
//		ArrayList<Seat> seats = new ArrayList<Seat>();
		int ticketID = -1;
		Ticket ticket = null;
		
		String selectTickets = "SELECT a.TicketID FROM Tickets a "
				+ "JOIN Products b ON a.TicketID = b.TicketID "
				+ "JOIN InvoiceProducts c on b.ProductID = c.ProductID "
				+ "WHERE b.ServiceID IS NULL "
				+ "AND c.InvoiceID = ?;";
		
//		String selectTicket = "SELECT DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, "
//				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile, "
//				+ "TicketNote, ProductType, ProductCode, TravelDate, b.ProductID FROM Tickets a "
//				+ "JOIN Products b ON a.TicketID = b.TicketID "
//				+ "JOIN InvoiceProducts c ON b.ProductID = c.ProductID "
//				+ "WHERE c.InvoiceID = ? AND b.ServiceID IS NULL;";

//				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile "
//				+ "TravelDate, TicketNote, ProductType, ProductCode, ProductID "
//				+ "FROM InvoiceProducts a LEFT JOIN Products b ON a.ProductID = b.ProductID LEFT JOIN Tickets c ON b.TicketID = c.TicketID "
//				+ "WHERE a.InvoiceID = ?;";
		
		
//				+ "FROM InvoiceProducts a JOIN Products b ON a.ProductID = b.ProductID RIGHT JOIN Tickets c ON b.TicketID = c.TicketID "		
		try {
			ps = conn.prepareStatement(selectTickets);
			
			ps.setInt(1, invoiceID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ticketID = rs.getInt("TicketID");
				ticket = getTicket(invoiceID, ticketID);
				tickets.add(ticket);
				
				
//				depTime = NullString.CheckNullTime(rs.getString("DepTime"));
//				
//				arrTime = NullString.CheckNullTime(rs.getString("ArrTime"));
//				
//				flightNo = NullString.CheckNullString(rs.getString("FlightNumber"));
//				
//				flightClass = NullString.CheckNullString(rs.getString("FlightClass"));
//				
//				aircraftType = NullString.CheckNullString(rs.getString("AircraftType"));
//				
//				seasonStart = NullString.CheckNullDate(rs.getString("SeasonStartDate"));
//				
//				seasonEnd = NullString.CheckNullDate(rs.getString("SeasonEndDate"));
//				
//				travelDate = NullString.CheckNullDate(rs.getString("TravelDate"));
//				
//				ticketNote = NullString.CheckNullString(rs.getString("TicketNote"));
//				
//				depAirportID = rs.getInt("DepAirportID");
//				
//				arrAirportID = rs.getInt("ArrAirportID");
//				
//				rebate = rs.getDouble("Rebate");
//				
//				pointsPerMile = rs.getDouble("PointsPerMile");
//				
//				type = NullString.CheckNullString(rs.getString("ProductType"));
//				
//				code = NullString.CheckNullString(rs.getString("ProductCode"));
//				
//				productID = rs.getInt("ProductID");
//				
//				arrAirport = AirportJDBC.getAirport(arrAirportID);
//				depAirport = AirportJDBC.getAirport(depAirportID);
//				
////				DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
////				DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
////				DateTime seasonStartFormat, seasonEndFormat, travelDateFormat;
////				
////				
////				DateTime depTimeFormat = formatTime.parseDateTime(depTime);
////				DateTime arrTimeFormat = formatTime.parseDateTime(arrTime);
//				
//				switch (flightClass) {
//					case "BC":
//						fc = new BusinessClass();
//						break;
//					case "EP":
//						fc = new EconomyPremium();
//						break;
//					case "EC":
//						fc = new Economy();
//						break;
//				}
//				
//				seats = SeatJDBC.getSeats(invoiceID, productID);
//			
//				switch (type) {
//					case "TO":
//						ticket = new OffseasonTicket(code, seasonStart, seasonEnd, depAirport, arrAirport, depTime, arrTime, flightNo, fc, aircraftType, rebate);
//						break;
//					case "TA":
//						ticket = new AwardTicket(code, depAirport, arrAirport, depTime, arrTime, flightNo, fc, aircraftType, pointsPerMile);
//						break;
//					case "TS":
//						ticket = new StandardTicket(code, depAirport, arrAirport, depTime, arrTime, flightNo, fc, aircraftType);
//						break;
//				}
//	
//				ticket.setTravelDate(travelDate);
//				ticket.setSeats(seats);
//				ticket.setTicketNote(ticketNote);
			
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
		
		return tickets;
		
	}
	
	public static Ticket getTicket(int invoiceID, int ticketID) {
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//ticket
		String flightNo, flightClass, aircraftType, ticketNote, type, code;
		DateTime depTime, arrTime, seasonStart, seasonEnd, travelDate;
		int depAirportID, arrAirportID, productID;
		double rebate, pointsPerMile;
		
		Airport arrAirport = null;
		Airport depAirport = null;
		FlightClass fc = null;
		ArrayList<Seat> seats = new ArrayList<Seat>();
		Ticket ticket = null;
		
		String selectTicket = "SELECT DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, "
				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile, "
				+ "TicketNote, ProductType, ProductCode, TravelDate, b.ProductID FROM Tickets a "
				+ "JOIN Products b ON a.TicketID = b.TicketID "
				+ "JOIN InvoiceProducts c ON b.ProductID = c.ProductID "
				+ "WHERE c.InvoiceID = ? AND b.TicketID = ? AND b.ServiceID IS NULL;";
		
		try {
			ps = conn.prepareStatement(selectTicket);
			
			ps.setInt(1, invoiceID);
			ps.setInt(2, ticketID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				depTime = NullString.CheckNullTime(rs.getString("DepTime"));
				
				arrTime = NullString.CheckNullTime(rs.getString("ArrTime"));
				
				flightNo = NullString.CheckNullString(rs.getString("FlightNumber"));
				
				flightClass = NullString.CheckNullString(rs.getString("FlightClass"));
				
				aircraftType = NullString.CheckNullString(rs.getString("AircraftType"));
				
				seasonStart = NullString.CheckNullDate(rs.getString("SeasonStartDate"));
				
				seasonEnd = NullString.CheckNullDate(rs.getString("SeasonEndDate"));
				
				travelDate = NullString.CheckNullDate(rs.getString("TravelDate"));
				
				ticketNote = NullString.CheckNullString(rs.getString("TicketNote"));
				
				depAirportID = rs.getInt("DepAirportID");
				
				arrAirportID = rs.getInt("ArrAirportID");
				
				rebate = rs.getDouble("Rebate");
				
				pointsPerMile = rs.getDouble("PointsPerMile");
				
				type = NullString.CheckNullString(rs.getString("ProductType"));
				
				code = NullString.CheckNullString(rs.getString("ProductCode"));
				
				productID = rs.getInt("ProductID");
				
				arrAirport = AirportJDBC.getAirport(arrAirportID);
				depAirport = AirportJDBC.getAirport(depAirportID);
				
				switch (flightClass) {
					case "BC":
						fc = new BusinessClass();
						break;
					case "EP":
						fc = new EconomyPremium();
						break;
					case "EC":
						fc = new Economy();
						break;
				}
				
				seats = SeatJDBC.getSeats(invoiceID, productID);
			
				switch (type) {
					case "TO":
						ticket = new OffseasonTicket(code, seasonStart, seasonEnd, depAirport, arrAirport,
								depTime, arrTime, flightNo, fc, aircraftType, rebate, seats, travelDate);
						break;
					case "TA":
						ticket = new AwardTicket(code, depAirport, arrAirport, depTime, arrTime, 
								flightNo, fc, aircraftType, pointsPerMile, seats);
						break;
					case "TS":
						ticket = new StandardTicket(code, depAirport, arrAirport, depTime, arrTime,
								flightNo, fc, aircraftType, seats);
						break;
				}
	
				ticket.setTravelDate(travelDate);
				ticket.setTicketNote(ticketNote);
				
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
		
		return ticket;
	}
		
		
	public static Ticket getInsuranceTicket(int invoiceID, int ticketID) {
		
		
		Ticket ticket = null;
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//ticket
		String flightNo, flightClass, aircraftType, ticketNote, type, code;
		DateTime depTime, arrTime, seasonStart, seasonEnd, travelDate;
		int depAirportID, arrAirportID, productID;
		double rebate, pointsPerMile;
		
		Airport arrAirport = null;
		Airport depAirport = null;
		FlightClass fc = null;
		ArrayList<Seat> seats = new ArrayList<Seat>();
		
//		DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
//		DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
		
		String selectTicket = "SELECT DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, "
				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile, "
				+ "TravelDate, TicketNote, ProductType, ProductCode, c.ProductID "
				+ "FROM InvoiceProducts a JOIN Tickets b ON a.InsuranceTicketID = b.TicketID JOIN Products c ON b.TicketID = c.TicketID "
				+ "WHERE a.InsuranceTicketID = ?;";
		
//				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile "
//				+ "TravelDate, TicketNote, ProductType, ProductCode, ProductID "
//				+ "FROM InvoiceProducts a LEFT JOIN Products b ON a.ProductID = b.ProductID LEFT JOIN Tickets c ON b.TicketID = c.TicketID "
		try {
			ps = conn.prepareStatement(selectTicket);
			ps.setInt(1, ticketID);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				depTime = NullString.CheckNullTime(rs.getString("DepTime"));
				
				arrTime = NullString.CheckNullTime(rs.getString("ArrTime"));
				
				flightNo = NullString.CheckNullString(rs.getString("FlightNumber"));
				
				flightClass = NullString.CheckNullString(rs.getString("FlightClass"));
				
				aircraftType = NullString.CheckNullString(rs.getString("AircraftType"));
				
				seasonStart = NullString.CheckNullDate(rs.getString("SeasonStartDate"));
				
				seasonEnd = NullString.CheckNullDate(rs.getString("SeasonEndDate"));
				
				travelDate = NullString.CheckNullDate(rs.getString("TravelDate"));
				
				ticketNote = NullString.CheckNullString(rs.getString("TicketNote"));
				
				depAirportID = rs.getInt("DepAirportID");
				
				arrAirportID = rs.getInt("ArrAirportID");
				
				rebate = rs.getDouble("Rebate");
				
				pointsPerMile = rs.getDouble("PointsPerMile");
				
				type = NullString.CheckNullString(rs.getString("ProductType"));
				
				code = NullString.CheckNullString(rs.getString("ProductCode"));
				
				productID = rs.getInt("ProductID");
				
				arrAirport = AirportJDBC.getAirport(arrAirportID);
				depAirport = AirportJDBC.getAirport(depAirportID);
				
//				DateTime seasonStartFormat = formatDate.parseDateTime(seasonStart);
//				DateTime seasonEndFormat = formatDate.parseDateTime(seasonEnd);
//				DateTime travelDateFormat = formatDate.parseDateTime(travelDate);
//				DateTime depTimeFormat = formatTime.parseDateTime(depTime);
//				DateTime arrTimeFormat = formatTime.parseDateTime(arrTime);
				
				if (flightClass.equals("BC")) {
					fc = new BusinessClass();
				} else if (flightClass.equals("EP")) {
					fc = new EconomyPremium();
				} else {
					fc = new Economy();
				}
				
				seats = SeatJDBC.getSeats(invoiceID, productID);
			
				if (type.equals("TS")) {
					ticket = new StandardTicket(code, depAirport, arrAirport, depTime, arrTime, 
							flightNo, fc, aircraftType, seats);
					
				} else if (type.equals("TO")) {
					ticket = new OffseasonTicket(code, seasonStart, seasonEnd, depAirport, arrAirport,
							depTime, arrTime, flightNo, fc, aircraftType, rebate, seats, travelDate);
					
				} else {
					ticket = new AwardTicket(code, depAirport, arrAirport, depTime, arrTime, 
							flightNo, fc, aircraftType, pointsPerMile, seats);

				}	
				
				ticket.setTravelDate(travelDate);
				ticket.setTicketNote(ticketNote);
				
			} else {
//				ticket = new StandardTicket("---", AirportJDBC.getAirport(0), AirportJDBC.getAirport(0), NullString.CheckNullTime(null), NullString.CheckNullTime(null), "---", new Economy(), "---");
				throw new SQLException("No associated ticket.");
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
		
		return ticket;
		
	}
	
}
