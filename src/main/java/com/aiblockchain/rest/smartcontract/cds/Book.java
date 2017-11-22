/**
 * 
 */
package com.aiblockchain.rest.smartcontract.cds;

/**
 * @author Athi
 *
 */
public class Book {
	String member;
	double margin;
	
	Book() {		
	}
	
	public Book(String member, double margin) {
		super();
		this.member = member;
		this.margin = margin;
	}

	public String getMember() {
		System.out.println("performing Get member");
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	@Override
	public String toString() {
		return "Book [member=" + member + ", margin=" + margin + "]";
	}
}
