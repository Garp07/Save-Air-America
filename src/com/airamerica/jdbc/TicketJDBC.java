package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

public class TicketJDBC {
	
	public static ArrayList<Ticket> getTickets(int invoiceID) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//ticket
		String depTime, arrTime, flightNo, flightClass, aircraftType, seasonStart, seasonEnd, travelDate, ticketNote, type, code;
		int depAirportID, arrAirportID;
		double rebate, pointsPerMile;
		
		Airport arrAirport = null;
		Airport depAirport = null;
		FlightClass fc = null;
		ArrayList<Seat> seats = new ArrayList<Seat>();
		Ticket ticket = null;
		
		String selectTicket = "SELECT DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, "
				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile, "
				+ "TravelDate, TicketNote, ProductType, ProductCode "
				+ "FROM InvoiceProducts a LEFT JOIN Products b ON a.ProductID = b.ProductID JOIN Tickets c ON b.TicketID = c.TicketID "
				+ "WHERE a.InvoiceID = ?;";
		
		try {
			ps = conn.prepareStatement(selectTicket);
			
			ps.setInt(1, invoiceID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				depTime = rs.getString("DepTime");
				arrTime = rs.getString("ArrTime");
				flightNo = rs.getString("FlightNumber");
				flightClass = rs.getString("FlightClass");
				aircraftType = rs.getString("AircraftType");
				seasonStart = rs.getString("SeasonStartDate");
				seasonEnd = rs.getString("SeasonEndDate");
				travelDate = rs.getString("TravelDate");
				ticketNote = rs.getString("TicketNote");
				depAirportID = rs.getInt("DepAirportID");
				arrAirportID = rs.getInt("ArrAirportID");
				rebate = rs.getDouble("Rebate");
				pointsPerMile = rs.getDouble("PointsPerMile");
				type = rs.getString("ProductType");
				code = rs.getString("ProductCode");
				
				arrAirport = AirportJDBC.getAirport(arrAirportID);
				depAirport = AirportJDBC.getAirport(depAirportID);
				
				DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
				DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTime seasonStartFormat, seasonEndFormat, travelDateFormat;
				
				
				DateTime depTimeFormat = formatTime.parseDateTime(depTime);
				DateTime arrTimeFormat = formatTime.parseDateTime(arrTime);
				
				if (flightClass.equals("BC")) {
					fc = new BusinessClass();
				} else if (flightClass.equals("EC")) {
					fc = new Economy();
				} else if (flightClass.equals("EP")) {
					fc = new EconomyPremium();
				}
				
				seats = SeatJDBC.getSeats(invoiceID);
			
				if (type.equals("TS")) {
					ticket = new StandardTicket(code, depAirport, arrAirport, depTimeFormat, arrTimeFormat, flightNo, fc, aircraftType);
				} else if (type.equals("TO")) {
					seasonStartFormat = formatDate.parseDateTime(seasonStart);
					seasonEndFormat = formatDate.parseDateTime(seasonEnd);
					ticket = new OffseasonTicket(code, seasonStartFormat, seasonEndFormat, depAirport, arrAirport, depTimeFormat, arrTimeFormat, flightNo, fc, aircraftType, rebate);
				} else if (type.equals("TA")) {
					ticket = new AwardTicket(code, depAirport, arrAirport, depTimeFormat, arrTimeFormat, flightNo, fc, aircraftType, pointsPerMile);
				}	
				
				if(travelDate != null) {
					travelDateFormat = formatDate.parseDateTime(travelDate);
					ticket.setTravelDate(travelDateFormat);
				}
				
				
				ticket.setSeats(seats);
				ticket.setTicketNote(ticketNote);
				
				tickets.add(ticket);
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
		
		return tickets;
		
	}
	
	public static Ticket getInsuranceTicket(int ticketID) {
		Ticket ticket = null;
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//ticket
		String depTime, arrTime, flightNo, flightClass, aircraftType, seasonStart, seasonEnd, travelDate, ticketNote, type, code;
		int depAirportID, arrAirportID, invoiceID;
		double rebate, pointsPerMile;
		
		Airport arrAirport = null;
		Airport depAirport = null;
		FlightClass fc = null;
		ArrayList<Seat> seats = new ArrayList<Seat>();
		
		String selectTicket = "SELECT DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, "
				+ "FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile, "
				+ "TravelDate, TicketNote, ProductType, ProductCode, InvoiceID "
				+ "FROM InvoiceProducts a JOIN Products b ON a.ProductID = b.ProductID JOIN Tickets c ON b.TicketID = c.TicketID "
				+ "WHERE a.InsuranceTicketID = ?;";
		
		try {
			ps = conn.prepareStatement(selectTicket);
			ps.setInt(1, ticketID);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				depTime = rs.getString("DepTime");
				arrTime = rs.getString("ArrTime");
				flightNo = rs.getString("FlightNumber");
				flightClass = rs.getString("FlightClass");
				aircraftType = rs.getString("AircraftType");
				seasonStart = rs.getString("SeasonStartDate");
				seasonEnd = rs.getString("SeasonEndDate");
				travelDate = rs.getString("TravelDate");
				ticketNote = rs.getString("TicketNote");
				depAirportID = rs.getInt("DepAirportID");
				arrAirportID = rs.getInt("ArrAirportID");
				rebate = rs.getDouble("Rebate");
				pointsPerMile = rs.getDouble("PointsPerMile");
				type = rs.getString("ProductType");
				code = rs.getString("ProductCode");
				invoiceID = rs.getInt("InvoiceID");
				
				arrAirport = AirportJDBC.getAirport(arrAirportID);
				depAirport = AirportJDBC.getAirport(depAirportID);
				
				DateTimeFormatter formatTime = DateTimeFormat.forPattern("HH:mm");
				DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
				
				DateTime seasonStartFormat = formatDate.parseDateTime(seasonStart);
				DateTime seasonEndFormat = formatDate.parseDateTime(seasonEnd);
				DateTime travelDateFormat = formatDate.parseDateTime(travelDate);
				DateTime depTimeFormat = formatTime.parseDateTime(depTime);
				DateTime arrTimeFormat = formatTime.parseDateTime(arrTime);
				
				if (flightClass.equals("BC")) {
					fc = new BusinessClass();
				} else if (flightClass.equals("EC")) {
					fc = new Economy();
				} else {
					fc = new EconomyPremium();
				}
				
				seats = SeatJDBC.getSeats(invoiceID);
			
				if (type.equals("TS")) {
					ticket = new StandardTicket(code, depAirport, arrAirport, depTimeFormat, arrTimeFormat, flightNo, fc, aircraftType);
					ticket.setTravelDate(travelDateFormat);
					ticket.setSeats(seats);
					ticket.setTicketNote(ticketNote);
				} else if (type.equals("TO")) {
					ticket = new OffseasonTicket(code, seasonStartFormat, seasonEndFormat, depAirport, arrAirport, depTimeFormat, arrTimeFormat, flightNo, fc, aircraftType, rebate);
					ticket.setTravelDate(travelDateFormat);
					ticket.setSeats(seats);
					ticket.setTicketNote(ticketNote);
				} else {
					ticket = new AwardTicket(code, depAirport, arrAirport, depTimeFormat, arrTimeFormat, flightNo, fc, aircraftType, pointsPerMile);
					ticket.setTravelDate(travelDateFormat);
					ticket.setSeats(seats);
					ticket.setTicketNote(ticketNote);
				}	
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
