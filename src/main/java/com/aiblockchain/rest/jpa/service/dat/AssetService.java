package com.aiblockchain.rest.jpa.service.dat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.dat.Asset;
import com.aiblockchain.rest.jpa.entity.dat.Customer;

/**
 * @author Athi
 *
 */
@Service("assetService")
@Transactional(propagation = Propagation.REQUIRED)
public class AssetService {

	@Autowired
    @PersistenceContext(unitName="aiblockchain_mysql_dat")
    private EntityManager entityManager;
	
	//@Transactional(propagation = Propagation.REQUIRED)
	public List<Asset> getAssetList() {
	    //fetch data
		//System.out.println("EntityManager in asset service - " + entityManager);	
	    Query query = entityManager.createQuery("select c from Asset c");
	    List<Asset> assets = (List<Asset>)query.getResultList();
	    //System.out.println("Assets...");
	    for (Asset anAsset: assets) {
	    	System.out.println("Description: "+anAsset.getDescription());
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
		return assets;		
	}
	
	//@Transactional(propagation = Propagation.REQUIRED)
	public Asset getAsset(int id) {
		System.out.println("Id in service " + id);
		Asset asset = entityManager.find(Asset.class, id);
		System.out.println("Customer in service : " + asset.getDescription());
	    return asset;
	}	
}
