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
	String category;
	String subCategory;
	String description;
	
	//bi-directional many-to-one association to Fault
	//@JsonIgnoreProperties("faultAsset")
	//@JsonManagedReference
	//@Column(nullable = true)
	//@OneToMany(mappedBy="faultAsset", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("faultAsset")
	//@JsonBackReference
	@OneToMany(mappedBy="faultAsset", cascade=CascadeType.PERSIST)
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
	public void setAtype(String type) {
		this.atype = type;
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
	public Set<Fault> getFaults() {
		return faults;
	}
	public void setFaults(Set<Fault> faults) {
		this.faults = faults;
	}
	@Override
	public String toString() {
		return "FaultAsset [id=" + id + ", atype=" + atype + ", category=" + category + ", subCategory=" + subCategory
				+ ", description=" + description + ", faults=" + faults + "]";
	}
}
