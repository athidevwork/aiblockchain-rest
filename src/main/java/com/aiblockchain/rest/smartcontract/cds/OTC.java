/**
 * 
 */
package com.aiblockchain.rest.smartcontract.cds;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Athi
 *
 */
public class OTC {
	Map<String, Book> book = new HashMap<String, Book>();
	
	protected boolean isMember(String addr) {
        if(book.get(addr) != null){
            return true;
        }
        return false;		
	}
	
	protected boolean addMember(String addr, double regisFund) {
        if (isMember(addr)) {
        	System.out.println("Member : " + book.get(addr));
            return false; // already a member 
        }
        book.put(addr, new Book(addr, regisFund));
        return true;		
	}
	
	protected boolean removeMember(String addr) {
		if (isMember(addr)) {
			book.remove(addr);
			return true;
		}
		else {
			System.out.println("Member not found in book to remove " + addr);
			return false;
		}
	}
	
	protected boolean checkMargin (String addr, double spread) {
        if (book.get(addr).getMargin() < (3 * spread)) {
            return false;
        }
        else{
            return true;
        }
    }
    
	protected void spendMargin (String addr, double purchase) {
		double orig = book.get(addr).getMargin();
		if (purchase <= orig)
			book.get(addr).setMargin(orig - purchase);
		else
			System.out.println("Purchase " + purchase + " cannot be spent. Balance : " + orig);
    }
    
	protected void depositMargin (String addr, double deposit){
		double orig = book.get(addr).getMargin();
		if (deposit > 0)
			book.get(addr).setMargin(orig + deposit);
		else
			System.out.println("Cannot add deposit " + deposit + " to balance. Balance : " + orig);
    }
    
	protected double check(String addr) {
        return book.get(addr).getMargin();
    }

	public void spending(String sender, double spread) {
		// TODO Auto-generated method stub
		
	}
    
}
