package com.aiblockchain.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServerProperties {
	@XmlElement
	String name;
	@XmlElement
	String value;
	
	public ServerProperties(String name, String value) {
		this.name = name;
		this.value = value;
	} 
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}
