/**
 * 
 */
package com.aiblockchain.rest.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.entity.dat.Asset;
import com.aiblockchain.rest.data.repository.dat.AccountRepository;
import com.aiblockchain.rest.data.repository.dat.AssetRepository;
import com.aiblockchain.rest.data.repository.dat.TransactionRepository;

/**
 * @author Athi
 *
 */
//@Service("dataAssetService")
//@Transactional
public class DataAssetService {
	/*@Autowired
	AssetRepository assetRepo;
	@Autowired
	AccountRepository acctRepo;
	@Autowired
	TransactionRepository transRepo;*/
	
	//@Transactional
	public List<Asset> getAssetList() {
		List<Asset> assets = new ArrayList<>();
		try {
			//AssetRepository assetRepo = (AssetRepository) SpringDataContext.getBean("assetRepository");
			//System.out.println("Start get list with assetRepo = " + assetRepo);
			//System.out.println("asset count = " + assetRepo.count());
			//assets = assetRepo.findAll();
			//System.out.println("Service Assets = " + assets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assets;		
	}

	public Asset saveAsset(Asset asset) {
		//return assetRepo.save(asset);
		return null;
	}
}
