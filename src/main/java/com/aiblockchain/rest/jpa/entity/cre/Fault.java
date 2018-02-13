/**
 * 
 */
package com.aiblockchain.rest.jpa.entity.cre;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	LocalDateTime fDate;
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
	@ManyToOne
	@JsonIgnore
	//@JsonManagedReference
    @JoinColumn(name="asset_id")//, nullable=false, updatable=false, insertable=true)
	private FaultAsset faultAsset;
	
	public Fault() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return fDate;
	}
	public void setDate(LocalDateTime date) {
		this.fDate = date;
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
	/*public LocalDateTime getfDate() {
		return fDate;
	}
	public void setfDate(LocalDateTime fDate) {
		this.fDate = fDate;
	}
	public String getfSignature() {
		return fSignature;
	}
	public void setfSignature(String fSignature) {
		this.fSignature = fSignature;
	}*/
	public FaultAsset getFaultAsset() {
		return faultAsset;
	}
	public void setFaultAsset(FaultAsset faultAsset) {
		this.faultAsset = faultAsset;
	}
	@Override
	public String toString() {
		return "Fault [id=" + id + ", fDate=" + fDate + ", fSignature=" + fSignature + ", aibcStatus=" + aibcStatus
				+ ", aibcTrans=" + aibcTrans + ", faultAsset=" + faultAsset + "]";
	}
}
