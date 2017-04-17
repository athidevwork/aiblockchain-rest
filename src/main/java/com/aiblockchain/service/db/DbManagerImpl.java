package com.aiblockchain.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;

import com.aiblockchain.rest.model.Users;

public class DbManagerImpl implements DbManager{
	Connection conn = null;
	
	public void init () {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/aiblockchain","root","p@ssword"); 
			System.out.println("connection = " + conn);
		}
		catch(Exception e) { 
			System.out.println(e);
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
	
	public void getUsers() {
		try{    
			Statement stmt=conn.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users");  
			System.out.println("\nUsers List");
			while(rs.next())  
			System.out.println(rs.getInt(1)+",  "+rs.getString(2)+",  "+rs.getString(3)+",  "+rs.getString(4));  
			//conn.close();  
		}
		catch(Exception e) { 
			System.out.println(e);
		}  
	}
	
	public List<Users> getUsersList() {
		List<Users> users = new ArrayList<Users>();

		try { 
			Statement stmt=conn.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from users");  
			while(rs.next())  
				users.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))); 
		}
		catch(Exception e) { 
			System.out.println(e);
		}  
		System.out.println("UsersList");
		System.out.println(Arrays.asList(users));
		return users;
	}
	
	public int addUser() {
      Random rand = new Random(); 
      int value = rand.nextInt(500); 
		try{    
			// create a sql date object so we can use it in our INSERT statement
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		      // the mysql insert statement
		      String query = " insert into users (id, name, age, created_date) values (?, ?, ?, ?)";
		      
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setInt (1, value);
		      preparedStmt.setString (2, "Athi"+value);
		      preparedStmt.setInt (3, 45);
		      preparedStmt.setDate   (4, startDate);

		      preparedStmt.execute();
		}
		catch(Exception e) { 
			System.out.println(e);
		}
		return value;
	}
	
	public void updateUser(int id) {
		try {
		      Random rand = new Random(); 
		      int value = rand.nextInt(50); 
		      
		      // create the java mysql update preparedstatement
		      String query = "update users set age = ? where id = ?";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setInt(1, value);
		      preparedStmt.setInt(2, id);

		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();			
		}
		catch(Exception e) { 
			System.out.println(e);
		} 		
	}
}
