package com.aiblockchain.rest.jpa.entity.dat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * //@author Athi
 *
 */
//@Entity
//@Table(name="lot")
public class Lot {

	//@Id
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	//@JsonIgnoreProperties("lot")
	//@OneToMany(mappedBy="lot", cascade=CascadeType.PERSIST)
	Set<Transaction> transactions;
	
	//@ManyToOne
	//@JsonIgnore
    //@JoinColumn(name="acct_id", nullable=false, updatable=false, insertable=true)
	private Account account;
	
	//@ManyToOne
	//@JsonIgnore
    //@JoinColumn(name="asset_id", nullable=false, updatable=false, insertable=true)
	private Asset asset;
	
	//@Column(name="purchase_date")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDate;
	
	private BigDecimal price;
	
	//@Column(name="sale_date")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date saleDate;
	
	//@Column(name="sale_price")
	private BigDecimal salePrice;
	
	private int qty;
	private String status;
	
	public Lot() {		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//@Override
	public String toString() {
		return "Lot [id=" + id + ", transactions=" + transactions + ", account=" + account + ", asset=" + asset
				+ ", purchaseDate=" + purchaseDate + ", price=" + price + ", saleDate=" + saleDate + ", salePrice="
				+ salePrice + ", qty=" + qty + ", status=" + status + "]";
	}
}
