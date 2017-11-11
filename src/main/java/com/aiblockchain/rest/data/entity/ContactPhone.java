package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACT_PHONE database table.
 * 
 */
@Entity
@Table(name="CONTACT_PHONE")
@NamedQuery(name="ContactPhone.findAll", query="SELECT c FROM ContactPhone c")
public class ContactPhone implements Serializable {
	private static final long serialVersionUID = 6810267259946137666L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "INTEGER")
	private long id;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	private Customer customer;

	//bi-directional many-to-one association to Phone
	@ManyToOne(fetch=FetchType.LAZY)
	private Phone phone;

	public ContactPhone() {
	}

	public ContactPhone(Customer customer, Phone phone) {
		super();
		this.customer = customer;
		this.phone = phone;
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

	public Phone getPhone() {
		return this.phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "ContactPhone [id=" + id + ", customer=" + customer + ", phone=" + phone + "]";
	}

}