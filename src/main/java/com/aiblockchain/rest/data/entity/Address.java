package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ADDRESS database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 7078125133300055931L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long addressId;

	private String address1;

	private String address2;

	private String atype;

	private String city;

	@Column(name="\"STATE\"")
	private String state;

	private String zip;

	//bi-directional many-to-one association to ContactAddress
	@OneToMany(mappedBy="address", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContactAddress> contactAddresses;

	public Address() {
	}

	public Address(String address1, String address2, String atype, String city, String state, String zip) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.atype = atype;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAtype() {
		return this.atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<ContactAddress> getContactAddresses() {
		return this.contactAddresses;
	}

	public void setContactAddresses(List<ContactAddress> contactAddresses) {
		this.contactAddresses = contactAddresses;
	}

	public ContactAddress addContactAddress(ContactAddress contactAddress) {
		getContactAddresses().add(contactAddress);
		contactAddress.setAddress(this);

		return contactAddress;
	}

	public ContactAddress removeContactAddress(ContactAddress contactAddress) {
		getContactAddresses().remove(contactAddress);
		contactAddress.setAddress(null);

		return contactAddress;
	}

	@Override
	public String toString() {
		return "Address [id=" + addressId + ", address1=" + address1 + ", address2=" + address2 + ", atype=" + atype
				+ ", city=" + city + ", state=" + state + ", zip=" + zip + ", contactAddresses=" + contactAddresses
				+ "]";
	}

}