/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.ContactAddress;

/**
 * @author Athi
 *
 */
//@Repository
public interface ContactAddressRepository extends JpaRepository<ContactAddress, Integer> {
	//List<ContactAddress> findAddrsForName(String legalName);	
}
