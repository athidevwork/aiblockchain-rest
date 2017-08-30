/**
 * 
 */
package com.aiblockchain.rest.service.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.Diamond;

/**
 * @author Athi
 *
 */
public class DiamondManagerImpl extends DbManagerImpl implements DiamondManager {
	DbManager dbMgr = (DbManager) AppContext.getBean(AppContext.DB_MANAGER);
	
    public DbManager getDbMgr() {
		return dbMgr;
	}
    
	@Override
	public void getDiamond() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int addDiamond(Diamond d) {
		Random rand = new Random(); 
		int value = 0;
		//int value = rand.nextInt(500); 
		try {
			Statement stmt=getDbMgr().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select max(id) from diamond");
			while(rs.next())
				value = rs.getInt(1) + 1;
		}
		catch(Exception e) { 
			System.out.println("Error getting max id from db " + e);
		}  
		
		try{    
			// the mysql insert statement
			String query = " insert into diamond (id,description,cut,color,clarity,carat,shape,certification,quality,weight,measurements) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = getDbMgr().getConnection().prepareStatement(query);
			preparedStmt.setInt (1, value);
			preparedStmt.setString (2, d.getDescription());
			preparedStmt.setString (3, d.getCut());
			preparedStmt.setString (4, d.getColor());
			preparedStmt.setString (5, d.getClarity());
			preparedStmt.setString (6, d.getCarat());
			preparedStmt.setString (7, d.getShape());
			preparedStmt.setString (8, d.getCertification());
			preparedStmt.setString (9, d.getQuality());			
			preparedStmt.setString (10, d.getWeight());
			preparedStmt.setString (11, d.getMeasurements());
			
			if (preparedStmt.execute())
				getDbMgr().getConnection().commit();
		}
		catch(Exception e) { 
			System.out.println("Error on insert " + e);
		}
		return value;
	}

	@Override
	public void updateDiamond(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Diamond> getDiamondList() {
		List<Diamond> diamond = new ArrayList<Diamond>();

		try { 
			Statement stmt=getDbMgr().getConnection().createStatement();  
			ResultSet rs=stmt.executeQuery("select * from diamond"); 
			//System.out.println("Result set = " + rs.getFetchSize());
			while(rs.next())  
				diamond.add(new Diamond(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
						, rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11))); 
		}
		catch(Exception e) { 
			System.out.println(e);
		}  
		System.out.println("DiamondList");
		System.out.println(Arrays.asList(diamond));
		return diamond;
	}
}
