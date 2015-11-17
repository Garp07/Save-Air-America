package com.airamerica.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmailJDBC {
	
	public static ArrayList<String> getEmail(int personID) {
		ArrayList<String> emailList = new ArrayList<String>();
		
		Connection conn = DatabaseInfo.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//email
		String email;
		
		String selectEmail = "SELECT Email "
				+ "FROM Emails WHERE PersonID = ?;";
		
		try {
			ps = conn.prepareStatement(selectEmail);
			
			ps.setInt(1, personID);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
			
				email = rs.getString("Email");
				
				emailList.add(email);
				
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
		return emailList;
	}
	
}
