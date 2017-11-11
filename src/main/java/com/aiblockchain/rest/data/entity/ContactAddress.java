package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACT_ADDRESS database table.
 * 
 */
@Entity
@Table(name="CONTACT_ADDRESS")
@NamedQuery(name="ContactAddress.findAll", query="SELECT c FROM ContactAddress c")
public class ContactAddress implements Serializable {
	private static final long serialVersionUID = -7022846884216910592L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "INTEGER")
	private long id;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	private Address address;

	public ContactAddress() {
	}

	public ContactAddress(Customer customer, Address address) {
		super();
		this.customer = customer;
		this.address = address;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ContactAddress [id=" + id + ", customer=" + customer + ", address=" + address + "]";
	}

}