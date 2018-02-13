package com.aiblockchain.rest.jpa.entity.dat;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * //@author Athi
 *
 */
//@Entity
//@Table(name="account")
//@XmlRootElement(name="Account")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Account {
	
	//@Id
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	//@ManyToOne
	//@JsonIgnore
    //@JoinColumn(name="customer_id", nullable=false, updatable=false, insertable=true)
	private Customer customer;
	
	//@JsonIgnoreProperties("account")
	//@OneToMany(mappedBy="account", cascade=CascadeType.PERSIST)
	Set<Asset> assets;
	
	//@JsonIgnoreProperties("account")
	//@OneToMany(mappedBy="account", cascade=CascadeType.PERSIST)
	Set<Lot> lots;

	//@JsonIgnoreProperties("fromAccount")
	//@OneToMany(mappedBy="fromAccount", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<Transaction> sender;

	//@JsonIgnoreProperties("toAccount")
	//@OneToMany(mappedBy="toAccount", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<Transaction> receiver;
	
	//@JsonIgnoreProperties("ownerAcct")
	//@OneToMany(mappedBy="ownerAcct", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<Transaction> owner;
	
	//@Column(name="account_desc")
	private String accountDesc;
	
	//@Column(name="account_id")
	private String accountId;

	public Account() {		
	}
	
	public Set<Asset> getAssets() {
		return assets;
	}

	public void setAssets(Set<Asset> assets) {
		this.assets = assets;
		for (Asset theAsset: assets) {
			theAsset.setAccount(this);
		}
	}
	
	public Set<Lot> getLots() {
		return lots;
	}

	public void setLots(Set<Lot> lots) {
		this.lots = lots;
		for (Lot theLot: lots) {
			theLot.setAccount(this);
		}
	}
	
	public Set<Transaction> getSender() {
		return sender;
	}

	public void setSender(Set<Transaction> sender) {
		this.sender = sender;
	}

	public Set<Transaction> getReceiver() {
		return receiver;
	}

	public void setReceiver(Set<Transaction> receiver) {
		this.receiver = receiver;
	}

	public Set<Transaction> getOwner() {
		return owner;
	}

	public void setOwner(Set<Transaction> owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	//@Override
	public String toString() {
		return "Account [id=" + id + ", customer=" + customer + ", assets=" + assets + ", lots=" + lots + ", sender="
				+ sender + ", receiver=" + receiver + ", owner=" + owner + ", accountDesc=" + accountDesc
				+ ", accountId=" + accountId + "]";
	}
}
