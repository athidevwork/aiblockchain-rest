package com.aiblockchain.rest.data.entity.dat;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
//@Entity
//@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = -7571837407885901851L;

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(columnDefinition = "INTEGER")
	//@Column(name="ID", insertable = false, updatable = false, nullable = false)
	private String acctId;

	//bi-directional many-to-one association to Customer
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="CUSTOMER_ID", columnDefinition="INTEGER")
	private Customer customer;

	//bi-directional many-to-one association to Lot
	//@OneToMany(mappedBy="asset", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Asset> assets;
	
	//bi-directional many-to-one association to Lot
	//@OneToMany(mappedBy="account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lot> lots;

	//bi-directional many-to-one association to Transaction
	//@OneToMany(mappedBy="fromAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> sender;

	//bi-directional many-to-one association to Transaction
	//@OneToMany(mappedBy="toAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> receiver;
	
	//bi-directional many-to-one association to Transaction
	//@OneToMany(mappedBy="ownerAcct", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> owner;	

	public Account() {
	}

	public Account(Customer customer) {
		super();
		//this.acctId = acctId;
		this.customer = customer;
	}

	public String getAcctId() {
		return this.acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/*public List<Asset> getAssets() {
		return this.assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}*/
	
	public List<Lot> getLots() {
		return this.lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

	public Lot addLot(Lot lot) {
		getLots().add(lot);
		lot.setAccount(this);

		return lot;
	}

	public Lot removeLot(Lot lot) {
		getLots().remove(lot);
		lot.setAccount(null);

		return lot;
	}

	public List<Transaction> getSender() {
		return this.sender;
	}

	public void setSender(List<Transaction> sender) {
		this.sender = sender;
	}

	public Transaction addsender(Transaction sender) {
		getSender().add(sender);
		sender.setFromAccount(this);

		return sender;
	}

	public Transaction removesender(Transaction sender) {
		getSender().remove(sender);
		sender.setFromAccount(null);

		return sender;
	}

	public List<Transaction> getreceiver() {
		return this.receiver;
	}

	public void setreceiver(List<Transaction> receiver) {
		this.receiver = receiver;
	}

	public Transaction addreceiver(Transaction receiver) {
		getreceiver().add(receiver);
		receiver.setToAccount(this);

		return receiver;
	}

	public Transaction removereceiver(Transaction receiver) {
		getreceiver().remove(receiver);
		receiver.setToAccount(null);

		return receiver;
	}

	public List<Transaction> getowner() {
		return this.owner;
	}

	public void setowner(List<Transaction> owner) {
		this.owner = owner;
	}

	public Transaction addowner(Transaction owner) {
		getowner().add(owner);
		owner.setOwnerAcct(this);

		return owner;
	}

	public Transaction removeowner(Transaction owner) {
		getowner().remove(owner);
		owner.setOwnerAcct(null);

		return owner;
	}
	
	//@Override
	public String toString() {
		return "Account [id=" + acctId + ", customer=" + customer + ", lots="
				+ lots + ", sender=" + sender + ", receiver=" + receiver + ", owner=" + owner + "]";
	}

}