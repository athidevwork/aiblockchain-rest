/**
 * 
 */
package com.aiblockchain.rest.jpa.service.cre;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiblockchain.rest.jpa.entity.cre.CreUser;
import com.aiblockchain.rest.jpa.entity.cre.Fault;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;

/**
 * @author Athi
 *
 */
@Service("FaultService")
public class FaultService {
	@Autowired
	@PersistenceContext(unitName="aiblockchain_mysql_cre")
	private EntityManager entityManager;
	
	public List<Fault> getFaultList() {
	    Query query = entityManager.createQuery("select c from Fault c");
	    List<Fault> faults = (List<Fault>)query.getResultList();
	    //System.out.println("Faults...");
	    /*for (Fault aFault : faults) {
	    	//System.out.println("Fault: " + aFault);
	    	//System.out.println("Id : "+aFault.getId());
	    	//System.out.println("Asset...");
	    	FaultAsset faultAsset = aFault.getFaultAsset();
	    	System.out.println("FaultAsset: " + faultAsset);
	    }*/	
		return faults;		
	}	
}
