/**
 * 
 */
package com.aiblockchain.rest.jpa.entity.cre;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Athi
 *
 */
@Entity
@Table(name="asset")
@XmlRootElement(name="Asset")
public class FaultAsset {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
	int id;
	String atype;
	String building;
	String location;
	String unit;
	String description;
	
	//bi-directional many-to-one association to Fault
	//@JsonIgnoreProperties("faultAsset")
	//@JsonManagedReference
	//@Column(nullable = true)
	//@OneToMany(mappedBy="faultAsset", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("faultAsset")
	//@JsonBackReference
	@OneToMany(mappedBy="asset")
	private Set<Fault> faults = new HashSet<Fault>();
	
	public FaultAsset() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAtype() {
		return atype;
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
	public void setAtype(String type) {
		this.atype = type;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Fault> getFaults() {
		return faults;
	}
	public void setFaults(Set<Fault> faults) {
		this.faults = faults;
	}
	@Override
	public String toString() {
		return "FaultAsset [id=" + id + ", atype=" + atype + ", building=" + building + ", location=" + location
				+ ", unit=" + unit + ", description=" + description + ", faults=" + faults + "]";
	}
}
