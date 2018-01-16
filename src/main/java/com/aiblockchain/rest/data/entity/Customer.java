package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
//@Entity
/*@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
	@NamedQuery(name="Customer.findCustomerWithLegalName", query="select cu.id, cu.LEGAL_NAME, a.* " + "" +
		"from aiblockchain.customer cu, aiblockchain.address a,  aiblockchain.contact_address ca"+
		"where cu.LEGAL_NAME like '%?%'"+
		"and a.ID = ca.ADDRESS_ID and cu.ID = ca.CUSTOMER_ID")
})*/

public class Customer implements Serializable {
	private static final long serialVersionUID = 6114627040695685844L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long custId;

	private String beneficiary;

	@Column(name="EMERGENCY_CONTACT")
	private String emergencyContact;

	@Column(name="GOVT_ID")
	private String govtId;

	@Column(name="LEGAL_NAME")
	private String legalName;

	@Column(name="TAX_ID")
	private String taxId;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="acctId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Account> accounts;

	//bi-directional many-to-one association to ContactAddress
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContactAddress> contactAddresses;

	//bi-directional many-to-one association to ContactEmail
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContactEmail> contactEmails;

	//bi-directional many-to-one association to ContactPhone
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContactPhone> contactPhones;

	public Customer() {
	}

	public Customer(String beneficiary, String emergencyContact, String govtId, String legalName, String taxId) {
		super();
		this.beneficiary = beneficiary;
		this.emergencyContact = emergencyContact;
		this.govtId = govtId;
		this.legalName = legalName;
		this.taxId = taxId;
	}

	public long getCustId() {
		return this.custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getBeneficiary() {
		return this.beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getEmergencyContact() {
		return this.emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getGovtId() {
		return this.govtId;
	}

	public void setGovtId(String govtId) {
		this.govtId = govtId;
	}

	public String getLegalName() {
		return this.legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setCustomer(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setCustomer(null);

		return account;
	}

	public List<ContactAddress> getContactAddresses() {
		return this.contactAddresses;
	}

	public void setContactAddresses(List<ContactAddress> contactAddresses) {
		this.contactAddresses = contactAddresses;
	}

	public ContactAddress addContactAddress(ContactAddress contactAddress) {
		getContactAddresses().add(contactAddress);
		contactAddress.setCustomer(this);

		return contactAddress;
	}

	public ContactAddress removeContactAddress(ContactAddress contactAddress) {
		getContactAddresses().remove(contactAddress);
		contactAddress.setCustomer(null);

		return contactAddress;
	}

	public List<ContactEmail> getContactEmails() {
		return this.contactEmails;
	}

	public void setContactEmails(List<ContactEmail> contactEmails) {
		this.contactEmails = contactEmails;
	}

	public ContactEmail addContactEmail(ContactEmail contactEmail) {
		getContactEmails().add(contactEmail);
		contactEmail.setCustomer(this);

		return contactEmail;
	}

	public ContactEmail removeContactEmail(ContactEmail contactEmail) {
		getContactEmails().remove(contactEmail);
		contactEmail.setCustomer(null);

		return contactEmail;
	}

	public List<ContactPhone> getContactPhones() {
		return this.contactPhones;
	}

	public void setContactPhones(List<ContactPhone> contactPhones) {
		this.contactPhones = contactPhones;
	}

	public ContactPhone addContactPhone(ContactPhone contactPhone) {
		getContactPhones().add(contactPhone);
		contactPhone.setCustomer(this);

		return contactPhone;
	}

	public ContactPhone removeContactPhone(ContactPhone contactPhone) {
		getContactPhones().remove(contactPhone);
		contactPhone.setCustomer(null);

		return contactPhone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + custId + ", beneficiary=" + beneficiary + ", emergencyContact=" + emergencyContact
				+ ", govtId=" + govtId + ", legalName=" + legalName + ", taxId=" + taxId + ", accounts=" + accounts
				+ ", contactAddresses=" + contactAddresses + ", contactEmails=" + contactEmails + ", contactPhones="
				+ contactPhones + "]";
	}

}