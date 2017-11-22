/**
 * 
 */
package com.aiblockchain.rest.smartcontract.cds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Athi
 *
 */
public class Platform {
    //Book book;
    //CreditSwap swap;    
    String admin;
    String sender;
    
    Map<String, CreditSwap> swapBook = new HashMap<String, CreditSwap>();
    List<CreditDefaultSwap> swaps;
    //String[] swaps = new String[100];
    OTC otc = new OTC();
    
    Platform() {
        admin = sender;
    }
    
    // recovery rate
    // Discounted Factor  
    protected boolean register(String participants, double regisCapital) {
        return otc.addMember(participants,regisCapital);
    }
     
    protected boolean createCDS(int recovery, int df, int probability, double maturity) {
        if(otc.isMember(sender) != true){  // if not registered, cannot write contract 
            return false;
        }
        int spread;
        spread = 100 + recovery; // for now use this, it will be a math function in the future, or client will do the calculation. 
        if(otc.checkMargin(sender, spread ) != true){
            return false;
        }
        CreditDefaultSwap theswap = new CreditDefaultSwap (sender, recovery, df, probability, maturity, spread);
        swapBook.put(sender, new CreditSwap(sender, "", spread, 0));
        swaps.add(theswap);
        return true;
    }
    
    protected String[] monitor() {
        return (String[]) swaps.toArray();
    }
    
    
    protected boolean buyCDS(String order) {
        if(otc.isMember(sender) != true){  // if not registered, cannot write contract 
            return false;
        }

        if(otc.checkMargin(sender, swapBook.get(order).getSpread() ) != true){ // if you dont have money you can't buy this
            return false;
        }
        swapBook.get(order).setBuyer(sender);
        return true;
    }
    
    protected boolean pay (String order) {
        otc.spending(sender, swapBook.get(order).getSpread());
        otc.depositMargin(swapBook.get(order).getWriter(), swapBook.get(order).getSpread());
        CreditDefaultSwap con = CreditDefaultSwap(order);
        if(con.annualpay()){
            return true;
        }
        else{
            return false;
        }
    }

	protected String getAdmin() {
    	return admin;
    }
    
    protected void netting() {
        
    }
    
    protected double getBalance() {
       return otc.check(sender);
    }
    
    protected void addMoney(double value) {
        otc.depositMargin(sender, value);
    }
}
