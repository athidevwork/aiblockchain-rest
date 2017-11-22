package com.aiblockchain.rest.smartcontract.cds;

/**
 * @author Athi
 *
 */
public class CreditDefaultSwap {
	 int recovery;
	 int df;
	 int probability;
	 double maturity;
	 double spread;
	 String key;
	 
	public CreditDefaultSwap(String sender, int recovery, int df, int probability, double maturity, double spread) {
		super();
		key = sender;
		this.recovery = recovery;
		this.df = df;
		this.probability = probability;
		this.maturity = maturity;
		this.spread = spread;
	}

	public boolean isPayable(String sender) {
        if(key != sender){
            return false;
        }
        else{ // add check it is the right amount 
            maturity -= 1;
            return true;
        }		
	}
	
	public double getBalance () {
		return maturity;
	}
	
	@Override
	public String toString() {
		return "CreditDefaultSwap [recovery=" + recovery + ", df=" + df + ", probability=" + probability + ", maturity="
				+ maturity + ", spread=" + spread + "]";
	}

	public boolean annualpay() {
		// TODO Auto-generated method stub
		return false;
	}
}
