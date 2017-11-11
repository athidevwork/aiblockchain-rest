/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.ContactEmail;

/**
 * @author Athi
 *
 */
@Repository
public interface ContactEmailRepository extends JpaRepository<ContactEmail, Integer> {

	//List<ContactEmail> findEmailsForName(String legalName);

}
