package com.aiblockchain.rest.service.db;

import java.sql.Connection;

public interface DbManager {
	public void init ();
	public void shutdown ();
	public Connection getConnection();
}
