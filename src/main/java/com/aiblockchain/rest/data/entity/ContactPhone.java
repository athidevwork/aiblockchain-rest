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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long contactPhoneId;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_ID", columnDefinition="INTEGER")
	private Customer customer;

	//bi-directional many-to-one association to Phone
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PHONE_ID", columnDefinition="INTEGER")
	private Phone phone;

	public ContactPhone() {
	}

	public ContactPhone(Customer customer, Phone phone) {
		super();
		this.customer = customer;
		this.phone = phone;
	}

	public long getContactPhoneId() {
		return this.contactPhoneId;
	}

	public void setContactPhoneId(long contactPhoneId) {
		this.contactPhoneId = contactPhoneId;
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
		return "ContactPhone [id=" + contactPhoneId + ", customer=" + customer + ", phone=" + phone + "]";
	}

}