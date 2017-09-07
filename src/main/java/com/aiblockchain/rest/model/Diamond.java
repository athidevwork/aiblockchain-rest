/**
 * 
 */
package com.aiblockchain.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Athi
 *
 */
@XmlRootElement
public class Diamond implements Serializable {
	private static final long serialVersionUID = -6687050666721108516L;
	private int id;
    private String description;
    private String cut;
    private String color;
    private String clarity;
    private String carat;
    private String shape;
    private String certification;
    private String quality;
    private String weight;
    private String measurements;
    private String rowHash;
    
    public Diamond() {}
    
	public Diamond(int id, String description, String cut, String color, String clarity, String carat, String shape,
			String certification, String quality, String weight, String measurements, String rowHash) {
		super();
		this.id = id;
		this.description = description;
		this.cut = cut;
		this.color = color;
		this.clarity = clarity;
		this.carat = carat;
		this.shape = shape;
		this.certification = certification;
		this.quality = quality;
		this.weight = weight;
		this.measurements = measurements;
		this.rowHash = rowHash;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
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
	public String getRowHash() {
		return rowHash;
	}
	public void setRowHash(String rowHash) {
		this.rowHash = rowHash;
	}
	@Override
	public String toString() {
		return "Diamond [id=" + id + ", description=" + description + ", cut=" + cut + ", color=" + color + ", clarity="
				+ clarity + ", carat=" + carat + ", shape=" + shape + ", certification=" + certification + ", quality="
				+ quality + ", weight=" + weight + ", measurements=" + measurements + ", rowHash=" + rowHash + "]";
	}
}
