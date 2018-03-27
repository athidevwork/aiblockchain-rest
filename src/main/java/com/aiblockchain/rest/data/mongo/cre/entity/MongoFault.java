/**
 * 
 */
package com.aiblockchain.rest.data.mongo.cre.entity;

import org.springframework.data.annotation.Id;

/**
 * @author Athi
 *
 */
public class MongoFault {
	@Id
	int id;
	String fDate;
	String category;
	String subCategory;
	String description;	
	String signature;
	String aibcStatus;
	String aibcTrans;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
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
		return signature;
	}
	public void setSignature(String fSignature) {
		this.signature = fSignature;
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
	@Override
	public String toString() {
		return "MongoFault [id=" + id + ", fDate=" + fDate + ", category=" + category + ", subCategory=" + subCategory
				+ ", description=" + description + ", fSignature=" + signature + ", aibcStatus=" + aibcStatus
				+ ", aibcTrans=" + aibcTrans + "]";
	}
}
