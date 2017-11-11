package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LOT database table.
 * 
 */
@Entity
@NamedQuery(name="Lot.findAll", query="SELECT l FROM Lot l")
public class Lot implements Serializable {
	private static final long serialVersionUID = -6543933234300704588L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "INTEGER")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;

	@Column(name="PURCHASE_PRICE")
	private BigDecimal purchasePrice;

	@Temporal(TemporalType.DATE)
	@Column(name="SALE_DATE")
	private Date saleDate;

	@Column(name="SALE_PRICE")
	private BigDecimal salePrice;

	//bi-directional many-to-one association to Account
	@ManyToOne(fetch=FetchType.LAZY)
	private Account account;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="lot")
	private List<Transaction> transactions;

	public Lot() {
	}

	public Lot(Date purchaseDate, BigDecimal purchasePrice, Date saleDate, BigDecimal salePrice, Account account) {
		super();
		this.purchaseDate = purchaseDate;
		this.purchasePrice = purchasePrice;
		this.saleDate = saleDate;
		this.salePrice = salePrice;
		this.account = account;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Date getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setLot(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setLot(null);

		return transaction;
	}

	@Override
	public String toString() {
		return "Lot [id=" + id + ", purchaseDate=" + purchaseDate + ", purchasePrice=" + purchasePrice + ", saleDate="
				+ saleDate + ", salePrice=" + salePrice + ", account=" + account + ", transactions=" + transactions
				+ "]";
	}

}