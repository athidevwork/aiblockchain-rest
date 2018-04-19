/**
 * 
 */
package com.aiblockchain.rest.jpa.entity.cre;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Athi
 *
 */
@Entity
@Table(name="cre_user")
@XmlRootElement(name="User")
public class CreUser {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;
	String email;
	String username;
	String password;
	String authorization;
	@Column(name="first_name")
	String firstName;
	@Column(name="last_name")
	String lastName;
	String status;
	
	public CreUser() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CreUser [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", authorization=" + authorization + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", status=" + status + "]";
	}

}
