package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = -7571837407885901851L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "INTEGER")
	private long id;

	@Column(name="ACCT_ID")
	private String acctId;

	//bi-directional many-to-one association to Asset
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSET_ID", columnDefinition="INTEGER")
	private Asset asset;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID", columnDefinition="INTEGER")
	private Customer customer;

	//bi-directional many-to-one association to Lot
	@OneToMany(mappedBy="account")
	private List<Lot> lots;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="fromAccount")
	private List<Transaction> transactions1;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="toAccount")
	private List<Transaction> transactions2;

	public Account() {
	}

	public Account(String acctId, Asset asset, Customer customer) {
		super();
		this.acctId = acctId;
		this.asset = asset;
		this.customer = customer;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAcctId() {
		return this.acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public Asset getAsset() {
		return this.asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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

	public List<Transaction> getTransactions1() {
		return this.transactions1;
	}

	public void setTransactions1(List<Transaction> transactions1) {
		this.transactions1 = transactions1;
	}

	public Transaction addTransactions1(Transaction transactions1) {
		getTransactions1().add(transactions1);
		transactions1.setFromAccount(this);

		return transactions1;
	}

	public Transaction removeTransactions1(Transaction transactions1) {
		getTransactions1().remove(transactions1);
		transactions1.setFromAccount(null);

		return transactions1;
	}

	public List<Transaction> getTransactions2() {
		return this.transactions2;
	}

	public void setTransactions2(List<Transaction> transactions2) {
		this.transactions2 = transactions2;
	}

	public Transaction addTransactions2(Transaction transactions2) {
		getTransactions2().add(transactions2);
		transactions2.setToAccount(this);

		return transactions2;
	}

	public Transaction removeTransactions2(Transaction transactions2) {
		getTransactions2().remove(transactions2);
		transactions2.setToAccount(null);

		return transactions2;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", acctId=" + acctId + ", asset=" + asset + ", customer=" + customer + ", lots="
				+ lots + ", transactions1=" + transactions1 + ", transactions2=" + transactions2 + "]";
	}

}