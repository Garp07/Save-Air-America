package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.airamerica.customer.Customer;
import com.airamerica.invoice.Invoice;
import com.airamerica.person.Person;
import com.airamerica.product.service.Service;
import com.airamerica.product.ticket.Ticket;
import com.airamerica.utils.NullString;

public class InvoiceJDBC {
	
	public static ArrayList<Invoice> getInvoices() {
		
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//invoice
		String code, date;
		//String PNR;
		int customerID, personID, invoiceID;
		Customer customer = null;
		Person person = null;
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		ArrayList<Service> services = new ArrayList<Service>();
		
		String selectInvoice = "SELECT InvoiceCode, CustomerID, SalespersonID, InvoiceDate, PNR, InvoiceID "
				+ "FROM Invoices;";
		
		try {
			ps = conn.prepareStatement(selectInvoice);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				code = NullString.CheckNullString(rs.getString("InvoiceCode"));
				date = NullString.CheckNullString(rs.getString("InvoiceDate"));
				//PNR = rs.getString("PNR");
				customerID = rs.getInt("CustomerID");
				personID = rs.getInt("SalespersonID");
				invoiceID = rs.getInt("InvoiceID");

				customer = CustomerJDBC.getCustomer(customerID);
				person = PersonJDBC.getPerson(personID);
				tickets = TicketJDBC.getTickets(invoiceID);
				services = ServiceJDBC.getServices(invoiceID);
				
				DateTimeFormatter formatDate = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTime invoiceDate = formatDate.parseDateTime(date);
				
				invoices.add(new Invoice(code, customer, person, invoiceDate, tickets, services));
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
		
		return invoices;
		
	}
	
}
