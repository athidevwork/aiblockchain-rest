/**
 * 
 */
package com.aiblockchain.rest.jpa.service.cre;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.cre.Fault;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;
import com.aiblockchain.rest.security.Utils;

/**
 * @author Athi
 *
 */
@Service("SampleFaultDataService")
public class SampleFaultDataService {
	@Autowired
	@PersistenceContext(unitName="aiblockchain_mysql_cre")
	private EntityManager entityManager;
	
    @Transactional(transactionManager = "transactionManagerCre", propagation = Propagation.REQUIRED)
	public void saveSampleData() {
    	System.out.println("Setting up sample data");
		
    	try {
        	System.out.println("Setting up sample data");
        	
        	FaultAsset faultAsset = new FaultAsset();
        	faultAsset.setAtype("CRE");
        	faultAsset.setCategory("Plumbing");
        	faultAsset.setSubCategory("Master Bath");
        	faultAsset.setDescription("Master Bath Faucet");
        	entityManager.persist(faultAsset);
        	
        	Fault fault = new Fault();
        	fault.setDate(LocalDateTime.now());
        	fault.setAibcStatus("New");
        	fault.setFaultAsset(faultAsset);
        	
			System.out.println("Encoded Fault Hash string : " + Utils.sha256(fault.toString()));		
        	fault.setSignature(Utils.sha256(fault.toString()));
        	
			System.out.println("EntityManager - " + entityManager);	
			//System.out.println(customer1.toString());
			//System.out.println(customer2.toString());
			
			//entityManager.getTransaction().begin();
			entityManager.persist(fault);
			//entityManager.getTransaction().commit();
			
			System.out.println("After entities persist");    		
       	} catch (Exception e) {
    		e.printStackTrace();
    	}
	    	    
	    //close connections
	    //em.close();
	    //PersistenceManager.INSTANCE.close();		
	}
}
