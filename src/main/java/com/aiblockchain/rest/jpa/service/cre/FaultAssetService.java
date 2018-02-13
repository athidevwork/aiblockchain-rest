/**
 * 
 */
package com.aiblockchain.rest.jpa.service.cre;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiblockchain.rest.jpa.entity.cre.Fault;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;


/**
 * @author Athi
 *
 */
@Service("FaultAssetService")
public class FaultAssetService {
	@Autowired
	@PersistenceContext(unitName="aiblockchain_mysql_cre")
	private EntityManager entityManager;
	
	public List<FaultAsset> getAssetList() {
	    Query query = entityManager.createQuery("select c from FaultAsset c");
	    List<FaultAsset> assets = (List<FaultAsset>) query.getResultList();
	    //System.out.println("Assets...");
	    /*for (FaultAsset anAsset: assets) {
	    	System.out.println("Asset" + anAsset);
	    	System.out.println("Faults...");
	    	for (Fault theFault: anAsset.getFaults()) {
	    		System.out.println("Fault: "+theFault);
	    	}
	    }*/		
		return assets;		
	}

	public List<FaultAsset> getAssetCategoryList(String category) {
	    Query query = entityManager.createQuery("select c from FaultAsset c");
	    List<FaultAsset> assets = (List<FaultAsset>) query.getResultList();
	    List<FaultAsset> filteredAsset = new ArrayList<FaultAsset>();
	    for (FaultAsset asset : assets) {
	    	if (asset.getCategory().contains(category) || asset.getSubCategory().contains(category))
	    		filteredAsset.add(asset);
	    }
		return filteredAsset;
	}
}
