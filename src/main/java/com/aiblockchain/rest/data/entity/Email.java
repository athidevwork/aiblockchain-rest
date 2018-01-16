package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EMAIL database table.
 * 
 */
//@Entity
//@NamedQuery(name="Email.findAll", query="SELECT e FROM Email e")
public class Email implements Serializable {
	private static final long serialVersionUID = -7662503089973436L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long emailId;

	private String email;

	private String etype;

	//bi-directional many-to-one association to ContactEmail
	@OneToMany(mappedBy="email", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ContactEmail> contactEmails;

	public Email() {
	}

	public Email(String email, String etype) {
		super();
		this.email = email;
		this.etype = etype;
	}

	public long getEmailIdId() {
		return this.emailId;
	}

	public void setEmailIdId(long emailId) {
		this.emailId = emailId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEtype() {
		return this.etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public List<ContactEmail> getContactEmails() {
		return this.contactEmails;
	}

	public void setContactEmails(List<ContactEmail> contactEmails) {
		this.contactEmails = contactEmails;
	}

	public ContactEmail addContactEmail(ContactEmail contactEmail) {
		getContactEmails().add(contactEmail);
		contactEmail.setEmail(this);

		return contactEmail;
	}

	public ContactEmail removeContactEmail(ContactEmail contactEmail) {
		getContactEmails().remove(contactEmail);
		contactEmail.setEmail(null);

		return contactEmail;
	}

	@Override
	public String toString() {
		return "Email [id=" + emailId + ", email=" + email + ", etype=" + etype + ", contactEmails=" + contactEmails + "]";
	}

}