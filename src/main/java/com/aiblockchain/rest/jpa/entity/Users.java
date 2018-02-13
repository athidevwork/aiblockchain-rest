/**
 * 
 */
package com.aiblockchain.rest.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

/**
 * @author Athi
 *
 */
@Component("Users")
@XmlRootElement
public class Users {
	List<User> users = new ArrayList<User>();

	public Users() {
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		getUsers().add(user);
	}

	@Override
	public String toString() {
		return "Users [\nusers=" + users + "]";
	}
}
