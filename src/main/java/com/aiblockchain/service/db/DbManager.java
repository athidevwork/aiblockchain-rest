package com.aiblockchain.service.db;

import java.util.List;

import com.aiblockchain.rest.model.Users;

public interface DbManager {
	public void init ();
	public void shutdown ();
	public void getUsers();
	public int addUser();
	public void updateUser(int id);
	public List<Users> getUsersList();
}
