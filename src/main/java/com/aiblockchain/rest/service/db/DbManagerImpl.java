package com.aiblockchain.rest.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManagerImpl implements DbManager{
	Connection conn = null;
	
	public Connection getConnection() {
		if (conn != null)
			return conn;
		else 
			init();
		return conn;
	}
	
	public void init () {
		try{  
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			//Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aiblockchain","root","p@ssword");
			System.out.println("connection = " + conn);
		}
		catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		catch(Exception e) { 
			System.out.println("Exception on init of db " + e);
		}
	}

	public void shutdown () {
		try{  
			conn.close();
		}
		catch(Exception e) { 
			System.out.println(e);
		}  
	}
}
