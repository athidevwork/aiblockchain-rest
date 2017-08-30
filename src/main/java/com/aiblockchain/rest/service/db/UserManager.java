/**
 * 
 */
package com.aiblockchain.rest.service.db;

import java.util.List;

import com.aiblockchain.rest.model.Users;

/**
 * @author Athi
 *
 */
public interface UserManager {
	public void getUsers();
	public int addUser();
	public void updateUser(int id);
	public List<Users> getUsersList();
}
