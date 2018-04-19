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
@Table(name="fault")
@XmlRootElement(name="Fault")
public class Fault {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;
	String startDate;
	String endDate;
	String category;
	String subCategory;
	String description;	
	String fSignature;
	@Column(name="aibc_status")
	String aibcStatus;
	@Column(name="aibc_trans")
	String aibcTrans;
	
	//bi-directional many-to-one association to Asset
	//@JsonIgnore
	//@JsonBackReference
	//@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinColumn(name="asset_id", columnDefinition="INTEGER", nullable=false, updatable=false, insertable=true)
	@ManyToOne(optional=false)
	@JsonIgnore
	//@JsonManagedReference
    @JoinColumn(name="asset_id")//, nullable=false, updatable=false, insertable=true)
	private FaultAsset asset;
	
	public Fault() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public String getSignature() {
		return fSignature;
	}
	public void setSignature(String signature) {
		this.fSignature = signature;
	}
	public String getAibcStatus() {
		return aibcStatus;
	}
	public void setAibcStatus(String aibcStatus) {
		this.aibcStatus = aibcStatus;
	}
	public String getAibcTrans() {
		return aibcTrans;
	}
	public void setAibcTrans(String aibcTrans) {
		this.aibcTrans = aibcTrans;
	}	
	public FaultAsset getFaultAsset() {
		return asset;
	}
	public void setFaultAsset(FaultAsset asset) {
		this.asset = asset;
	}

	@Override
	public String toString() {
		return "Fault [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", category=" + category
				+ ", subCategory=" + subCategory + ", description=" + description + ", fSignature=" + fSignature
				+ ", aibcStatus=" + aibcStatus + ", aibcTrans=" + aibcTrans + ", asset=" + asset + "]";
	}

}
