package com.aiblockchain.rest.jpa.entity.dat;

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
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * //@author Athi
 *
 */
//@Entity
//@Table(name="asset")
//@XmlRootElement(name = "Asset")
public class Asset {
	
	//@Id
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	//@JsonIgnoreProperties("asset")
	//@OneToMany(mappedBy="asset", cascade=CascadeType.PERSIST)
	Set<Transaction> transactions;
	
	//@ManyToOne
	//@JsonIgnore
    //@JoinColumn(name="acct_id", nullable=false, updatable=false, insertable=true)
	private Account account;
	
	//@Column(name="asset_id")
	private String assetId;
	private String description;
	private String cut;
	private String clarity;
	private String carat;
	private String shape;
	private String weight;
	private String measurements;
	
	//@Column(name="asset_hash")
	private String assetHash;

	public Asset() {		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	public String getClarity() {
		return clarity;
	}

	public void setClarity(String clarity) {
		this.clarity = clarity;
	}

	public String getCarat() {
		return carat;
	}

	public void setCarat(String carat) {
		this.carat = carat;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getMeasurements() {
		return measurements;
	}

	public void setMeasurements(String measurements) {
		this.measurements = measurements;
	}

	public String getAssetHash() {
		return assetHash;
	}

	public void setAssetHash(String assetHash) {
		this.assetHash = assetHash;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	//@Override
	public String toString() {
		return "Asset [id=" + id + ", transactions=" + transactions + ", account=" + account + ", assetId=" + assetId
				+ ", description=" + description + ", cut=" + cut + ", clarity=" + clarity + ", carat=" + carat
				+ ", shape=" + shape + ", weight=" + weight + ", measurements=" + measurements + ", assetHash="
				+ assetHash + "]";
	}
}
