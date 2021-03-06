/**
 * 
 */
package com.aiblockchain.rest.jpa.service.cre;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.cre.CreUser;
import com.aiblockchain.rest.jpa.entity.cre.Fault;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;
import com.aiblockchain.rest.security.Utils;

/**
 * @author Athi
 *
 */
@Service("SampleFaultDataService")
@Transactional(transactionManager = "transactionManagerCre", propagation = Propagation.REQUIRED)
public class FaultDataService {
	@Autowired
	@PersistenceContext(unitName="aiblockchain_mysql_cre")
	private EntityManager entityManager;
	
    //@Transactional(transactionManager = "transactionManagerCre", propagation = Propagation.REQUIRED)
	public void saveSampleData() {
    	System.out.println("Setting up sample data");
		
    	try {
        	System.out.println("Setting up sample data");
        	
        	FaultAsset faultAsset = new FaultAsset();
        	faultAsset.setAtype("CRE");
        	faultAsset.setBuilding("Building 1");
        	faultAsset.setLocation("3rd Floor");
        	faultAsset.setUnit("305");        	
        	faultAsset.setDescription("Scott's Apartment");   

        	entityManager.persist(faultAsset);
        	
        	Fault fault = new Fault();
        	Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            System.out.println("Today's date is: "+dateFormat.format(date));

            fault.setStartDate(dateFormat.format(date));
	    	fault.setEndDate(dateFormat.format(date));
        	fault.setCategory("Plumbing");
        	fault.setSubCategory("Master Bath");     	
        	fault.setDescription("Master Batch Left Faucet Leak");
        	fault.setAibcStatus("New");
        	fault.setFaultAsset(faultAsset);
        	
			System.out.println("Encoded Fault Hash string : " + Utils.sha256(fault.toString()));		
        	fault.setSignature(Utils.sha256(fault.toString()));
        	
			System.out.println("EntityManager - " + entityManager);	
			
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

	public FaultAsset saveFault(FaultAsset faultAsset) {
		System.out.println("Fault asset in service : " + faultAsset);
	    Query query = entityManager.createQuery("select c from FaultAsset c "
	    		+ " where c.building = :building"
	    		+ " and c.location = :location"
	    		+ " and c.unit = :unit")
	    		.setParameter("building", faultAsset.getBuilding())
	    		.setParameter("location", faultAsset.getLocation())
	    		.setParameter("unit", faultAsset.getUnit());

	    List<FaultAsset> dbAsset = (List<FaultAsset>) query.getResultList();
	    
	    FaultAsset saveToAsset = new FaultAsset();
	    if (dbAsset.size() == 0) {
	    	System.out.println("Creating new asset");
	    	//saveToAsset = new FaultAsset();
	    	saveToAsset.setAtype("CRE");
	    	saveToAsset.setBuilding(faultAsset.getBuilding());
	    	saveToAsset.setLocation(faultAsset.getLocation());
	    	saveToAsset.setUnit(faultAsset.getUnit());
	    	entityManager.persist(saveToAsset);
	    }
	    else {
	    	System.out.println("Found existing asset");
	    	saveToAsset.setAtype("CRE");
	    	saveToAsset.setId(dbAsset.get(0).getId());
	    	saveToAsset.setBuilding(dbAsset.get(0).getBuilding());
	    	saveToAsset.setLocation(dbAsset.get(0).getLocation());
	    	saveToAsset.setUnit(dbAsset.get(0).getUnit());
	    	/*entityManager.flush();
	    	entityManager.clear();*/
	    	//entityManager.persist(saveToAsset);
	    }
	    
	    System.out.println ("Saved or Existing asset : " + saveToAsset.toString());
	    for (Fault fault : faultAsset.getFaults()) {
	    	//Fault fault = new Fault();
	    	Date date = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	        System.out.println("Today's date is: "+dateFormat.format(date));

	    	fault.setStartDate(dateFormat.format(date));
	    	fault.setEndDate(dateFormat.format(date));
	    	fault.setCategory(fault.getCategory());
	    	fault.setSubCategory(fault.getSubCategory());
	    	fault.setDescription(fault.getDescription());
	    	fault.setAibcStatus("New");
	    	fault.setFaultAsset(saveToAsset);
	    	
			System.out.println("Encoded Fault Hash string : " + Utils.sha256(fault.toString()));		
	    	fault.setSignature(Utils.sha256(fault.toString()));
	    	
	    	System.out.println ("Saved fault : " + fault.toString());
	    	faultAsset.getFaults().add(fault);
			entityManager.persist(fault);		    	
	    }

		return faultAsset;
	}

	public CreUser savecreUser(CreUser user) {
		System.out.println("Fault asset in service : " + user);
	    Query query = entityManager.createQuery("select c from CreUser c "
	    		+ " where c.email = :email")
	    		.setParameter("email", user.getEmail());

	    List<CreUser> dbUser = (List<CreUser>) query.getResultList();
	    
	    CreUser saveToUser = new CreUser();
	    if (dbUser.size() == 0) {
	    	System.out.println("Creating new user");
	    	saveToUser.setEmail(user.getEmail());
	    	saveToUser.setFirstName(user.getFirstName());
	    	saveToUser.setLastName(user.getLastName());
	    	saveToUser.setUsername(user.getUsername());
	    	saveToUser.setPassword(user.getPassword());
	    	saveToUser.setAuthorization(user.getAuthorization());
	    	saveToUser.setStatus("ACTIVE");
	    	entityManager.persist(saveToUser);
	    }
	    else {
	    	System.out.println("Found existing user");
	    	saveToUser.setId(dbUser.get(0).getId());
	    	saveToUser.setEmail(dbUser.get(0).getEmail());
	    	saveToUser.setFirstName(dbUser.get(0).getFirstName());
	    	saveToUser.setLastName(dbUser.get(0).getLastName());
	    	saveToUser.setUsername(dbUser.get(0).getUsername());
	    	saveToUser.setPassword(dbUser.get(0).getPassword());
	    	saveToUser.setAuthorization(dbUser.get(0).getAuthorization());
	    	saveToUser.setStatus(dbUser.get(0).getStatus());
	    }
	    return saveToUser;
	}

	public List<CreUser> getCreUsersList() {
	    Query query = entityManager.createQuery("select c from CreUser c");
	    List<CreUser> users = (List<CreUser>) query.getResultList();	
		return users;
	}

	public CreUser getAuthorization(String auth) {
	    Query query = entityManager.createQuery("select c from CreUser c "
	    		+ " where c.authorization = :auth")
	    		.setParameter("auth", auth);

	    List<CreUser> dbUser = (List<CreUser>) query.getResultList();
	    return dbUser.get(0);
	}

	public CreUser getValidatedCreUsers(String authorization) {
		CreUser user = getAuthorization(authorization) ;
		System.out.println ("Auth User : " + user);
		return user;
	}

	public CreUser getValidatedCreUsers(String username, String password) {
	    Query query = entityManager.createQuery("select c from CreUser c "
	    		+ " where c.username = :username"
	    		+ " and c.password = :password")
	    		.setParameter("username", username)
	    		.setParameter("password", password);

	    List<CreUser> dbUser = (List<CreUser>) query.getResultList();
	    if (dbUser.isEmpty())
	    	return null;
	    else
	    	return dbUser.get(0);
	}
}
