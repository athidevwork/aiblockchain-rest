/**
 * 
 */
package com.aiblockchain.rest.jpa.service.dat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.dat.Account;
import com.aiblockchain.rest.jpa.entity.dat.Address;
import com.aiblockchain.rest.jpa.entity.dat.Asset;
import com.aiblockchain.rest.jpa.entity.dat.Customer;
import com.aiblockchain.rest.jpa.entity.dat.Email;
import com.aiblockchain.rest.jpa.entity.dat.Phone;

/**
 * @author Athi
 *
 */
//@Component
@Service("customerService")
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerService {
	@Autowired
	 @PersistenceContext(unitName="aiblockchain_mysql_dat")
    private EntityManager entityManager;
    
	//@Transactional(propagation = Propagation.REQUIRED)
	public List<Customer> getCustomerList() {
	    //fetch data
	    Query q = entityManager.createQuery("select c from Customer c");
	    List<Customer> customers = (List<Customer>)q.getResultList();
	    /*System.out.println("Customers...");
	    for (Customer aCustomer: customers) {
	    	System.out.println("Legal Name: "+aCustomer.getLegalName());
	    	System.out.println("Addresses...");
	    	for (Address theAddress: aCustomer.getAddresses()) {
	    		System.out.println("Line 1:"+theAddress.getLine1());
	    		System.out.println("State: "+theAddress.getState());
	    		System.out.println("Zip: "+theAddress.getZip());
	    	}
	    	System.out.println("Phones...");
	    	for (Phone thePhone: aCustomer.getPhones()) {
	    		System.out.println("Type:"+thePhone.getPhoneType());
	    		System.out.println("Number: "+thePhone.getPhoneNumber());
	    	}	
	    	System.out.println("Emails...");
	    	for (Email theEmail: aCustomer.getEmails()) {
	    		System.out.println("Type:"+theEmail.getEmailType());
	    		System.out.println("Address: "+theEmail.getEmailAddress());
	    	}	
	    	System.out.println("Accounts...");
	    	for (Account theAccount: aCustomer.getAccounts()) {
	    		System.out.println("Account Desc: "+ theAccount.getAccountDesc());
	    		System.out.println("Assets...");
	    		for (Asset theAsset: theAccount.getAssets()) {
	    			System.out.println("Description: "+theAsset.getDescription());
	    		}
	    	}
	    	System.out.println("Address size : " + aCustomer.getAddresses().size());
	    	System.out.println("Phones size : " + aCustomer.getPhones().size());
	    	System.out.println("Emails size : " + aCustomer.getEmails().size());
	    	System.out.println("Accounts size : " + aCustomer.getAccounts().size());
	    }j
	    System.out.println("End of Cust Service");*/
		return customers;		
	}

	//@Transactional(propagation = Propagation.REQUIRED)
	public Customer getCustomer(int id) {
		System.out.println("Id in service " + id);
		Customer customer = entityManager.find(Customer.class, id);
		System.out.println("Customer in service : " + customer.getLegalName());
	    return customer;
	}
}
