/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.ContactPhone;

/**
 * @author Athi
 *
 */
@Repository
public interface ContactPhoneRepository extends JpaRepository<ContactPhone, Integer> {

	//List<ContactPhone> findPhonesForName(String legalName);

}
