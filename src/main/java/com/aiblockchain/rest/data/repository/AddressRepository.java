/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.Address;

/**
 * @author Athi
 *
 */
//@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

	//Address findAddr(long id);
	
}
