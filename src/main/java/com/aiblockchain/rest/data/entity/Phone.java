package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PHONE database table.
 * 
 */
@Entity
@NamedQuery(name="Phone.findAll", query="SELECT p FROM Phone p")
public class Phone implements Serializable {
	private static final long serialVersionUID = 1908787136427554623L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "INTEGER")
	private long id;

	private String pnumber;

	private String ptype;

	//bi-directional many-to-one association to ContactPhone
	@OneToMany(mappedBy="phone")
	private List<ContactPhone> contactPhones;

	public Phone() {
	}

	public Phone(String pnumber, String ptype) {
		super();
		this.pnumber = pnumber;
		this.ptype = ptype;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPnumber() {
		return this.pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public List<ContactPhone> getContactPhones() {
		return this.contactPhones;
	}

	public void setContactPhones(List<ContactPhone> contactPhones) {
		this.contactPhones = contactPhones;
	}

	public ContactPhone addContactPhone(ContactPhone contactPhone) {
		getContactPhones().add(contactPhone);
		contactPhone.setPhone(this);

		return contactPhone;
	}

	public ContactPhone removeContactPhone(ContactPhone contactPhone) {
		getContactPhones().remove(contactPhone);
		contactPhone.setPhone(null);

		return contactPhone;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", pnumber=" + pnumber + ", ptype=" + ptype + ", contactPhones=" + contactPhones
				+ "]";
	}

}