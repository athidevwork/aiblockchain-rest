/**
 * 
 */
package com.aiblockchain.rest.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.repository.cre.FaultAssetRepository;
import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;

/**
 * @author Athi
 *
 */
@Service("DataFaultAssetService")
public class FaultAssetService {
	public List<FaultAsset> getAssetList() {
		List<FaultAsset> assets = new ArrayList<>();
		try {
			FaultAssetRepository assetRepo = (FaultAssetRepository) SpringDataContext.getBean("FaultAssetRepository");
			System.out.println("Start get list with assetRepo = " + assetRepo);
			System.out.println("asset count = " + assetRepo.count());
			assets = assetRepo.findAll();
			System.out.println("Assets size = " + assets.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assets;		
	}
}
