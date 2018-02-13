package com.aiblockchain.rest.data.entity.dat;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the "TRANSACTION" database table.
 * 
 */
//@Entity
//@Table(name="\"TRANSACTION\"")
//@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 8420397683536295218L;

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "ID", columnDefinition = "INTEGER", insertable = false, updatable = false, nullable = false)
	private long transId;

	//@Column(name="AFTER_HASH")
	private String afterHash;

	//@Column(name="AFTER_TRANS")
	private String afterTrans;
	
	//@Column(name="BEFORE_HASH")
	private String beforeHash;

	//@Column(name="BEFORE_TRANS")
	private String beforeTrans;
	
	private String description;

	//bi-directional many-to-one association to Asset
	//@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="ASSET_ID", columnDefinition="INTEGER")
	private Asset asset;

	//bi-directional many-to-one association to Lot
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="LOT_ID", columnDefinition="INTEGER")
	private Lot lot;

	//bi-directional many-to-one association to Account
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="FROM_ACCT_ID", columnDefinition="INTEGER")
	private Account fromAccount;
	
	//bi-directional many-to-one association to Account
	//@ManyToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="TO_ACCT_ID", columnDefinition="INTEGER")
	private Account toAccount;

	//bi-directional many-to-one association to Account
	//@ManyToOne
	//@JoinColumn(name="OWNER_ID", columnDefinition="INTEGER")
	private Account ownerAcct;	
	
	public Transaction() {
	}

	public Transaction(String afterHash, String beforeHash, String description, Account ownerAcct, Asset asset,
			Account fromAcct, Lot lot, Account toAcct) {
		super();
		this.afterHash = afterHash;
		this.beforeHash = beforeHash;
		this.description = description;
		this.ownerAcct = ownerAcct;
		this.asset = asset;
		this.fromAccount = fromAcct;
		this.lot = lot;
		this.toAccount = toAcct;
	}

	public long getTransIdId() {
		return this.transId;
	}

	public void setTransIdId(long transId) {
		this.transId = transId;
	}

	public String getAfterHash() {
		return this.afterHash;
	}

	public void setAfterHash(String afterHash) {
		this.afterHash = afterHash;
	}

	public String getAfterTrans() {
		return this.afterTrans;
	}

	public void setAfterTrans(String afterTrans) {
		this.afterTrans = afterTrans;
	}
	
	public String getBeforeHash() {
		return this.beforeHash;
	}

	public void setBeforeHash(String beforeHash) {
		this.beforeHash = beforeHash;
	}

	public String getBeforeTrans() {
		return this.beforeTrans;
	}

	public void setBeforeTrans(String beforeTrans) {
		this.beforeTrans = beforeTrans;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getOwnerAcct() {
		return this.ownerAcct;
	}

	public void setOwnerAcct(Account ownerAcct) {
		this.ownerAcct = ownerAcct;
	}

	public Asset getAsset() {
		return this.asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Account getFromAccount() {
		return this.fromAccount;
	}

	public void setFromAccount(Account account1) {
		this.fromAccount = account1;
	}

	public Lot getLot() {
		return this.lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public Account getToAccount() {
		return this.toAccount;
	}

	public void setToAccount(Account account2) {
		this.toAccount = account2;
	}
	
	//@Override
	public String toString() {
		return "Transaction [id=" + transId + ", afterHash=" + afterHash + ", afterTrans=" + afterTrans + ", beforeHash="
				+ beforeHash + ", beforeTrans=" + beforeTrans + ", description=" + description + ", ownerAcct=" + ownerAcct
				+ ", asset=" + asset + ", fromAccount=" + fromAccount + ", lot=" + lot + ", toAccount=" + toAccount
				+ "]";
	}

}