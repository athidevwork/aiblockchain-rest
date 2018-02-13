package com.aiblockchain.rest.data.entity.dat;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTACT_EMAIL database table.
 * 
 */
//@Entity
//@Table(name="CONTACT_EMAIL")
/*//@NamedQueries({
	//@NamedQuery(name="ContactEmail.findAll", query="SELECT c FROM ContactEmail c")
	//@NamedQuery(name="ContactEmail.findEmailsForName", query="select cu.id, cu.LEGAL_NAME, e.etype, e.email from customer cu, email e,  contact_email ce " +
													"where lower(cu.LEGAL_NAME) like CONCAT(?1,'%') " +
													"and e.ID = ce.EMAIL_ID and cu.ID = ce.CUSTOMER_ID
})*/
public class ContactEmail implements Serializable {
	private static final long serialVersionUID = -4603306068113829339L;

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long contactEmailId;

	//bi-directional many-to-one association to Customer
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="CUSTOMER_ID", columnDefinition="INTEGER")
	private Customer customer;

	//bi-directional many-to-one association to Email
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="EMAIL_ID", columnDefinition="INTEGER")
	private Email email;

	public ContactEmail() {
	}

	public ContactEmail(Customer customer, Email email) {
		super();
		this.customer = customer;
		this.email = email;
	}

	public long getContactEmailId() {
		return this.contactEmailId;
	}

	public void setContactEmailId(long contactEmailId) {
		this.contactEmailId = contactEmailId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Email getEmail() {
		return this.email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	//@Override
	public String toString() {
		return "ContactEmail [id=" + contactEmailId + ", customer=" + customer + ", email=" + email + "]";
	}

}