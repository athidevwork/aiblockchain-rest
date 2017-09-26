/**
 * 
 */
package com.aiblockchain.rest.model;

import java.io.Serializable;

/**
 * @author Athi
 *
 */
public class DiamondHistory implements Serializable {
	private static final long serialVersionUID = -6657652497859495359L;
	private int id;
	private String date;
	private String description;
	
	public DiamondHistory() {} 
	
	public DiamondHistory(int id, String date, String description) {
		this.id = id;
		this.date = date;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "DiamondHistory [id=" + id + ", date=" + date + ", description=" + description + "]";
	}
}
