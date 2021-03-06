/**
 * 
 */
package com.aiblockchain.rest.service.db;

import java.util.List;

import com.aiblockchain.rest.model.Diamond;

/**
 * @author Athi
 *
 */
public interface DiamondManager {
	public int addDiamond(Diamond d);
	public void updateDiamond(int id);
	public List<Diamond> getDiamondList();
	public Diamond getDiamond(String itemId);
	public boolean doesItemIdExist(Diamond d);
	public List<Diamond> getDiamondsForAcct(String acctId);	
}
