/**
 * 
 */
package com.aiblockchain.rest.data.mongo.cre.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author Athi
 *
 */
@Document(collection = "faults")
public class MongoFaultAsset {
	@Id	
	int id;
	String atype;
	String building;
	String location;
	String unit;
	String description;
	private List<MongoFault> faults = new ArrayList<MongoFault>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MongoFault> getFaults() {
		return faults;
	}
	public void setFaults(List<MongoFault> faults) {
		this.faults = faults;
	}
	@Override
	public String toString() {
		return "MFaultAsset [id=" + id + ", atype=" + atype + ", building=" + building + ", location=" + location
				+ ", unit=" + unit + ", description=" + description + ", faults=" + faults + "]";
	}
}
