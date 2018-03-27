/**
 * 
 */
package com.aiblockchain.rest.data.mongo.cre.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.mongo.cre.entity.MongoFaultAsset;

/**
 * @author Athi
 * @param <MongoFaultAsset>
 * @param <MongoFaultAsset>
 *
 */
@Repository("MongoFaultAssetRepository")
public interface MongoFaultAssetRepository extends MongoRepository <MongoFaultAsset, String> {
	//public List<MongoFaultAsset> findByBuildingAndLocationAndUnitOrderByBuildingAscAndLocationAscAndUnitAsc(String building, String location, String unit);
	public List<MongoFaultAsset> findByBuildingAndLocationAndUnit(String building, String location, String unit);
}
