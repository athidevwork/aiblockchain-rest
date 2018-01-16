/**
 * 
 */
package com.aiblockchain.rest.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Configuration;

/**
 * @author Athi
 *
 */
//@Configuration
public enum PersistenceManager {
	INSTANCE;
	
	private EntityManagerFactory emFactory;
	private PersistenceManager() {
		emFactory = Persistence.createEntityManagerFactory("dat");
	}
	public EntityManager getEntityManager() {
	  return emFactory.createEntityManager();
	}
	public void close() {
	  emFactory.close();
	}
}
