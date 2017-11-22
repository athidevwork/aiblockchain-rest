/**
 * 
 */
package com.aiblockchain.rest.smartcontract.cds;

/**
 * @author Athi
 *
 */
public class CreditSwap {
    String writer;
    String buyer;
    double spread;
    int status; // 0 for pre, 1 for open , 2 for executed;
    
	public CreditSwap(String writer, String buyer, double spread, int status) {
		super();
		this.writer = writer;
		this.buyer = buyer;
		this.spread = spread;
		this.status = status;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public double getSpread() {
		return spread;
	}
	public void setSpread(double spread) {
		this.spread = spread;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CreditSwap [writer=" + writer + ", buyer=" + buyer + ", spread=" + spread + ", status=" + status + "]";
	}
}
