package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.airamerica.person.Person;
import com.airamerica.product.service.CheckedBaggage;
import com.airamerica.product.service.Insurance;
import com.airamerica.product.service.Refreshment;
import com.airamerica.product.service.Service;
import com.airamerica.product.service.SpecAssist;
import com.airamerica.product.ticket.Ticket;

public class ServiceJDBC {
	
	public static ArrayList<Service> getServices(int invoiceID) {
		ArrayList<Service> services = new ArrayList<Service>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//service
		String code, insuranceName, ageClass, refreshmentName, typeOfService, type;
		int quantity, ticketID, personID;
		double costPerMile, cost;
		Person person = null;
		Ticket ticket = null;
		Service service = null;
		
		String selectService = "SELECT InsuranceName, AgeClass, CostPerMile, TypeOfService, RefreshmentName, "
				+ "Cost, ProductCode, Quantity, InsuranceTicketID, SpecialAssistancePersonID, ProductType "
				+ "FROM InvoiceProducts a LEFT JOIN Products b ON a.ProductID = b.ProductID "
				+ "LEFT JOIN Services c ON b.ServiceID = c.ServiceID "
				+ "WHERE a.InvoiceID = ?;";
		
		try {
			ps = conn.prepareStatement(selectService);
			ps.setInt(1, invoiceID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				code = rs.getString("ProductCode");
				insuranceName = rs.getString("InsuranceName");
				ageClass = rs.getString("AgeClass");
				refreshmentName = rs.getString("RefreshmentName");
				typeOfService = rs.getString("TypeOfService");
				quantity = rs.getInt("Quantity");
				ticketID = rs.getInt("InsuranceTicketID");
				personID = rs.getInt("SpecialAssistancePersonID");
				costPerMile = rs.getDouble("CostPerMile");
				cost = rs.getDouble("Cost");
				type = rs.getString("ProductType");
				
				ticket = TicketJDBC.getInsuranceTicket(ticketID);
				person = PersonJDBC.getPerson(personID);
				
				if (type.equals("SC")) {
					service = new CheckedBaggage(code, ticket); //Need to go back and add ticket attribute to CB class as well as a column in the DB
					service.setQuantity(quantity);
					services.add(service);
				} else if (type.equals("SI")) {
					service = new Insurance(code, insuranceName, ageClass, costPerMile, ticket);
					service.setQuantity(quantity);
					services.add(service);
				} else if (type.equals("SS")) {
					service = new SpecAssist(code, typeOfService, person);
					services.add(service);
				} else {
					service = new Refreshment(code, refreshmentName, cost);
					service.setQuantity(quantity);
					services.add(service);
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
		
		return services;
		
	}
}
