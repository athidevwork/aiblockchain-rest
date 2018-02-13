package com.aiblockchain.rest.data.entity.dat;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACT_ADDRESS database table.
 * 
 */
//@Entity
//@Table(name="CONTACT_ADDRESS")
//@NamedQuery(name="ContactAddress.findAll", query="SELECT c FROM ContactAddress c")
public class ContactAddress implements Serializable {
	private static final long serialVersionUID = -7022846884216910592L;

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long contactAddrId;

	//bi-directional many-to-one association to Customer
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="CUSTOMER_ID", columnDefinition="INTEGER")
	private Customer customer;

	//bi-directional many-to-one association to Address
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="ADDRESS_ID", columnDefinition="INTEGER")
	private Address address;

	public ContactAddress() {
	}

	public ContactAddress(Customer customer, Address address) {
		super();
		this.customer = customer;
		this.address = address;
	}

	public long getContactAddrIdId() {
		return this.contactAddrId;
	}

	public void setContactAddrIdId(long contactAddrId) {
		this.contactAddrId = contactAddrId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	//@Override
	public String toString() {
		return "ContactAddress [id=" + contactAddrId + ", customer=" + customer + ", address=" + address + "]";
	}

}