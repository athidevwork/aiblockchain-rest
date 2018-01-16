package com.aiblockchain.rest.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the ASSET database table.
 * 
 */
//@Entity()
//@NamedQuery(name="Asset.findAll", query="SELECT a FROM Asset a")
//@XmlRootElement(name = "Asset")
public class Asset implements Serializable {
	private static final long serialVersionUID = -8516327667509660199L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", insertable = false, updatable = false, nullable = false)
	private String assetId;

	@Column(name="ASSET_HASH")
	private String assetHash;

	@Column(name="CARAT")
	private String carat;

	@Column(name="CERTIFICATION")
	private String certification;

	@Column(name="CLARITY")
	private String clarity;

	@Column(name="COLOR")
	private String color;

	@Column(name="CUT")
	private String cut;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="MEASUREMENTS")
	private String measurements;

	@Column(name="QUALITY")
	private String quality;

	@Column(name="SHAPE")
	private String shape;

	@Column(name="WEIGHT")
	private String weight;

	//bi-directional many-to-one association to Account
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ACCT_ID", columnDefinition="INTEGER")
	private Account account;
	
	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="asset", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();

	public Asset() {
	}

	public Asset(String assetHash, String carat, String certification, String clarity, String color, String cut,
			String description, String measurements, String quality, String shape, String weight) {
		super();
		this.assetHash = assetHash;
		this.carat = carat;
		this.certification = certification;
		this.clarity = clarity;
		this.color = color;
		this.cut = cut;
		this.description = description;
		this.measurements = measurements;
		this.quality = quality;
		this.shape = shape;
		this.weight = weight;
	}

	public String getAssetId() {
		return this.assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetHash() {
		return this.assetHash;
	}

	public void setAssetHash(String assetHash) {
		this.assetHash = assetHash;
	}

	public String getCarat() {
		return this.carat;
	}

	public void setCarat(String carat) {
		this.carat = carat;
	}

	public String getCertification() {
		return this.certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public String getClarity() {
		return this.clarity;
	}

	public void setClarity(String clarity) {
		this.clarity = clarity;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCut() {
		return this.cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeasurements() {
		return this.measurements;
	}

	public void setMeasurements(String measurements) {
		this.measurements = measurements;
	}

	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getShape() {
		return this.shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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
		transaction.setAsset(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setAsset(null);

		return transaction;
	}

	@Override
	public String toString() {
		return "[id=" + assetId + ", assetHash=" + assetHash + ", carat=" + carat + ", certification=" + certification
				+ ", clarity=" + clarity + ", color=" + color + ", cut=" + cut + ", description=" + description
				+ ", measurements=" + measurements + ", quality=" + quality + ", shape=" + shape + ", weight=" + weight + "]";
				//+ ", accounts=" + accounts + ", transactions=" + transactions + "]";
	}
}