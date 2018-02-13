/**
 * 
 */
package com.aiblockchain.rest.data.repository.dat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.data.entity.dat.Asset;

/**
 * @author Athi
 *
 */
//@Repository
//@Transactional(propagation = Propagation.NESTED, value = "transactionManager")
public interface AssetRepository {//extends JpaRepository<Asset, String> {
	//public List<Asset> findAll();
	//public List<Asset> findAllByIdDesc();
}
