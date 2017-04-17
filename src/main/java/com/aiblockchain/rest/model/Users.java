package com.aiblockchain.rest.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String age;
    private String date;
    
    // JAX-B needs this
    public Users() {}
    
	public Users(int id, String name, String age, String date) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}    
}
