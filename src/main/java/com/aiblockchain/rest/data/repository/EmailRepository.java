/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.Email;

/**
 * @author Athi
 *
 */
//@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

	//Email findEmail(long id);

}
