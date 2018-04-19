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
	//@Id
	//int id;
	String startDate;
	String endDate;
	String category;
	String subCategory;
	String description;	
	String signature;
	String aibcStatus;
	String aibcTrans;
	
	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	public String getCategory() {
		return category;
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
		return "MongoFault [startDate=" + startDate + ", endDate=" + endDate + ", category=" + category
				+ ", subCategory=" + subCategory + ", description=" + description + ", signature=" + signature
				+ ", aibcStatus=" + aibcStatus + ", aibcTrans=" + aibcTrans + "]";
	}
}
