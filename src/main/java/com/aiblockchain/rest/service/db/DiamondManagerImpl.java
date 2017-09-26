/**
 * 
 */
package com.aiblockchain.rest.service.db;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import com.aiblockchain.api.SaveRequest;
import com.aiblockchain.context.AppContext;
import com.aiblockchain.rest.model.Diamond;
import com.aiblockchain.rest.model.DiamondHistory;

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
	public boolean doesItemIdExist(Diamond d) {
		try{    
			// the mysql insert statement
			String query = " select * from diamond where itemId = ?";

			PreparedStatement preparedStmt = getDbMgr().getConnection().prepareStatement(query);
			preparedStmt.setString (1, d.getItemId());
			
			System.out.println("SQL: " + preparedStmt);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				  String itemId = rs.getString("itemId");
				  System.out.println("itemId = " + itemId + "\n");
				  if (itemId.equals(d.getItemId()))
					  return true;
				  else
					  return false;
			}
		}
		catch(Exception e) { 
			System.out.println("Error on select of diamond characteristic." + e);
		}
		return false;		
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
		System.out.println("Max ID : " + value);
		
		String rowHash = null;
		//Create a hash for row
		try {
			rowHash = getChecksum(d);
		} catch (NoSuchAlgorithmException | IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Hex Of Database Row Hash = " + rowHash);
		
		try{
			// the mysql insert statement
			String query = " insert into diamond (id,description,cut,color,clarity,carat,shape,certification,quality,weight,measurements,itemId,acctId, email,rowhash) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
			preparedStmt.setString (12, d.getItemId());
			preparedStmt.setString (13, d.getAcctId());
			preparedStmt.setString (14, d.getEmail());
			preparedStmt.setString (15, rowHash);
			
			System.out.println("SQL: " + preparedStmt);
			
			preparedStmt.execute();
			/*getDbMgr().getConnection().setAutoCommit(false);
			if (preparedStmt.execute()) {
				System.out.println("Committing stmt : " + preparedStmt.toString());
				getDbMgr().getConnection().commit();
			}
			else
				System.out.println("insert failed");*/			
		}
		catch(Exception e) { 
			System.out.println("Error on insert of diamond characteristic." + e);
		}
		
		int historyId = 0; 
		try {
			Statement stmt=getDbMgr().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select max(id) from diamond_history");
			while(rs.next())
				historyId = rs.getInt(1) + 1;
		}
		catch(Exception e) { 
			System.out.println("Error getting max id for diamond_history from db " + e);
		}
		System.out.println("Max ID on history table : " + historyId);
		
		try{
			//getDbMgr().getConnection().setAutoCommit(false);
			
			List<DiamondHistory> history = d.getHistory();
			
			// the mysql insert statement to diamond_history
			String query = " insert into diamond_history (id, diamond_itemfk, date, description) values (?, ?, ?, ?)";

			PreparedStatement preparedStmt = getDbMgr().getConnection().prepareStatement(query);
			int idValue = historyId;
			for (DiamondHistory hist : history) {
				preparedStmt.setInt (1, idValue++);
				preparedStmt.setString (2, d.getItemId());
				preparedStmt.setString (3, hist.getDate());
				preparedStmt.setString (4, hist.getDescription());
				
				System.out.println("SQL: " + preparedStmt);
				
				preparedStmt.execute();
			}
			//getDbMgr().getConnection().commit();
			//getDbMgr().getConnection().setAutoCommit(true);
		}
		catch(Exception e) { 
			System.out.println("Error on insert of diamond history." + e);
		}
		
		/*try {
			Statement stmt=getDbMgr().getConnection().createStatement();
			String query = "SELECT MD5(CONCAT(id,description,cut,color,clarity,carat,shape,certification,quality,weight,measurements)) from diamond where id="+value;
			System.out.println("Query Str : " + query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
				rowHash = rs.getString(1);
		}
		catch(Exception e) { 
			System.out.println("Error getting row hash from db " + e);
		} 

		try {
			getDbMgr().getConnection().setAutoCommit(false);
			String query = "update diamond set rowhash = ? where id = ?";
			PreparedStatement preparedStmt = getDbMgr().getConnection().prepareStatement(query);
			preparedStmt.setString (1, rowHash);
			preparedStmt.setInt(2, value);

			if (preparedStmt.executeUpdate() != 0)
				getDbMgr().getConnection().commit();
		}
		catch(Exception e) { 
			System.out.println("Error updating row hash to db " + e);
		} 
		//System.out.println("Row Hash = " + rowHash);
		
		//{"diamondData":{"hashId":["4d2e42fc990bc58bf257beada3362977"]},"command":"add"}
		/*JSONObject saveRequest = new JSONObject();
		saveRequest.put("command", "add");
		JSONObject hashID = new JSONObject();
		
		JSONArray txnIds = new JSONArray();
		txnIds.put(rowHash);
		hashID.put("hashId", txnIds);
		saveRequest.put("diamondData", hashID);
		String saveRequestStr = saveRequest.toString();
		System.out.println ("Json Request : " + saveRequestStr);*/
		
		List<String> hashList = new ArrayList<String>();
		hashList.add(rowHash);
		SaveRequest request = new SaveRequest("add", hashList);
		System.out.println("Request : " + request);
		
		//call to send the row hash to AI blockchain
		//SaveResponse response = AbstractAPIAdapter.getInstance().saveRequest(request);
		//System.out.println("Response : " + response);
		return value;
	}

	private String getChecksum(Serializable object) throws IOException, NoSuchAlgorithmException {
	    ByteArrayOutputStream baos = null;
	    ObjectOutputStream oos = null;
	    try {
	        baos = new ByteArrayOutputStream();
	        oos = new ObjectOutputStream(baos);
	        oos.writeObject(object);
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] thedigest = md.digest(baos.toByteArray());
	        return DatatypeConverter.printHexBinary(thedigest);
	    } finally {
	        oos.close();
	        baos.close();
	    }
	}
	
	@Override
	public void updateDiamond(int id) {
		System.out.println("Update not implemented");
	}

	@Override
	public List<Diamond> getDiamondList() {
		List<Diamond> diamondList = new ArrayList<Diamond>();

		try { 
			Statement stmt=getDbMgr().getConnection().createStatement();
			ResultSet rs=stmt.executeQuery("select * from diamond");
			//System.out.println("Result set = " + rs.getFetchSize());
			while(rs.next()) {
				List<DiamondHistory> history = getHistory(String.valueOf(rs.getInt(12)));
				Diamond diamond = new Diamond(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
						, rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)
						, rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)
						, rs.getString(15));
				diamond.setHistory(history);
				diamondList.add(diamond);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("DiamondList");
		System.out.println(Arrays.asList(diamondList));
		return diamondList;
	}

	@Override
	public Diamond getDiamond(String itemId) {
		Diamond diamond = null;

		try {
			List<DiamondHistory> history = getHistory(itemId);
			
			String query = "select * from diamond where itemId = ?";

			PreparedStatement preparedStmt = getDbMgr().getConnection().prepareStatement(query);
			preparedStmt.setString (1, itemId);

			System.out.println("Acct SQL: " + preparedStmt);

			ResultSet rs=preparedStmt.executeQuery();
			 
			//System.out.println("Result set = " + rs.getFetchSize());
			while(rs.next()) {
				diamond = new Diamond(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
						, rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)
						, rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)
						, rs.getString(15));
				diamond.setHistory(history);
			}
		}
		catch(Exception e) {
			System.out.println("Error on select of diamond for an account." + e);
		}
		System.out.println("Diamond for an item " + itemId);
		System.out.println(diamond);
		return diamond;
	}

	private List<DiamondHistory> getHistory(String itemId) throws SQLException {
		//get history for the item
		String historySql = "select id, date, description from diamond_history where diamond_itemfk = ?";
		
		PreparedStatement preparedHistStmt = getDbMgr().getConnection().prepareStatement(historySql);
		preparedHistStmt.setString (1, itemId);

		System.out.println("History SQL: " + preparedHistStmt);

		ResultSet historyrs=preparedHistStmt.executeQuery();
		List<DiamondHistory> history = new ArrayList<DiamondHistory>();	
		while(historyrs.next()) {
			history.add(new DiamondHistory(historyrs.getInt(1), historyrs.getString(2), historyrs.getString(3)));
		}
		return history;
	}

	@Override
	public List<Diamond> getDiamondsForAcct(String acctId) {
		List<Diamond> diamondList = new ArrayList<Diamond>();

		try {
			String query = "select * from diamond where acctId = ?";

			PreparedStatement preparedStmt = getDbMgr().getConnection().prepareStatement(query);
			preparedStmt.setString (1, acctId);

			System.out.println("Acct SQL: " + preparedStmt);

			ResultSet rs=preparedStmt.executeQuery();
			//System.out.println("Result set = " + rs.getFetchSize());
			while(rs.next()) {
				List<DiamondHistory> history = getHistory(String.valueOf(rs.getInt(12)));
				Diamond diamond = new Diamond(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
						, rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)
						, rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)
						, rs.getString(15));
				diamond.setHistory(history);
				diamondList.add(diamond); 				
			}
		}
		catch(Exception e) {
			System.out.println("Error on select of diamond for an account." + e);
		}
		System.out.println("DiamondList for an account = " + acctId);
		System.out.println(Arrays.asList(diamondList));
		return diamondList;
	}
}
