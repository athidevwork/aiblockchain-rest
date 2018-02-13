package com.aiblockchain.rest.jpa.entity.dat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * //@author Athi
 *
 */
//@Entity
//@Table(name="email")
public class Email {
	
	//@Id
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	//@ManyToOne
	//@JsonIgnore
    //@JoinColumn(name="customer_id", nullable=false, updatable=false, insertable=true)
	private Customer customer;
	
	//@Column(name="email_type")
	private String emailType;
	
	//@Column(name="email_address")
	private String emailAddress;
	
	public Email() {	
	}
	
	public Email(String emailType, String emailAddress) {
		super();
		this.emailType = emailType;
		this.emailAddress = emailAddress;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
