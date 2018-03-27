/**
 * 
 */
package com.aiblockchain.rest.data.mongo.cre.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.mongo.cre.entity.MongoFault;
import com.aiblockchain.rest.data.mongo.cre.entity.MongoFaultAsset;
import com.aiblockchain.rest.data.mongo.cre.respository.MongoFaultAssetRepository;
import com.aiblockchain.rest.security.Utils;

/**
 * @author Athi
 *
 */
@Service("MongoFaultAssetService")
public class MongoFaultAssetService {

	public List<MongoFaultAsset> getAssetList() {
		List<MongoFaultAsset> assets = new ArrayList<>();
		try {
			MongoFaultAssetRepository assetRepo = (MongoFaultAssetRepository) SpringDataContext.getBean("MongoFaultAssetRepository");
			System.out.println("Start get list with assetRepo = " + assetRepo);
			System.out.println("asset count = " + assetRepo.count());
			assets = assetRepo.findAll();
			System.out.println("Assets size = " + assets.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assets;		
	}

	public MongoFaultAsset saveFaultAsset(MongoFaultAsset faultAsset) {
		MongoFaultAssetRepository assetRepo = (MongoFaultAssetRepository) SpringDataContext.getBean("MongoFaultAssetRepository");
		System.out.println("Save with repo : " + assetRepo);
		for (MongoFault fault : faultAsset.getFaults()) {
			System.out.println("Encoded Fault Hash string : " + Utils.sha256(fault.toString()));		
	    	fault.setSignature(Utils.sha256(fault.toString()));
		}
		return assetRepo.save(faultAsset);
	}

	public boolean updateFaultAsset(int id, MongoFaultAsset asset) {
		MongoFaultAssetRepository assetRepo = (MongoFaultAssetRepository) SpringDataContext.getBean("MongoFaultAssetRepository");
		System.out.println("Save with repo : " + assetRepo);
		if (assetRepo.existsById(String.valueOf(id))) {
			Optional<MongoFaultAsset> optional = assetRepo.findById(String.valueOf(id));
			MongoFaultAsset dbAsset =  optional.get();
			int i=0;
			for (MongoFault fault : dbAsset.getFaults()) {
				fault.setAibcTrans(asset.getFaults().get(i).getAibcTrans());
				fault.setAibcStatus(asset.getFaults().get(i).getAibcStatus());
				i++;
			}
			assetRepo.save(dbAsset);
			return true;
		}
		else {
			System.out.println("id " + id + " not found in database.");
			return false;
		}
	}
	
	public boolean deleteFaultAsset(int id) {
		MongoFaultAssetRepository assetRepo = (MongoFaultAssetRepository) SpringDataContext.getBean("MongoFaultAssetRepository");
		System.out.println("Save with repo : " + assetRepo);
		if (assetRepo.existsById(String.valueOf(id))) {
			//assetRepo.deleteById(String.valueOf(id));
			Optional<MongoFaultAsset> optional = assetRepo.findById(String.valueOf(id));
			MongoFaultAsset asset =  optional.get();
			assetRepo.delete(asset);
			return true;
		}
		else {
			System.out.println("id " + id + " not found in database.");
			return false;
		}
	}

	public MongoFaultAsset getFaultAsset(int id) {
		MongoFaultAssetRepository assetRepo = (MongoFaultAssetRepository) SpringDataContext.getBean("MongoFaultAssetRepository");		
		System.out.println("ID : " + id);
		MongoFaultAsset asset = null;
		Optional<MongoFaultAsset> optional = assetRepo.findById(String.valueOf(id));
		System.out.print("optional : " + optional);
		asset =  optional.get();
		System.out.println("Asset in service : " + asset);		
		if (assetRepo.existsById(String.valueOf(id))) {
			Optional<MongoFaultAsset> optionalA = assetRepo.findById(String.valueOf(id));
			asset =  optionalA.get();	
			System.out.println("Exists Asset in service : " + asset);
		}
		return asset;
	}

	public List<MongoFaultAsset> getFaultAsset(String building, String location, String unit) {
		MongoFaultAssetRepository assetRepo = (MongoFaultAssetRepository) SpringDataContext.getBean("MongoFaultAssetRepository");
		System.out.println("Save with repo : " + assetRepo);
		List<MongoFaultAsset> assets = assetRepo.findByBuildingAndLocationAndUnit(building, location, unit);
		System.out.println("Assets in service : " + assets);
		return assets;
	}
}
