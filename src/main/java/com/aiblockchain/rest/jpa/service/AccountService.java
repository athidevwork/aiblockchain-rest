package com.aiblockchain.rest.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.Account;

/**
 * @author Athi
 *
 */
@Service("accountService")
public class AccountService {
	@Autowired
    //@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Account> getAccountList() {
	    //fetch data
	    Query q = entityManager.createQuery("select c from Account c");
	    List<Account> accounts = (List<Account>)q.getResultList();
	    /*Query q = entityManager.createQuery("from account");
	    List<Account> accounts = (List<Account>)q.getResultList();*/
	    //System.out.println("Accounts...");
	    for (Account anAccount: accounts) {
	    	System.out.println("Description: "+anAccount.getAccountDesc());
	    	/*System.out.println("Accounts...");
	    	for (Account theAcct: anAsset.get) {
	    		System.out.println("Account #:"+theAcct.getAccountNumber());
	    	}
	    	System.out.println("Accounts...");
	    	for (Account theAccount: aCustomer.getAccounts()) {
	    		System.out.println("Account Number: "+ theAccount.getAccountNumber());
	    		System.out.println("Assets...");
	    		for (Asset theAsset: theAccount.getAssets()) {
	    			System.out.println("Description: "+theAsset.getDescription());
	    		}
	    	}*/
	    }		
		return accounts;		
	}
}
