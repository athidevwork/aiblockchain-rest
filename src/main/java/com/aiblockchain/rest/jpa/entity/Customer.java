package com.aiblockchain.rest.jpa.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Athi
 *
 */
@Entity
@Table(name="customer")
@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnoreProperties("customer")
	@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
	Set<Address> addresses;
	
	@JsonIgnoreProperties("customer")
	@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
	Set<Phone> phones;
	
	@JsonIgnoreProperties("customer")
	@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
	Set<Email> emails;
	
	@JsonIgnoreProperties("customer")
	@OneToMany(mappedBy="customer", cascade=CascadeType.PERSIST)
	Set<Account> accounts;
	
	@Column(name="legal_name")
	private String legalName;
	
	@Column(name="tax_id")
	private String taxId;
	
	@Column(name="govt_id")
	private String govtId;
	
	@Column(name="emergency_contact")
	private String emergencyContact;
	
	private String beneficiary;
	
	public Customer() {		
	}
	
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
		for (Account theAccount: accounts) {
			theAccount.setCustomer(this);
		}
	}
	
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
		for (Address theAddress: addresses) {
			theAddress.setCustomer(this);
		}
	}
	
	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
		for (Phone thePhone: phones) {
			thePhone.setCustomer(this);
		}		
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
		for (Email theEmail: emails) {
			theEmail.setCustomer(this);
		}		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getGovtId() {
		return govtId;
	}

	public void setGovtId(String govtId) {
		this.govtId = govtId;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", addresses=" + addresses + ", phones=" + phones + ", emails=" + emails
				+ ", accounts=" + accounts + ", legalName=" + legalName + ", taxId=" + taxId + ", govtId=" + govtId
				+ ", emergencyContact=" + emergencyContact + ", beneficiary=" + beneficiary + ", getAccounts()="
				+ getAccounts() + ", getAddresses()=" + getAddresses() + ", getPhones()=" + getPhones()
				+ ", getEmails()=" + getEmails() + ", getId()=" + getId() + ", getLegalName()=" + getLegalName()
				+ ", getTaxId()=" + getTaxId() + ", getGovtId()=" + getGovtId() + ", getEmergencyContact()="
				+ getEmergencyContact() + ", getBeneficiary()=" + getBeneficiary() + "]";
	}
}
