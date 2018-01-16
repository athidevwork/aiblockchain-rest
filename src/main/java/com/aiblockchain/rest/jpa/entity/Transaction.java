/**
 * 
 */
package com.aiblockchain.rest.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Athi
 *
 */
@Entity
@Table(name="\"transaction\"")
@XmlRootElement(name = "Transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="after_hash")
	private String afterHash;

	@Column(name="after_trans")
	private String afterTrans;
	
	@Column(name="before_hash")
	private String beforeHash;

	@Column(name="before_trans")
	private String beforeTrans;
	
	private String description;

	//bi-directional many-to-one association to Asset
	@ManyToOne
	@JoinColumn(name="asset_id")
	@JsonIgnore
	private Asset asset;

	//bi-directional many-to-one association to Lot
	@ManyToOne
	@JoinColumn(name="lot_id")
	@JsonIgnore
	private Lot lot;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="from_acct_id")
	@JsonIgnore
	private Account fromAccount;
	
	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="to_acct_id")
	@JsonIgnore
	private Account toAccount;

	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="owner_id")
	@JsonIgnore
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

	public int getId() {
		return this.id;
	}

	public void setId(int transId) {
		this.id = transId;
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

	public void setFromAccount(Account fromAcct) {
		this.fromAccount = fromAcct;
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

	public void setToAccount(Account toAcct) {
		this.toAccount = toAcct;
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", afterHash=" + afterHash + ", afterTrans=" + afterTrans + ", beforeHash="
				+ beforeHash + ", beforeTrans=" + beforeTrans + ", description=" + description + ", ownerAcct=" + ownerAcct
				+ ", asset=" + asset + ", fromAccount=" + fromAccount + ", lot=" + lot + ", toAccount=" + toAccount
				+ "]";
	}
}
