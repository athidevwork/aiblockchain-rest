/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.data.entity.Account;



/**
 * @author Athi
 *
 */
@Repository
//@Transactional(propagation = Propagation.NESTED, value = "transactionManager")
public interface AccountRepository extends JpaRepository<Account, Integer> {
	//public List<Account> findAcctsForName(String legalName);
}
