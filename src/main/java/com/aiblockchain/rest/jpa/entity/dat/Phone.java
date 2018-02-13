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
//@Table(name="phone")
public class Phone {
	
	//@Id
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	//@ManyToOne
	//@JsonIgnore
    //@JoinColumn(name="customer_id", nullable=false, updatable=false, insertable=true)
	private Customer customer;
	
	//@Column(name="phone_type")
	private String phoneType;
	
	//@Column(name="phone_number")
	private String phoneNumber;
	
	public Phone() {		
	}
	
	public Phone(String phoneType, String phoneNumber) {
		super();
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
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
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
