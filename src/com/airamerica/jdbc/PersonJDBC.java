package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.airamerica.address.Address;
import com.airamerica.person.Person;
import com.airamerica.utils.NullString;

public class PersonJDBC {
	
//	public static ArrayList<Person> getPersons() {
//		ArrayList<Person> persons = new ArrayList<Person>();
//		
//		Connection conn = DatabaseInfo.getConnection();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		//Person
//		String code, firstName, lastName, phoneNumber;
//		int addressID, personID;
//		Address address = null;
//		ArrayList<String> emails = new ArrayList<String>();
//		
//		String selectPerson = "SELECT PersonID, PersonCode, FirstName, LastName, AddressID, PhoneNumber FROM Persons;";
//		
//		try {
//			ps = conn.prepareStatement(selectPerson);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				code = rs.getString("PersonCode");
//				firstName = rs.getString("FirstName");
//				lastName = rs.getString("LastName");
//				phoneNumber = rs.getString("PhoneNumber");
//				personID = rs.getInt("PersonID");
//				addressID = rs.getInt("AddressID");
//				
//				address = AddressJDBC.getAddress(addressID);
//				
//				emails = EmailJDBC.getEmail(personID);
//				
//				if (phoneNumber == null) {
//					Person p = new Person(code, firstName, lastName, address);
//					p.setEmails(emails);
//					persons.add(p);
//				} else {
//					Person p = new Person(code, firstName, lastName, address, phoneNumber);
//					p.setEmails(emails);
//					persons.add(p);
//				}
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
//		return persons;
//		
//	}
	
	public static Person getPerson(int ID) {
		Person person = null;
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//Person
		String code, firstName, lastName, phoneNumber;
		int addressID, personID;
		Address address = null;
		ArrayList<String> emails = new ArrayList<String>();
		
		String selectPerson = "SELECT PersonID, PersonCode, FirstName, LastName, AddressID, PhoneNumber "
				+ "FROM Persons WHERE PersonID = ?;";
		
		if(ID == 0) {
			return new Person("ONLINE", "ONLINE", "ONLINE", new Address("", "", "", "", ""));
		}
		
		try {
			ps = conn.prepareStatement(selectPerson);
			ps.setInt(1, ID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				code = NullString.CheckNullString(rs.getString("PersonCode"));

				firstName = NullString.CheckNullString(rs.getString("FirstName"));

				lastName = NullString.CheckNullString(rs.getString("LastName"));

				phoneNumber = NullString.CheckNullString(rs.getString("PhoneNumber"));
				
				personID = rs.getInt("PersonID");
				
				addressID = rs.getInt("AddressID");
				
				if (addressID != 0) {
					address = AddressJDBC.getAddress(addressID);
				} else {
					address = new Address("ONLINE", "ONLINE", "ONLINE", "ONLINE", "ONLINE");
				}
				
				emails = EmailJDBC.getEmail(personID);
				
				person = new Person(code, firstName, lastName, address, phoneNumber);
				person.setEmails(emails);
				
//				if (phoneNumber != null) {
//					person = new Person(code, firstName, lastName, address);
//					person.setEmails(emails);
//				} else {
//					person = new Person(code, firstName, lastName, address, phoneNumber);
//					person.setEmails(emails);
//				}
				
			} else {
				person = new Person("---", "---", "---", AddressJDBC.getAddress(0), "---");
//				throw new SQLException("No associated person.");
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
		
		return person;
		
	}
	
}