/**
 * 
 */
package com.aiblockchain.rest.data.repository.cre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.cre.FaultAsset;

/**
 * @author Athi
 *
 */
@Repository("FaultAssetRepository")
//@Transactional(propagation = Propagation.NESTED, value = "transactionManageCre")
@Transactional(transactionManager="transactionManagerCre")//, value = "cre")
public interface FaultAssetRepository  extends JpaRepository<FaultAsset, Integer> {
	/*public List<FaultAsset> findByCategoryOrderByCategoryAsc(String category);
	public List<FaultAsset> findBySubCategoryOrderBySubCategoryAsc(String subCategory);
	
	public List<FaultAsset> findByCategoryContainingOrSubCategoryContaining(String pattern);*/
}
