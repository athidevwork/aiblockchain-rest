/**
 * 
 */
package com.aiblockchain.rest.jpa.entity;

/**
 * @author Athi
 *
 */
public class User {
	String user;
	String username;
	String password;
	String authorization;
	
	public User(String user, String encUserName, String encPassword, String authorization) {
		this.user = user;
		this.username = encUserName;
		this.password = encPassword;
		this.authorization = authorization;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	@Override
	public String toString() {
		return "User [user=" + user + ", username=" + username + ", password=" + password + ", authorization="
				+ authorization + "]";
	}
}
